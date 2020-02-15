
package deadwood;
    
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class ParseXML{

    public Document getDocFromFile(String filename) throws ParserConfigurationException{
        {
                        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = null;
           
        try{
            doc = db.parse(filename);
        }catch (Exception ex){
            System.out.println("XML parse failure");
            ex.printStackTrace();
        }
           return doc;
        }
        
        }  
             
    public Board buildBoard(Document d){   
            
        Board board = new Board();
        Scene[] scenes = new Scene[10];
        d.getDocumentElement().normalize();
        Element root = d.getDocumentElement();
        NodeList sets = root.getElementsByTagName("set");
            
        for (int i=0; i<sets.getLength();i++){  
            Scene scene = new Scene();
            LinkedList<Role> offCardRoles= new LinkedList<Role>();
                
            Node set = sets.item(i);
            String setName = set.getAttributes().getNamedItem("name").getNodeValue();
            scene.setName(setName);                          
                
            NodeList setChildren = set.getChildNodes();
                
            for (int j=0; j< setChildren.getLength(); j++){                    
                Node setChildrenSub = setChildren.item(j);                
                 
                if("takes".equals(setChildrenSub.getNodeName())){
                    NodeList takesChildren = setChildrenSub.getChildNodes();
                    int[] takes = {0,0,0,0,0,0,0,0,0,0};
                    for(int k=0; k< takesChildren.getLength(); k++ ){                          
                        try{

                            Node take = takesChildren.item(k);

                            if((take.getNodeName()).equals("take")){
                                String takeNumber= take.getAttributes().getNamedItem("number").getNodeValue();
                                takes[k] = Integer.parseInt(takeNumber);
                            }

                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    Arrays.sort(takes);
                    scene.setTotalShots(takes[9]);
                }
             
                else if("parts".equals(setChildrenSub.getNodeName())){

                    NodeList partsChildren = setChildrenSub.getChildNodes();
                    for(int k=0; k< partsChildren.getLength();k++){

                        try{

                            Node part = partsChildren.item(k);
                                
                            if("part".equals(part.getNodeName())){
                                
                                Role role = new Role();
                                String partName = part.getAttributes().getNamedItem("name").getNodeValue();
                                role.setName(partName);
                                String partLevel = part.getAttributes().getNamedItem("level").getNodeValue();
                                role.setRank(Integer.parseInt(partLevel));
                                NodeList partChildren = part.getChildNodes();
                                        
                                for(int m = 0; m< partChildren.getLength(); m++){                 
                                        
                                    Node childLine = partChildren.item(m);   
                                    if("line".equals(childLine.getNodeName())){
                                        String line = childLine.getTextContent();
                                        role.setLine(line);
                                    }              
                                }
                                offCardRoles.add(role); 
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                scene.setOffCardRoles(offCardRoles); 
            }
            scenes[i] = scene;
        }
        board.setScenes(scenes);
        return board;   
    }    

    public Stack<SceneCard> buildDeck(Document d){
            
        Stack<SceneCard> StackOfCards = new Stack<>();          
        d.getDocumentElement().normalize();
        Element root = d.getDocumentElement();
        NodeList cards = root.getElementsByTagName("card");
            
        for (int i=0; i< cards.getLength(); i++){
                
            SceneCard Card = new SceneCard();
            LinkedList<Role> listOfRoles= new LinkedList<Role>();
            Node card = cards.item(i);
                
            String cardBudget = card.getAttributes().getNamedItem("budget").getNodeValue();
            Card.setBudget(Integer.parseInt(cardBudget));            
            String cardName = card.getAttributes().getNamedItem("name").getNodeValue();
            Card.setName(cardName);
                
            NodeList cardChildren = card.getChildNodes();
                
            for(int j=0; j< cardChildren.getLength(); j++){
                Node cardChild = cardChildren.item(j);
                    
                if("scene".equals(cardChild.getNodeName())){
                    String sceneNumber = cardChild.getAttributes().getNamedItem("number").getNodeValue();
                    Card.setNumber(Integer.parseInt(sceneNumber));
                    String sceneDescription = cardChild.getTextContent();
                    Card.setDescription(sceneDescription);
                }

                if("part".equals(cardChild.getNodeName())){
                    Role cardRole = new Role();

                    String partName = cardChild.getAttributes().getNamedItem("name").getNodeValue();
                    cardRole.setName(partName);
                    String partLevel = cardChild.getAttributes().getNamedItem("level").getNodeValue();
                    cardRole.setRank(Integer.parseInt(partLevel));

                    NodeList partChildren = cardChild.getChildNodes();
                        
                    for(int k=0; k< partChildren.getLength(); k++){
                        try{
                            
                            Node partChild = partChildren.item(k);
                            if("line".equals(partChild.getNodeName())){
                                String Line = partChild.getTextContent();
                                cardRole.setLine(Line);                                
                            }                        
                        }catch(Exception e){
                            e.printStackTrace();
                        }             
                    }
                    listOfRoles.add(cardRole);
                }   
            }
            Card.setRoles(listOfRoles);
            StackOfCards.push(Card);        
        }
        return StackOfCards;
    }   
}
