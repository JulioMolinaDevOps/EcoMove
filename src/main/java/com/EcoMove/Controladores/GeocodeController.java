
package com.EcoMove.Controladores;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/geocode")
public class GeocodeController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public ResponseEntity<Object> geocode(@RequestParam String address) {
    try {
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String url = "https://nominatim.openstreetmap.org/search?format=json&q=" + encodedAddress;

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "EcoMoveApp/1.0 (tu-email@example.com)");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
        return ResponseEntity.ok(response.getBody());
    } catch (Exception e) {
        e.printStackTrace(); // log real del error
        return ResponseEntity.status(500).body("Error en geocodificación: " + e.getMessage());
    }
}

    
}