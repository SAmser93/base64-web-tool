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
import ru.sem.decoder.Services.Base64Service;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest/base64")
public class Base64ServiceController {

    private Logger logger;

    private Base64Service base64Service;

    @Autowired
    Base64ServiceController(){
        this.logger = LogManager.getLogger(Base64ServiceController.class);
        base64Service = new Base64Service();
    }

    @RequestMapping(value = "/decode",  method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =  MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> decodeGzip(@RequestBody String body) {
        logger.info("decodeGzip got body: "+body);
        Map<String,String> params = new HashMap<>();
        params.put("body",body);
        return base64Service.decode(params);
    }

    @RequestMapping(value = "/encode",  method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =  MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> encodeGzip(@RequestBody String body) {
        logger.info("encodeGzip got body: "+body);
        Map<String,String> params = new HashMap<>();
        params.put("body",body);
        return base64Service.encode(params);
    }

}

