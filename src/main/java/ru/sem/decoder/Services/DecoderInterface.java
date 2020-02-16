package ru.sem.decoder.Services;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DecoderInterface {
    int BUFFER_SIZE = 32;
    ResponseEntity<?> encode(Map<?,?> params);
    ResponseEntity<?> decode(Map<?,?> params);
}
