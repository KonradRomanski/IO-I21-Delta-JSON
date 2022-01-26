package pl.put.poznan.JSONTools.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JSONToolTest {

    @Test
    public void TestJSONConstructor(){
        JSONTool jsonTool = new JSONTool("WRONG {;A} ; ; JSON");
        assertNull(jsonTool.getJsonObject());
        jsonTool = new JSONTool("{\"menu\":{\"id\":\"file\",\"value\":\"File\",\"popup\":{\"menuitem\":[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}]}}}");
        assertNotNull(jsonTool.getJsonObject());
        JSONTool secondJsonTool = new JSONTool(jsonTool.getJsonObject());
        assertEquals(secondJsonTool.getJsonObject(), jsonTool.getJsonObject(), "From jsonNode constructor");
    }

}
