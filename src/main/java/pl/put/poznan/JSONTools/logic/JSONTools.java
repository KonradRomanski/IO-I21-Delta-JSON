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

    private ObjectMapper mapper;
    private JsonNode jsonObject;

    public JSONTools(String file) throws JsonProcessingException {
        mapper = new ObjectMapper();
        jsonObject = mapper.readTree(file);
    }
}
