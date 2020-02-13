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
import ru.sem.decoder.Services.Base64DecoderService;
import ru.sem.decoder.Services.Base64EncoderService;
import ru.sem.decoder.Services.GZIPBase64DecoderService;
import ru.sem.decoder.Services.GZIPBase64EncoderService;

import java.util.HashMap;
import java.util.Map;

/*
    TODO:
    1. Add GZIP to front
    2. README
 */

@RestController
@RequestMapping("/rest")
public class DecoderController {

    private Logger logger;

    private GZIPBase64DecoderService GB64DecoderService;
    private GZIPBase64EncoderService GB64EncoderService;
    private Base64DecoderService decoderService;
    private Base64EncoderService encoderService;

    @Autowired
    DecoderController(){
        this.logger = LogManager.getLogger(DecoderController.class);
        GB64DecoderService = new GZIPBase64DecoderService();
        GB64EncoderService = new GZIPBase64EncoderService();
        decoderService = new Base64DecoderService();
        encoderService = new Base64EncoderService();
    }

    @RequestMapping(value = "/decodeGzip",  method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =  MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> decodeGzip(@RequestBody String body) {
        logger.info("decodeGzip got body: "+body);
        Map<String,String> params = new HashMap<>();
        params.put("body",body);
        return GB64DecoderService.run(params);
    }

    @RequestMapping(value = "/encodeGzip",  method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =  MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> encodeGzip(@RequestBody String body) {
        logger.info("encodeGzip got body: "+body);
        Map<String,String> params = new HashMap<>();
        params.put("body",body);
        return GB64EncoderService.run(params);
    }

    @RequestMapping(value = "/decodeBase64",  method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =  MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> decodeBase64(@RequestBody String body) {
        logger.info("decodeBase64 got body: "+body);
        Map<String,String> params = new HashMap<>();
        params.put("body",body);
        return decoderService.run(params);
    }

    @RequestMapping(value = "/encodeBase64",  method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =  MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> encodeBase64(@RequestBody String body) {
        logger.info("encodeBase64 got body: "+body);
        Map<String,String> params = new HashMap<>();
        params.put("body",body);
        return encoderService.run(params);
    }
}

