package ru.sem.decoder.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

@Service
public class GZIPBase64EncoderService implements DecoderInterface {

    private Logger logger = LogManager.getLogger(GZIPBase64EncoderService.class);

    @Override
    public ResponseEntity<?> run(Map<?, ?> params){
        JSONObject body = new JSONObject(params.get("body").toString());
        String toEnecode = body.get("data").toString();
        JSONObject response = new JSONObject();
        try{
            long startTime = System.currentTimeMillis();

            ByteArrayOutputStream bos = new ByteArrayOutputStream(toEnecode.length());
            GZIPOutputStream gzip = new GZIPOutputStream(bos);

            // Compress the input string
            gzip.write(toEnecode.getBytes());
            gzip.close();
            byte[] compressed = bos.toByteArray();
            bos.close();

            logger.info("compressed before base64: {}",new String(compressed));

            // Convert to base64
            compressed = java.util.Base64.getEncoder().encode(compressed);

            long endTime = System.currentTimeMillis();
            response.put("resultData",new String(compressed));
            response.put("elapsedTime",endTime-startTime);
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
