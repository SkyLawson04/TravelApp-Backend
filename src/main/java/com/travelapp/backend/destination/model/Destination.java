package com.travelapp.backend.destination.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Document("destination")
public class Destination {
    @Id
    private String id;

    private String name;
    private String city;
    private String location;
    private String description;
    private String culturalValue;
    private double rating;
    private List<String> imageUrl;
    private String type;
}
