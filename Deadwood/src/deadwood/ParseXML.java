
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
        
        Board board = Board.getInstance();
        Scene[] scenes = new Scene[10];
        Space[] spaces = new Space[12];
        d.getDocumentElement().normalize();
        Element root = d.getDocumentElement();
        NodeList sets = root.getElementsByTagName("set");
        try{ 
        for (int i=0; i<sets.getLength();i++){  
            Scene scene = new Scene();
            LinkedList<Role> offCardRoles = new LinkedList<Role>();
            //LinkedList<String> Neighbors = new LinkedList<String>();
                
            Node setNode = sets.item(i);
            
            if("set".equals(setNode.getNodeName())){
           
                String setName = setNode.getAttributes().getNamedItem("name").getNodeValue();
                scene.setName(setName); 
                scene.ID = i;
                
                NodeList setChildren = setNode.getChildNodes();
                
                for (int j=0; j< setChildren.getLength(); j++){                    
                    Node setChildrenSub = setChildren.item(j);      
                    
                    /*if("neighbors".equals(setChildrenSub.getNodeName())){
                        NodeList neighborsChildren = setChildrenSub.getChildNodes();
                         
                        for(int k=0; k <neighborsChildren.getLength(); k++){
                            Node neighborChild = neighborsChildren.item(k);
                            if("neighbor".equals(neighborChild.getNodeName())){
                                String neighborName = neighborChild.getAttributes().getNamedItem("name").getNodeValue();
                                Neighbors.add(neighborName);
                            }
                        }
                    }*/
                 
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
                        scene.resetShots();
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
                    //scene.setAdjacentSpaces(Neighbors);
                }
            }
                       
            scenes[i] = scene;
            spaces[i] = scene;
        }

        NodeList trailer = root.getElementsByTagName("trailer");
        for(int i =10; i<(trailer.getLength()+10);i++){
            Space space = new Space();
            //LinkedList<String> Neighbors = new LinkedList<String>();
            
            Node trailerNode = trailer.item(0);          
            space.setName("trailer"); 
            space.ID = i;
            
           /* NodeList trailerChildren = trailerNode.getChildNodes();
            Node neighborNode = trailerChildren.item(1);
            if("neighbors".equals(neighborNode.getNodeName())){
                
                NodeList neighborsChildren = neighborNode.getChildNodes();
          
                for(int k=0; k <neighborsChildren.getLength(); k++){
                    Node neighborChild = neighborsChildren.item(k);
                    if("neighbor".equals(neighborChild.getNodeName())){
                        String neighborName = neighborChild.getAttributes().getNamedItem("name").getNodeValue();
                        Neighbors.add(neighborName);
                    }
                } 
            }
            scene.setAdjacentSpaces(Neighbors);   */
            spaces[i] = space;
        }
        
        NodeList office = root.getElementsByTagName("office");
        for(int i =11; i<(office.getLength()+11);i++){
            Space space = new Space();
            //LinkedList<String> Neighbors = new LinkedList<String>();   
            
            Node officeNode = office.item(0);          
            space.setName("office"); 
            space.ID = i;
            
            /*NodeList officeChildren = officeNode.getChildNodes();

            Node neighborNode = officeChildren.item(1);
            if("neighbors".equals(neighborNode.getNodeName())){
                
                NodeList neighborsChildren = neighborNode.getChildNodes();
          
                for(int k=0; k <neighborsChildren.getLength(); k++){
                    Node neighborChild = neighborsChildren.item(k);
                    if("neighbor".equals(neighborChild.getNodeName())){
                        String neighborName = neighborChild.getAttributes().getNamedItem("name").getNodeValue();
                        Neighbors.add(neighborName);
                    }
                } 
            }
            scene.setAdjacentSpaces(Neighbors);*/
            spaces[i] = space;  
        }
        
        board.setScenes(scenes);
        board.setSpaces(spaces);
          
        }catch(Exception e){
            e.printStackTrace();
        }
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
    
    public void buildAdjacentSpaces(Document d, Board board){
        d.getDocumentElement().normalize();
        Element root = d.getDocumentElement();
        NodeList sets = root.getElementsByTagName("set");
        try{ 
        for (int i=0; i<sets.getLength();i++){  
            LinkedList<Space> Neighbors = new LinkedList<>();
                
            Node setNode = sets.item(i);
            
            if("set".equals(setNode.getNodeName())){           
                NodeList setChildren = setNode.getChildNodes();
                
                for (int j=0; j< setChildren.getLength(); j++){                    
                    Node setChildrenSub = setChildren.item(j);      
                    
                    if("neighbors".equals(setChildrenSub.getNodeName())){
                        NodeList neighborsChildren = setChildrenSub.getChildNodes();
                         
                        for(int k=0; k <neighborsChildren.getLength(); k++){
                            Node neighborChild = neighborsChildren.item(k);
                            if("neighbor".equals(neighborChild.getNodeName())){
                                String neighborName = neighborChild.getAttributes().getNamedItem("name").getNodeValue();
                                for(int n=0; n<board.getScenes().length;n++){
                                    if(board.getScene(n).getName().equals(neighborName)){
                                        Neighbors.add(board.getScene(n));
                                    }
                                }
                                
                                //Neighbors.add(neighborName);
                            }
                        }
                    }
                }
            }
            board.getScene(i).setAdjacentSpaces(Neighbors);
        }
        
        NodeList trailer = root.getElementsByTagName("trailer");
        for(int i =10; i<(trailer.getLength()+10);i++){
            LinkedList<Space> Neighbors = new LinkedList<>();
            
            Node trailerNode = trailer.item(0);          
            
            NodeList trailerChildren = trailerNode.getChildNodes();
            Node neighborNode = trailerChildren.item(1);
            if("neighbors".equals(neighborNode.getNodeName())){  
                NodeList neighborsChildren = neighborNode.getChildNodes();
                
                for(int k=0; k <neighborsChildren.getLength(); k++){
                    Node neighborChild = neighborsChildren.item(k);
                    if("neighbor".equals(neighborChild.getNodeName())){
                        String neighborName = neighborChild.getAttributes().getNamedItem("name").getNodeValue();
                        for(int n=0; n<board.getScenes().length;n++){
                            if(board.getScene(n).getName().equals(neighborName)){
                                Neighbors.add(board.getScene(n));
                            }
                        }
                    }
                } 
            }
            board.getSpace(i).setAdjacentSpaces(Neighbors);
        }
        
        NodeList office = root.getElementsByTagName("office");
        for(int i =11; i<(trailer.getLength()+11);i++){
            LinkedList<Space> Neighbors = new LinkedList<>();
            
            Node officeNode = office.item(0);          
            
            NodeList officeChildren = officeNode.getChildNodes();
            Node neighborNode = officeChildren.item(1);
            if("neighbors".equals(neighborNode.getNodeName())){  
                NodeList neighborsChildren = neighborNode.getChildNodes();
                
                for(int k=0; k <neighborsChildren.getLength(); k++){
                    Node neighborChild = neighborsChildren.item(k);
                    if("neighbor".equals(neighborChild.getNodeName())){
                        String neighborName = neighborChild.getAttributes().getNamedItem("name").getNodeValue();
                        for(int n=0; n<board.getScenes().length;n++){
                            if(board.getScene(n).getName().equals(neighborName)){
                                Neighbors.add(board.getScene(n));
                            }
                        }
                    }
                } 
            }
            board.getSpace(i).setAdjacentSpaces(Neighbors);
        }
                
        }catch(Exception e){
            e.printStackTrace();
        }
        
            for(int i=0; i<board.getSpaces().length;i++){
                System.out.println(board.getSpace(i).ID + "  " + board.getSpace(i).getName());
            }
    }
    
    
}
