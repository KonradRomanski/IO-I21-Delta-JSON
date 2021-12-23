package pl.put.poznan.JSONTools.rest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.JSONTools.logic.ExtractJSONTool;
import pl.put.poznan.JSONTools.logic.JSONTool;
import pl.put.poznan.JSONTools.logic.MinifyJSONTool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Vector;


@RestController
//@RequestMapping("/{text}")
@RequestMapping("/")
public class JSONToolsController {

    private static final Logger logger = LoggerFactory.getLogger(JSONToolsController.class);

    @GetMapping(path = {"/minify"})
    public String minify(@RequestParam(required=false) Map<String,String> outputFile) {
        try {
            String file = Files.readString(Path.of("src/main/resources/json/" + outputFile.get("fileName") + ".json"));
            MinifyJSONTool actObj = new MinifyJSONTool(file);
            return actObj.getMinified();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR encountered";
    }


    @GetMapping(path = {"/prettify"})
    public String prettify(@RequestParam(required=false) Map<String,String> outputFile) {
        try {
            String file = Files.readString(Path.of("src/main/resources/json/" + outputFile.get("fileName") + ".json"));
            MinifyJSONTool actObj = new MinifyJSONTool(file);
            return actObj.getPretty();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR encountered";
    }

    @GetMapping(path = {"/extract/{outputFile}"})
    public String extract(@PathVariable(required=false,name="outputFile") String outputFile,
                          @RequestParam(required=false) Map<String,String> value ) {
//        return value.get("node");

        try {
            String file = Files.readString(Path.of("src/main/resources/json/" + outputFile + ".json"));
//            ObjectMapper objMapper = new ObjectMapper();
//            JsonNode obj = objMapper.readTree(file);
//            String strObj = objMapper.writeValueAsString(obj.get("jsonObject"));

            Vector vec = new Vector();



            if(value.containsKey("extractObjects")) {
                String[] list = value.get("extractObjects").split(",");
                vec.addAll(Arrays.asList(list));
                ExtractJSONTool extractJSONTool = new ExtractJSONTool(file, vec);
                return extractJSONTool.getCleaned();
            }
            else if(value.containsKey("leaveObjects")) {
                String[] list = value.get("leaveObjects").split(",");
                vec.addAll(Arrays.asList(list));
                ExtractJSONTool extractJSONTool = new ExtractJSONTool(file, vec);
                return extractJSONTool.getPretty();
            }
            return outputFile + " " + vec;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR encountered";
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Boolean Upload(@RequestParam(required=false) Map<String,String> fileParam,
                          @RequestBody String inputFile) {
        String tempFileName = (fileParam.get("fileName") == "") ? "file" : fileParam.get("fileName");

        JSONTool objJSON = new JSONTool(inputFile);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(Paths.get("src/main/resources/json/" + tempFileName + ".json").toFile(), objJSON);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

}


