package ru.sem.decoder.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Map;

@Service
public class Base64DecoderService implements DecoderInterface {

    private Logger logger = LogManager.getLogger(Base64DecoderService.class);

    @Override
    public ResponseEntity<?> run(Map<?, ?> params){
        JSONObject body = new JSONObject(params.get("body").toString());
        String toDecode = body.get("data").toString();
        JSONObject response = new JSONObject();
        try{
            long startTime = System.currentTimeMillis();
            logger.info("Encoded string: {}", toDecode);
            byte[] decodedBytes = Base64.getDecoder().decode(toDecode);
            String decodedString = new String(decodedBytes);
            response.put("resultData",decodedString);
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
