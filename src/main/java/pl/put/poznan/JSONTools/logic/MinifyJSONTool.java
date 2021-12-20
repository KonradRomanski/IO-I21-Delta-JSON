package pl.put.poznan.JSONTools.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class MinifyJSONTool extends JSONTool {

    private String jsonMinified;
    private String jsonPretty;

    public MinifyJSONTool(String file) {
        super(file);
    }
    public MinifyJSONTool(JSONTool file) {
        super(file.getJsonObject());
    }

    private void minify() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.CLOSE_CLOSEABLE);
        jsonMinified = mapper.writeValueAsString(super.getJsonObject());
    }

    private void prettify() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        jsonPretty = mapper.writeValueAsString(super.getJsonObject());
    }

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
