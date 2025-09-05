
package com.EcoMove.Controladores;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/geocode")
public class GeocodeController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public ResponseEntity<Object> geocode(@RequestParam String address) {
        String url = "https://nominatim.openstreetmap.org/search?format=json&q=" + address;
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error en geocodificación");
        }
    }
    
}