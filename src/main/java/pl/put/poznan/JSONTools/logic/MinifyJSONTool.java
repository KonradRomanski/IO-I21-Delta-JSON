package pl.put.poznan.JSONTools.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

/**
 * Decorator for JSONTool component
 *
 */
public class MinifyJSONTool{// extends JSONTool {

    /**
     * Store json as string object without white characters
     */
    private String jsonMinified;

    /**
     * Store json as string object with white characters
     */
    private String jsonPretty;

    /**
     * Store JSONTool component
     */
    private JSONTool jsonTool;

    /**
     * Create object from raw string
     * @param file - raw json string
     */
    public MinifyJSONTool(String file) {
        jsonTool = new JSONTool(file);
    }

    /**
     * Create object from parent class object
     * @param file - parent class object JSONTool
     */
    public MinifyJSONTool(JSONTool file) {
        jsonTool = file;
    }

    /**
     * Processing Json to minify string
     * @throws JsonProcessingException - when wrong JsonNode
     */
    public void minify() throws JsonProcessingException {
        if (jsonTool.toString() == "") throw new JsonProcessingException("Error"){};

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.CLOSE_CLOSEABLE);
        jsonMinified = mapper.writeValueAsString(jsonTool.getJsonObject());
    }

    /**
     * Processing Json to pretty string
     * @throws JsonProcessingException - when wrong JsonNode
     */
    private void prettify() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        jsonPretty = mapper.writeValueAsString(jsonTool.getJsonObject());
    }

    /**
     * Getter for minified json string
     * This will catch exception from processing method
     * @return string
     */
    public String getMinified() {
        if (jsonMinified == null){
            try {
                this.minify();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return jsonMinified;
    }

    /**
     * Getter for pretty json string
     * This will catch exception from processing method
     * @return string
     */
    public String getPretty() {
        if (jsonPretty == null){
            try {
                this.prettify();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return jsonPretty;
    }
}
