package com.travelapp.backend.festival.repository;

import com.travelapp.backend.festival.model.Festival;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FestivalRepository extends MongoRepository<Festival, String> {
}
