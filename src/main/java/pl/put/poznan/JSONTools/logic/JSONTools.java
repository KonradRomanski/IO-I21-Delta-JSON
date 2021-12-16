package pl.put.poznan.JSONTools.logic;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class JSONTools {

    private ObjectMapper mapper = new ObjectMapper();

    public JSONTools(String[] transforms){
        this.mapper = transforms;
    }

    public String transform(String text){
        // of course, normally it would do something based on the transforms
        return text.toUpperCase();
    }
}
