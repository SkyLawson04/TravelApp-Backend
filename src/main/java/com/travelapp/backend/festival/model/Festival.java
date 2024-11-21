package com.travelapp.backend.festival.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@Document("festival")
public class Festival {
    @Id
    private String id;
    private String destinationId;
    private String name;
    private String imageUrl;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startDate;
}
