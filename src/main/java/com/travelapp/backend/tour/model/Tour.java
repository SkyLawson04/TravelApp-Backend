package com.travelapp.backend.tour.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document("tour")
public class Tour {
    @Id
    private String id;
    private String destinationId;
    private String imageUrl;
    private String tourDetails;
}
