package com.travelapp.backend.tour.controller;

import com.travelapp.backend.common.api.ApiResponse;
import com.travelapp.backend.hotel.model.Hotel;
import com.travelapp.backend.tour.model.Tour;
import com.travelapp.backend.tour.service.TourService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Tour")
@RequestMapping("/api/v1/tours")
public class TourController {
    private TourService tourService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTours(@RequestParam String destinationId) {
        try {
            List<Tour> tours = tourService.getTours(destinationId);
            return ResponseEntity.ok(ApiResponse.success("Get all tours successful"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody Tour tour) {
        try {
            tourService.addTour(tour);
            return ResponseEntity.ok(ApiResponse.success("Add tour successful"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }
}
