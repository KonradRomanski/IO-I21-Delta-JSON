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
        prettifiedCorrect = new String("{\n" +
                "  \"menu\" : {\n" +
                "    \"id\" : \"file\",\n" +
                "    \"value\" : \"File\",\n" +
                "    \"popup\" : {\n" +
                "      \"menuitem\" : [ {\n" +
                "        \"value\" : \"New\",\n" +
                "        \"onclick\" : \"CreateNewDoc()\"\n" +
                "      }, {\n" +
                "        \"value\" : \"Open\",\n" +
                "        \"onclick\" : \"OpenDoc()\"\n" +
                "      }, {\n" +
                "        \"value\" : \"Close\",\n" +
                "        \"onclick\" : \"CloseDoc()\"\n" +
                "      } ]\n" +
                "    }\n" +
                "  }\n" +
                "}");
        minifiedCorrect = new String("{\"menu\":{\"id\":\"file\",\"value\":\"File\",\"popup\":{\"menuitem\":[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}]}}}");
    }

    @Test
    public void testMinify(){
        MinifyJSONTool mMini = new MinifyJSONTool(minifiedCorrect);
        MinifyJSONTool pMini = new MinifyJSONTool(prettifiedCorrect);
        assertSame(minifiedCorrect, mMini.getMinified(), "Correct json minifying from mini");
        assertSame(minifiedCorrect, pMini.getMinified(), "Correct json minifying from pretty");
    }

    @Test
    public void testPrettify(){
        MinifyJSONTool mMini = new MinifyJSONTool(minifiedCorrect);
        MinifyJSONTool pMini = new MinifyJSONTool(prettifiedCorrect);
        assertSame(prettifiedCorrect, mMini.getPretty(), "Correct json prettifying from mini");
        assertSame(prettifiedCorrect, pMini.getPretty(), "Correct json prettifying from pretty");
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
