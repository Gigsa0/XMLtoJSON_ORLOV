import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MainTest {
    public Main main;

    @Test
    public void TestOneElement() throws ParserConfigurationException, SAXException, IOException {
        Assert.assertTrue("One element don't complete!", main.ReadXML("src/main/resources/XML/one_elem"));
        Assert.assertTrue("One element don't complete! Incorrect JSON file!", main.ChekJSON("src/main/resources/XML/one_elem"));
    }

    @Test
    public void TestFiveElements() throws ParserConfigurationException, SAXException, IOException {
        Assert.assertTrue("Five elements don't complete!", main.ReadXML("src/main/resources/XML/five_elem"));
        Assert.assertTrue("Five elements don't complete! Incorrect JSON file!", main.ChekJSON("src/main/resources/XML/five_elem"));
    }

    @Test
    public void TestWrongXML() throws ParserConfigurationException, SAXException, IOException {
        Assert.assertTrue("WRONG XML FILE OR FILE DOES NOT EXIST!", main.ReadXML("src/main/resources/XML/wrong"));
    }
}
