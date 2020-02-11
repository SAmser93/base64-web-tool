package ru.sem.decoder.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@Service
public class GZIPBase64DecoderService implements DecoderInterface {

    private Logger logger = LogManager.getLogger(GZIPBase64DecoderService.class);

    @Override
    public ResponseEntity<?> run(Map<?, ?> params){
        JSONObject body = new JSONObject(params.get("body").toString());
        String toDecode = body.get("data").toString();
        JSONObject response = new JSONObject();
        try{
            long startTime = System.currentTimeMillis();
            // get the bytes for the compressed string

            //Если вдруг попадётся иная реализация BASE64


            byte[] compressed = toDecode.getBytes(StandardCharsets.UTF_8);

            // convert the bytes from base64 to normal string
            Base64.Decoder d = Base64.getDecoder();
            compressed = d.decode(compressed);

            // decode.
            final int BUFFER_SIZE = 32;

            ByteArrayInputStream is = new ByteArrayInputStream(compressed);

            GZIPInputStream gis = new GZIPInputStream(is, BUFFER_SIZE);

            StringBuilder string = new StringBuilder();

            byte[] data = new byte[BUFFER_SIZE];

            int bytesRead;

            while ((bytesRead = gis.read(data)) != -1)
            {
                string.append(new String(data, 0, bytesRead));
            }
            gis.close();
            is.close();
            long endTime = System.currentTimeMillis();
            response.put("resultData",string);
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
