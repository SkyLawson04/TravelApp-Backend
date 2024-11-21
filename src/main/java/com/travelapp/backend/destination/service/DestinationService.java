package com.travelapp.backend.destination.service;

import com.travelapp.backend.destination.model.Destination;

import java.util.List;

public interface DestinationService {
    List<Destination> findAll();

    List<String> getTypes();

    void add(Destination destination);

    List<Destination> findWithKeyWords(String keyword);

    List<String> getSuggestions(String keyword);

    Destination findById(String id);

    List<Destination> filter(String city, String type, String priceRange);

    List<String> getCities();
}
