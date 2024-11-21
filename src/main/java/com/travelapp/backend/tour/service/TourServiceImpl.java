package com.travelapp.backend.tour.service;

import com.travelapp.backend.tour.model.Tour;
import com.travelapp.backend.tour.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
    private TourRepository tourRepository;

    @Override
    public void addTour(Tour tour) {
        tourRepository.save(tour);
    }

    @Override
    public List<Tour> getTours(String destinationId) {
        return tourRepository.findByDestinationId(destinationId);
    }
}
