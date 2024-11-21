package com.travelapp.backend.hotel.service;

import com.travelapp.backend.destination.model.Destination;
import com.travelapp.backend.hotel.model.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final MongoTemplate mongoTemplate;

    @Override
    public List<Hotel> findAll(String destinationId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("destinationId").is(destinationId));
        return mongoTemplate.find(query, Hotel.class);
    }

    @Override
    public List<Hotel> findWithFilter(String priceRange, String ratingRange, String destinationId) {
        List<Hotel> hotels;

        Query query = new Query();

        if(priceRange != null) {
            String[] parts = priceRange.split("-");

            int start = Integer.parseInt(parts[0].trim());
            int end = Integer.parseInt(parts[1].trim());

            query.addCriteria(Criteria.where("price").gte(start).lte(end));
        }

        if(ratingRange != null) {
            String[] parts = ratingRange.split("-");

            double start = Integer.parseInt(parts[0].trim());
            double end = Integer.parseInt(parts[1].trim());

            query.addCriteria(Criteria.where("rating").gte(start).lte(end));
        }
        query.addCriteria(Criteria.where("destinationId").is(destinationId));

        hotels = mongoTemplate.find(query, Hotel.class);
        return hotels;
    }

    @Override
    public List<Hotel> findWithKeyword(String keyword, String destinationId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("destinationId").is(destinationId));
        query.addCriteria(Criteria.where("name").regex(".*" + keyword + ".*", "i"));

        return mongoTemplate.find(query, Hotel.class);
    }

    @Override
    public List<String> getSuggestions(String keyword, String destinationId) {
        List<Hotel> hotels = findWithKeyword(keyword, destinationId);

        return hotels.stream().map(Hotel::getName).toList();
    }

    @Override
    public void addHotel(Hotel hotel) {
        mongoTemplate.save(hotel);
    }
}
