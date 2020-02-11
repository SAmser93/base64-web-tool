package ru.sem.decoder.Services;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DecoderInterface {
    ResponseEntity<?> run(Map<?,?> params);
}
