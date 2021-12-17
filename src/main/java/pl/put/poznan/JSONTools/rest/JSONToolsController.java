package pl.put.poznan.JSONTools.rest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.JSONTools.logic.JSONTools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;


@RestController
//@RequestMapping("/{text}")
@RequestMapping("/")
public class JSONToolsController {

    private static final Logger logger = LoggerFactory.getLogger(JSONToolsController.class);

    @RequestMapping(path = "/normal", method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text, @RequestParam(value="transforms", defaultValue="upper,escape") String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        JSONTools transformer = new JSONTools(transforms);
        return transformer.transform(text);
    }

    @GetMapping(path = {"/json", "/json/{path}"})
    public JsonNode path(@PathVariable(required=false,name="path") String path,
                     @RequestParam(required=false) Map<String,String> qparams) {
        try {
            String file = Files.readString(Path.of("src/main/resources/" + qparams.get("path")));
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(file);
            mapper.enable(SerializationFeature.CLOSE_CLOSEABLE);
            String minified = mapper.writeValueAsString(actualObj);

            return mapper.readTree(minified);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
//        return "Received path: " + /*qparams.values().toArray()[0] +*/ qparams.get("path");
    }

    @GetMapping(path = {"/mini", "/mini/{minified}"})
    public JsonNode mini(@PathVariable(required=false,name="minified") String minified,
                         @RequestParam(required=false) Map<String,String> qparams) {
//        return qparams.get("minified");
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(qparams.get("minified"));
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String unminified = mapper.writeValueAsString(actualObj);

            return mapper.readTree(unminified);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
//        return "Received path: " + /*qparams.values().toArray()[0] +*/ qparams.get("path");
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text, @RequestBody String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        JSONTools transformer = new JSONTools(transforms);
        return transformer.transform(text);
    }



}


