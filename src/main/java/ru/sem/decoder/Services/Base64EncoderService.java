package ru.sem.decoder.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Base64EncoderService implements DecoderInterface {

    private Logger logger = LogManager.getLogger(Base64EncoderService.class);

    @Override
    public ResponseEntity<?> run(Map<?, ?> params){
        JSONObject body = new JSONObject(params.get("body").toString());
        String toEncode = body.get("data").toString();
        JSONObject response = new JSONObject();
        try{
            long startTime = System.currentTimeMillis();
            logger.info("String before base64: {}", toEncode);

            // Convert to base64
            String encoded = java.util.Base64.getEncoder().encodeToString(toEncode.getBytes());
            response.put("resultData",encoded);
            response.put("elapsedTime",System.currentTimeMillis()-startTime);
            logger.info("final response: {}",response);
            return new ResponseEntity<Object>(response.toString(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.put("errorMessage",e.getMessage());
            logger.info("final error: {}",response);
            return new ResponseEntity<Object>(response.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
