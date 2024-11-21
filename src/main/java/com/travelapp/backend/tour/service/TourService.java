package com.travelapp.backend.tour.service;

import com.travelapp.backend.tour.model.Tour;

import java.util.List;

public interface TourService {
    void addTour(Tour tour);

    List<Tour> getTours(String destinationId);
}
