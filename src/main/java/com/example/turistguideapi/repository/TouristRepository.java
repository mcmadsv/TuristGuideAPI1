package com.example.turistguideapi.repository;

import com.example.turistguideapi.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository() {
        attractions.add(new TouristAttraction("Tivoli", "Forlystelsespark midt i København centrum"));
        attractions.add(new TouristAttraction("Den Lille Havfrue", "Berømt statue i København"));
    }

    public List<TouristAttraction> getAllAttractions() {
        return attractions;
    }

    public Optional<TouristAttraction> getAttractionByName(String name) {
        return attractions.stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public void addAttraction(TouristAttraction attraction) {
        attractions.add(attraction);
    }

    public boolean updateAttraction(String name, TouristAttraction updatedAttraction) {
        return getAttractionByName(name)
                .map(a -> {
                    a.setDescription(updatedAttraction.getDescription());
                    return true;
                })
                .orElse(false);
    }

    public boolean deleteAttraction(String name) {
        return attractions.removeIf(a -> a.getName().equalsIgnoreCase(name));
    }
}