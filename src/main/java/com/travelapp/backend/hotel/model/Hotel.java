package com.travelapp.backend.hotel.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document("hotel")
public class Hotel {
    @Id
    private String id;

    private String destinationId;
    private String name;
    private String location;
    private String imageUrl;
    private double rating;
    private int price;
}
