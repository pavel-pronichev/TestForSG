import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Test {

    static ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
    public static volatile int counter = 1;

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File(args[0]));

        NodeList shapeLevel = document.getChildNodes();

        NodeList figureLevel = shapeLevel.item(0).getChildNodes();
        for (int i = 0; i < figureLevel.getLength(); i++) {
            if (figureLevel.item(i).getNodeType() == Node.ELEMENT_NODE) {
                concurrentLinkedQueue.add(figureLevel.item(i));
            }
        }

        Set<Thread> set = new HashSet<>();
        int cQ = 4;
        if(!args[1].isEmpty()){
            cQ = Integer.parseInt(args[1]);
        }

        for(int i = 0; i<cQ; i++){
            Controller controller = new Controller();
            Thread thread = new Thread(controller);
            thread.start();
            set.add(thread);
        }

        for(Thread x : set){
            try {
                x.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static List<Object> clearNodeList(NodeList nodeList) {

        List<Object> list = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                list.add(nodeList.item(i).getTextContent());
            }
        }
        return list;
    }

    public static class Controller implements Runnable {

        private boolean isOver = false;

        public Controller(){}
        @Override
        public void run() {

            while (!isOver) {
                if (!concurrentLinkedQueue.isEmpty()) {

                    int count = counter++;

                    Node figureLevel = (Node) concurrentLinkedQueue.poll();

                    String nameFigure = figureLevel.getNodeName();
                    NodeList tmp = figureLevel.getChildNodes();

                    List<Object> list = clearNodeList(tmp);
                    String colorItem = list.get(0).toString();
                    switch (nameFigure) {
                        case "triangle":
                            Triangle triangle = new Triangle(Float.parseFloat(list.get(1).toString()), Float.parseFloat(list.get(2).toString()), Float.parseFloat(list.get(3).toString()));

                            System.out.println(count + ": " + colorItem + "-" + triangle.getSquare());
                            break;
                        case "circle":
                            Circle circle = new Circle(Float.parseFloat(list.get(1).toString()));
                            System.out.println(count + ": " + colorItem + "-" + circle.getSquare());
                            break;
                        case "square":
                            Square square = new Square(Float.parseFloat(list.get(1).toString()));
                            System.out.println(count + ": " + colorItem + "-" + square.getSquare());
                            break;
                        case "rectangle":
                            Rectangle rectangle = new Rectangle(Float.parseFloat(list.get(1).toString()), Float.parseFloat(list.get(2).toString()));
                            System.out.println(count + ": " + colorItem + "-" + rectangle.getSquare());
                    }

                }else isOver = true;


            }
        }
    }

}
