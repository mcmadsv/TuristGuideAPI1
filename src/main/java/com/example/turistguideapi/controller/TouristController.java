package com.example.turistguideapi.controller;

import com.example.turistguideapi.model.TouristAttraction;
import com.example.turistguideapi.service.TouristService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attractions")
public class TouristController {
    private final TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TouristAttraction>> getAllAttractions() {
        return ResponseEntity.ok(service.getAllAttractions());
    }

    @GetMapping("/{name}")
    public ResponseEntity<TouristAttraction> getAttractionByName(@PathVariable String name) {
        return service.getAttractionByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> addAttraction(@RequestBody TouristAttraction attraction) {
        service.addAttraction(attraction);
        return ResponseEntity.ok("Attraktion tilføjet");
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> updateAttraction(@PathVariable String name, @RequestBody TouristAttraction attraction) {
        return service.updateAttraction(name, attraction) ?
                ResponseEntity.ok("Attraktion opdateret") :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteAttraction(@PathVariable String name) {
        return service.deleteAttraction(name) ?
                ResponseEntity.ok("Attraktion slettet") :
                ResponseEntity.notFound().build();
    }
}
