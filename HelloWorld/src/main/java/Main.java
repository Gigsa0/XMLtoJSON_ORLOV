import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class Main {
    public static void main(String[] args) throws Exception {
        ReadXML("src/main/resources/XML/test");
        ChekJSON("src/main/resources/XML/test");
    }

    public static boolean ReadXML(String fileName) throws IOException, SAXException, ParserConfigurationException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileName + ".xml");
            Element root = doc.getDocumentElement();
            Elements elemets = new Elements(root.getNodeName());
            ParsXML(root, elemets);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writer().withDefaultPrettyPrinter().writeValue(new File(fileName + ".json"), elemets);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean ChekJSON(String jsonFile) throws IOException {
        if (ChekJSON.ChekJSON(jsonFile + ".json")) {
            System.out.println("JSONFile: " + jsonFile + ".json" + " Correct!");
            return true;
        } else {
            System.out.println("JSONFile: " + jsonFile + ".json" + " Incorrect!");
            return false;
        }
    }


    private static void ParsXML(Node node, Elements elements) {
        if (node.hasChildNodes() && (node.getNodeType() == Node.ELEMENT_NODE)) {
            NodeList list = node.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Elements child = elements.addChild(list.item(i).getNodeName());
                    ParsXML(list.item(i), child);
                }
                if (list.item(i).getNodeType() == Node.TEXT_NODE) {
                    if (!node.getTextContent().contains("\n")) {
                        String text = elements.getText(node.getTextContent());
                    }
                }
            }
        }
    }
}

