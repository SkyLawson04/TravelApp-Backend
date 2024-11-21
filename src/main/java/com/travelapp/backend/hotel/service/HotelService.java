package com.travelapp.backend.hotel.service;

import com.travelapp.backend.hotel.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HotelService {
    List<Hotel> findAll(String destinationId);
    List<Hotel> findWithFilter(String priceRange, String ratingRange, String destinationId);
    List<Hotel> findWithKeyword(String keyword, String destinationId);

    List<String> getSuggestions(String keyword, String destinationId);

    void addHotel(Hotel hotel);
}
