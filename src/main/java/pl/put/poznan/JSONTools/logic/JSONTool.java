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

    /**
     * Store json as JsonNode - class from jackson library
     */
    protected JsonNode jsonObject;

    /**
     * Constructor
     * @param file - raw json string
     */
    public JSONTool(String file) {
        try {
            jsonObject = new ObjectMapper().readTree(file);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor
     * @param file - ready JsonNode object
     */
    public JSONTool(JsonNode file) {
            jsonObject = file;
    }

    /**
     * Getter JsonObject
     * @return JsonNode
     */
    public JsonNode getJsonObject() {
        return jsonObject;
    }
}
