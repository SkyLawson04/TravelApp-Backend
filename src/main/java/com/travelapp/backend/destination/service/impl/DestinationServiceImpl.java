package com.travelapp.backend.destination.service.impl;

import com.travelapp.backend.destination.model.Destination;
import com.travelapp.backend.destination.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {
    private final MongoTemplate mongoTemplate;

    @Override
    public List<Destination> findAll() {
        return mongoTemplate.findAll(Destination.class);
    }

    @Override
    public List<Destination> filter(String city, String type, String ratingRange) {
        Query query = new Query();
        if(type != null) {
            query.addCriteria(Criteria.where("type").is(type));
        }

        if(city != null) {
            query.addCriteria(Criteria.where("city").is(city));
        }

        if(ratingRange != null) {
            String[] parts = ratingRange.split("-");

            double start = Integer.parseInt(parts[0].trim());
            double end = Integer.parseInt(parts[1].trim());

            query.addCriteria(Criteria.where("rating").gte(start).lte(end));
        }

        return mongoTemplate.find(query, Destination.class);
    }

    @Override
    public List<String> getCities() {
        List<Destination> destinations = mongoTemplate.findAll(Destination.class);

        Set<String> cities = new HashSet<>();

        destinations.forEach(destination -> cities.add(destination.getCity()));

        return cities.stream().toList();
    }


    @Override
    public List<String> getTypes() {
        return List.of("Cultural", "Spiritual");
    }


    @Override
    public void add(Destination destination) {
        mongoTemplate.save(destination);
    }

    @Override
    public List<Destination> findWithKeyWords(String keyword) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(".*" + keyword + ".*", "i"));
        return mongoTemplate.find(query, Destination.class);
    }

    @Override
    public List<String> getSuggestions(String keyword) {
        List<Destination> destinations = findWithKeyWords(keyword);

        return destinations.stream().map(Destination::getName).toList();
    }

    @Override
    public Destination findById(String id) {
        return mongoTemplate.findById(id, Destination.class);
    }
}
