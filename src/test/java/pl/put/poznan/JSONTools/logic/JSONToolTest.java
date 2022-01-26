package pl.put.poznan.JSONTools.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JSONToolTest {

    @Test
    public void TestJSONConstructor(){
        JSONTool jsonTool = new JSONTool("WRONG {;A} ; ; JSON");
        assertNull(jsonTool.getJsonObject());
        jsonTool = new JSONTool("'{\"name\":\"John\", \"age\":30, \"car\":null}'");
        assertNotNull(jsonTool.getJsonObject());
        JSONTool secondJsonTool = new JSONTool(jsonTool.getJsonObject());
        assertEquals(secondJsonTool.getJsonObject(), jsonTool.getJsonObject(), "From jsonNode constructor");
    }
}
