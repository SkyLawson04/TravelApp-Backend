package com.travelapp.backend.destination.repository;

import com.travelapp.backend.destination.model.Destination;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DestinationRepository extends MongoRepository<Destination, String> {
}
