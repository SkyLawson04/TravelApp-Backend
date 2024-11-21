package com.travelapp.backend.destination.controller;

import com.travelapp.backend.common.api.ApiResponse;
import com.travelapp.backend.destination.model.Destination;
import com.travelapp.backend.destination.service.DestinationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Destination")
@RequestMapping("/api/v1/destinations")
public class DestinationController {
    private final DestinationService destinationService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllDestinations() {
        try {
            List<Destination> flights = destinationService.findAll();
            return ResponseEntity.ok(ApiResponse.success("Get all destinations successful", flights));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getDestinationById(@RequestParam String id) {
        try {
            Destination destination = destinationService.findById(id);
            return ResponseEntity.ok(ApiResponse.success("Get destination successful", destination));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @GetMapping("/getByKeyword")
    public ResponseEntity<ApiResponse> getDestinationsByKeyword(@RequestParam String keyword) {
        try {
            List<Destination> flights = destinationService.findWithKeyWords(keyword);
            return ResponseEntity.ok(ApiResponse.success("Get destinations by keyword successful", flights));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @GetMapping("/getRecommendations")
    public ResponseEntity<ApiResponse> getRecommendationsByKeyword(@RequestParam String keyword) {
        try {
            List<String> recommendations = destinationService.getSuggestions(keyword);
            return ResponseEntity.ok(ApiResponse.success("Get all recommendations successful", recommendations));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse> filterDestinations(@RequestParam(required = false) String type,
                                                          @RequestParam(required = false) String city,
                                                          @RequestParam(required = false) String ratingRange) {
        try {
            List<Destination> destinations = destinationService.filter(city, type, ratingRange);
            return ResponseEntity.ok(ApiResponse.success("Get all destinations by type successful", destinations));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @GetMapping("/cities")
    public ResponseEntity<ApiResponse> findCities() {
        try {
            List<String> cities = destinationService.getCities();
            return ResponseEntity.ok(ApiResponse.success("Get all cities by type successful", cities));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @GetMapping("/type")
    public ResponseEntity<ApiResponse> getTypes() {
        try {
            List<String> types = destinationService.getTypes();
            return ResponseEntity.ok(ApiResponse.success("Get all types successful", types));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody Destination destination) {
        try {
            destinationService.add(destination);
            return ResponseEntity.ok(ApiResponse.success("Add destination successful"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }
}
