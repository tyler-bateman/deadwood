// Example Code for parsing XML file
// Dr. Moushumi Sharmin
// CSCI 345
package deadwood;
    
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ParseXML{

   
        // building a document from the XML file
        // returns a Document object after loading the book.xml file.
        public Document getDocFromFile(String filename) throws ParserConfigurationException{
        {
                        
           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           DocumentBuilder db = dbf.newDocumentBuilder();
           Document doc = null;
           
           try{
               doc = db.parse(filename);
           } catch (Exception ex){
               System.out.println("XML parse failure");
               ex.printStackTrace();
           }
           return doc;
        } // exception handling
        
        }  
        
        // reads data from XML file and prints data
        public void readBoardData(Document d){   
            d.getDocumentElement().normalize();
            Element root = d.getDocumentElement();
            NodeList sets = root.getElementsByTagName("set");
            
            for (int i=0; i<sets.getLength();i++){                
                System.out.println("Printing information for set "+(i+1));
                
                //reads data from the nodes
                Node set = sets.item(i);
                String setName = set.getAttributes().getNamedItem("name").getNodeValue();
                System.out.println("Set name = "+setName);
               
                //reads data                
                
                NodeList setChildren = set.getChildNodes();
                
                for (int j=0; j< setChildren.getLength(); j++){                    
                    Node setChildrenSub = setChildren.item(j);
                
                    if("neighbors".equals(setChildrenSub.getNodeName())){ 
                        System.out.println("     Neighbors :");
                        NodeList neighborsChildren = setChildrenSub.getChildNodes();
                      
                        for(int z=0; z< neighborsChildren.getLength(); z++ ){
                            
                            try{
                            Node neighbor = neighborsChildren.item(z);
                            String neighborName= neighbor.getAttributes().getNamedItem("name").getNodeValue();
                            System.out.println("         Neighbor name = " + neighborName);
                        }catch(Exception e){
                            //e.printStackTrace();
                        }         
                        }
                  }
                 
                    else if("area".equals(setChildrenSub.getNodeName())){
                        System.out.println("     Area :");
                     String w = setChildrenSub.getAttributes().getNamedItem("w").getNodeValue();
                     System.out.println("         w = "+w);
                     String h = setChildrenSub.getAttributes().getNamedItem("h").getNodeValue();
                     System.out.println("         h = "+h);
                     String y = setChildrenSub.getAttributes().getNamedItem("y").getNodeValue();
                     System.out.println("         y = "+y);
                     String x = setChildrenSub.getAttributes().getNamedItem("x").getNodeValue();
                     System.out.println("         x = "+x);                    
                  }
                  
                  else if("takes".equals(setChildrenSub.getNodeName())){
                      System.out.println("     Takes :");
                       NodeList takesChildren = setChildrenSub.getChildNodes();
                       
                       for(int z=0; z< takesChildren.getLength(); z++ ){
                           try{
                            Node take = takesChildren.item(z);
                            String takeNumber= take.getAttributes().getNamedItem("number").getNodeValue();
                            System.out.println("         Take number = " + takeNumber);
                            NodeList takeChildren = take.getChildNodes();
                            
                            for(int f =0; f< takeChildren.getLength(); f++){
                                System.out.println("             Take area :");
                                Node area = takeChildren.item(f);
                                String w = area.getAttributes().getNamedItem("w").getNodeValue();
                                System.out.println("                 w = "+w);
                                String h = area.getAttributes().getNamedItem("h").getNodeValue();
                                System.out.println("                 h = "+h);
                                String y = area.getAttributes().getNamedItem("y").getNodeValue();
                                System.out.println("                 y = "+y);
                                String x = area.getAttributes().getNamedItem("x").getNodeValue();
                                System.out.println("                 x = "+x);
                            }
                           }catch(Exception e){
                               
                           }
                        }                     
                  }
                  
                  else if("parts".equals(setChildrenSub.getNodeName())){
                      System.out.println("     Parts :");
                     NodeList partsChildren = setChildrenSub.getChildNodes();
                     
                     for (int z=0; z< partsChildren.getLength(); z++){
                         try{
                         Node part = partsChildren.item(z);
                         String partName = part.getAttributes().getNamedItem("name").getNodeValue();
                         System.out.println("         Part name = "+ partName);
                         String partLevel = part.getAttributes().getNamedItem("level").getNodeValue();
                         System.out.println("         Part level = "+ partLevel);
                         
                         NodeList partChildren = part.getChildNodes();
                         
                         for(int f = 0; f<partChildren.getLength(); f++){
                             Node child = partChildren.item(f);
                             
                             if("area".equals(child.getNodeName())){
                                 System.out.println("             Part area :");
                                String w = child.getAttributes().getNamedItem("w").getNodeValue();
                                System.out.println("                 w = "+w);
                                String h = child.getAttributes().getNamedItem("h").getNodeValue();
                                System.out.println("                 h = "+h);
                                String y = child.getAttributes().getNamedItem("y").getNodeValue();
                                System.out.println("                 y = "+y);
                                String x = child.getAttributes().getNamedItem("x").getNodeValue();
                                System.out.println("                 x = "+x);
                             }
                             
                             if("line".equals(child.getNodeName())){
                                 String line = child.getTextContent();
                                 System.out.println("             Line = "+line);
                             }
                         }
                         
                     }catch (Exception e){
                  } 
                          
                     }
                  }
                                 
                
                } //for childnodes
                
                System.out.println("\n");
                
            }//for book nodes
        
        }// method
    
    



}//class