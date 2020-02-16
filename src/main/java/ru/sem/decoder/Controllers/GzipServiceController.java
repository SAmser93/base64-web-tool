package ru.sem.decoder.Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sem.decoder.Services.GzipService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest/gzip")
public class GzipServiceController {

    private Logger logger;

    private GzipService gzipService;

    @Autowired
    GzipServiceController(){
        this.logger = LogManager.getLogger(GzipServiceController.class);
        gzipService = new GzipService();
    }

    @RequestMapping(value = "/decode",  method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =  MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> decodeGzip(@RequestBody String body) {
        logger.info("decodeGzip got body: "+body);
        Map<String,String> params = new HashMap<>();
        params.put("body",body);
        return gzipService.decode(params);
    }

    @RequestMapping(value = "/encode",  method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =  MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> encodeGzip(@RequestBody String body) {
        logger.info("encodeGzip got body: "+body);
        Map<String,String> params = new HashMap<>();
        params.put("body",body);
        return gzipService.encode(params);
    }
}

