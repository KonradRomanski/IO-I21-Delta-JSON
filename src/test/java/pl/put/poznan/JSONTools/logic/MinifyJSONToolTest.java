package pl.put.poznan.JSONTools.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

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

    public MinifyJSONTool testThrow;

    @BeforeEach
    void set() { MinifyJSONTool testThrow = new MinifyJSONTool(""); }

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
    public void testProcessing(){
        MinifyJSONTool wrong = new MinifyJSONTool("Wrong json {} ; ; ;");
        assertNotEquals(prettifiedCorrect, wrong.getPretty(), "Correct json prettifying from pretty");
    }

    @Test
    public void testProcessingException() {
        Exception exception = assertThrows(NullPointerException.class, () ->
                this.testThrow.minify());
        assertEquals(null, exception.getMessage());
    }

    @Test
    public void testPrettifyMock(){
        MinifyJSONTool minifyJSONTool = mock(MinifyJSONTool.class);
        when(minifyJSONTool.getPretty()).thenReturn(null);

        assertEquals(null, minifyJSONTool.getMinified());
    }

    @Test
    public void testMinifyMock(){
        MinifyJSONTool minifyJSONTool = mock(MinifyJSONTool.class);
        when(minifyJSONTool.getMinified()).thenReturn("{\"menu\":{\"id\":\"file\",\"value\":\"File\",\"popup\":{\"menuitem\":[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}]}}}");

        assertEquals("{\"menu\":{\"id\":\"file\",\"value\":\"File\",\"popup\":{\"menuitem\":[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}]}}}", minifyJSONTool.getMinified());
    }
}
