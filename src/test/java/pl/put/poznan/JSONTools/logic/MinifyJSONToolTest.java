package pl.put.poznan.JSONTools.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

public class MinifyJSONToolTest {

    private static String minifiedCorrect;
    private static String prettifiedCorrect;

    @BeforeAll
    public static void setUp(){
        prettifiedCorrect = new String("{\r\n" +
                "  \"menu\" : {\r\n" +
                "    \"id\" : \"file\",\r\n" +
                "    \"value\" : \"File\",\r\n" +
                "    \"popup\" : {\r\n" +
                "      \"menuitem\" : [ {\r\n" +
                "        \"value\" : \"New\",\r\n" +
                "        \"onclick\" : \"CreateNewDoc()\"\r\n" +
                "      }, {\r\n" +
                "        \"value\" : \"Open\",\r\n" +
                "        \"onclick\" : \"OpenDoc()\"\r\n" +
                "      }, {\r\n" +
                "        \"value\" : \"Close\",\r\n" +
                "        \"onclick\" : \"CloseDoc()\"\r\n" +
                "      } ]\r\n" +
                "    }\r\n" +
                "  }\r\n" +
                "}");
        minifiedCorrect = new String("{\"menu\":{\"id\":\"file\",\"value\":\"File\",\"popup\":{\"menuitem\":[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}]}}}");
    }

    @Test
    public void testMinify(){
        MinifyJSONTool mMini = new MinifyJSONTool(minifiedCorrect);
        MinifyJSONTool pMini = new MinifyJSONTool(prettifiedCorrect);
        assertEquals(minifiedCorrect, mMini.getMinified(), "Correct json minifying from mini");
        assertEquals(minifiedCorrect, pMini.getMinified(), "Correct json minifying from pretty");
    }

    @Test
    public void testPrettify(){
        MinifyJSONTool mMini = new MinifyJSONTool(minifiedCorrect);
        MinifyJSONTool pMini = new MinifyJSONTool(prettifiedCorrect);
        assertEquals(prettifiedCorrect, mMini.getPretty(), "Correct json prettifying from mini");
        assertEquals(prettifiedCorrect, pMini.getPretty(), "Correct json prettifying from pretty");
    }

    @Test
    public void testProcessingException(){
        MinifyJSONTool mockClass = mock(MinifyJSONTool.class, withSettings().useConstructor("Czy ten JSON jest ok ; ? ;"));
        Exception e = Mockito.spy(new Exception());
        Mockito.doThrow(e).when(mockClass).getMinified();

        Mockito.verify(e).printStackTrace();
    }

//    @Test
//    public void FullToShortcutDecoratorMockTest() {
//        FullToShortcutDecorator decorator = mock(FullToShortcutDecorator.class, withSettings().useConstructor("Pieczywo to na przykład chleb i bułki"));
//
//        when(decorator.decore()).thenReturn("Pieczywo to np. chleb i bułki");
//
//        Assert.assertEquals(decorator.decore(), new FullToShortcutDecorator("Pieczywo to na przykład chleb i bułki").decore());
//    }

}
