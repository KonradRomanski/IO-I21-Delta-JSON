package pl.put.poznan.JSONTools.logic;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class JSONTools {

    private JsonNode jsonObject;
    private String jsonMinified;
    private String jsonPretty;


    public JSONTools(String file) {
        try {
            jsonObject = new ObjectMapper().readTree(file);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void minify() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.CLOSE_CLOSEABLE);
        jsonMinified = mapper.writeValueAsString(jsonObject);

    }

    private void prettify() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        jsonPretty = mapper.writeValueAsString(jsonObject);
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
