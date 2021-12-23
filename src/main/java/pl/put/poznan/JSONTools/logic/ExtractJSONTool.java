package pl.put.poznan.JSONTools.logic;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.util.Set;
import java.util.Vector;


/**
 * Decorator for JSONTool component
 *
 */
public class ExtractJSONTool extends JSONTool {
    /**
     * Stores values to be removed or left
     */
    private final Vector<String> args;

    /**
     * Json string with removed values
     */
    private String jsonCleaned;

    /**
     * Json string with left values
     */
    private String jsonWithLeft;

    @JsonFilter("dynamicFilter")
    public class DynamicMixIn {
    }

    /**
     * Create object from raw string
     * @param file - raw json string
     * @param args - values to be removed or left
     */
    public ExtractJSONTool(String file, Vector<String> args) {
        super(file);
        this.args = args;
    }

    /**
     * Create object from raw string
     * @param file - raw json string
     * @param args - values to be removed or left
     */
    public ExtractJSONTool(JSONTool file, Vector<String> args) {
        super(file.getJsonObject());
        this.args = args;
    }

    /**
     * Processing Json to remove unwanted values
     * @throws JsonProcessingException - when wrong JsonNode
     */
    private void removeObjects() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        for(JsonNode node: jsonObject) {
            for(int i = 0 ; i < args.size(); i++) {
                ((ObjectNode)node).remove(args.get(i));
            }
        }
        jsonCleaned = mapper.writeValueAsString(super.getJsonObject());
    }

    /**
     * Processing Json to leave wanted values
     * @throws JsonProcessingException - when wrong JsonNode
     */
    private void leaveObjects() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Object.class, DynamicMixIn.class);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("dynamicFilter", SimpleBeanPropertyFilter.filterOutAllExcept((Set<String>) args));
        mapper.setFilterProvider(filterProvider);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        jsonWithLeft = mapper.writeValueAsString(super.getJsonObject());
    }

    /**
     * Getter for cleaned json string
     * This will catch exception from processing method
     * @return string
     */
    public String getCleaned() {
        if (jsonCleaned == null){
            try {
                this.removeObjects();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return jsonCleaned;
    }

    /**
     * Getter for pretty json string
     * This will catch exception from processing method
     * @return string
     */
    public String getPretty() {
        if (jsonWithLeft == null){
            try {
                this.leaveObjects();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return jsonWithLeft;
    }


}
