import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Хозяин on 28.10.2015.
 */
public class Test {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File("C:/Users/Хозяин/Desktop/test.xml"));

        NodeList shapeLevel = document.getChildNodes();

       // ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();

        int count = 0;
        NodeList figureLevel = shapeLevel.item(0).getChildNodes();
        for (int i = 0; i < figureLevel.getLength(); i++) {
            if (figureLevel.item(i).getNodeType() == Node.ELEMENT_NODE) {
                String nameFigure = figureLevel.item(i).getNodeName();
                NodeList tmp = figureLevel.item(i).getChildNodes();

                List<Object> list = clearNodeList(tmp);
                String colorItem = list.get(0).toString();
                switch (nameFigure) {
                    case "triangle":
                        Triangle triangle = new Triangle(Float.parseFloat(list.get(1).toString()),Float.parseFloat(list.get(2).toString()),Float.parseFloat(list.get(3).toString()));
                        System.out.println(count + ": " + colorItem+" "+ triangle.getSquare());
                        break;
                    case "circle":
                        Circle circle = new Circle(Float.parseFloat(list.get(1).toString()));
                        System.out.println(count + ": " + colorItem+" "+ circle.getSquare());
                        break;
                    case "square":
                        Square square = new Square(Float.parseFloat(list.get(1).toString()));
                        System.out.println(count + ": " + colorItem+" "+ square.getSquare());
                        break;
                    case "rectangle":
                        Rectangle rectangle = new Rectangle(Float.parseFloat(list.get(1).toString()),Float.parseFloat(list.get(2).toString()));
                        System.out.println(count + ": " + colorItem+" "+ rectangle.getSquare());

                }

                count++;
            }
        }


    }

    public static List<Object> clearNodeList(NodeList nodeList){

        List<Object> list = new ArrayList<>();

        for (int i = 0; i<nodeList.getLength(); i++){
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                list.add(nodeList.item(i).getTextContent());
            }
        }
        return list;
    }
}
