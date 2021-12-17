package pl.put.poznan.JSONTools.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class ExtractJSONTool extends JSONTool {

    private String jsonMinified;
    private String jsonPretty;

    public ExtractJSONTool(String file) {
        super(file);
    }

}
