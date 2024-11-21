package com.travelapp.backend.tour.repository;

import com.travelapp.backend.tour.model.Tour;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TourRepository extends MongoRepository<Tour, String> {
    List<Tour> findByDestinationId(String destinationId);
}
