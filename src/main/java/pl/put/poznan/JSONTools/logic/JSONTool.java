package pl.put.poznan.JSONTools.logic;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 *  Main component JSONTool contain JSON in JsonNode format.
 *  Class can be use for store JSONs.
 */
public class JSONTool {

    protected JsonNode jsonObject;

    /**
     *
     * @param file
     */
    public JSONTool(String file) {
        try {
            jsonObject = new ObjectMapper().readTree(file);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public JSONTool(JsonNode file) {
            jsonObject = file;
    }

    /**
     *
     * @return JsonNode
     */
    public JsonNode getJsonObject() {
        return jsonObject;
    }
}
