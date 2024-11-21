package com.travelapp.backend.hotel.controller;

import com.travelapp.backend.common.api.ApiResponse;
import com.travelapp.backend.hotel.model.Hotel;
import com.travelapp.backend.hotel.service.HotelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "hotel")
@RequestMapping("/api/v1/hotels")
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllHotels(@RequestParam String destinationId) {
        try {
            List<Hotel> hotels = hotelService.findAll(destinationId);
            return ResponseEntity.ok(ApiResponse.success("Get all hotels successful", hotels));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @GetMapping("/getByKeyword")
    public ResponseEntity<ApiResponse> getHotelsByKeyword(@RequestParam String keyword, @RequestParam String destinationId) {
        try {
            List<Hotel> hotels = hotelService.findWithKeyword(keyword, destinationId);
            return ResponseEntity.ok(ApiResponse.success("Get hotels by keyword successful", hotels));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @GetMapping("/getRecommendations")
    public ResponseEntity<ApiResponse> getRecommendationsByKeyword(@RequestParam String keyword, @RequestParam String destinationId) {
        try {
            List<String> recommendations = hotelService.getSuggestions(keyword, destinationId);
            return ResponseEntity.ok(ApiResponse.success("Get all recommendations successful", recommendations));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @GetMapping("/getByFilter")
    public ResponseEntity<ApiResponse> getHotelsByFilter(@RequestParam(required = false) String priceRange, @RequestParam(required = false) String ratingRange, @RequestParam String destinationId) {
        try {
            List<Hotel> hotels = hotelService.findWithFilter(priceRange, ratingRange, destinationId);
            return ResponseEntity.ok(ApiResponse.success("Get hotels successful", hotels));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody Hotel hotel) {
        try {
            hotelService.addHotel(hotel);
            return ResponseEntity.ok(ApiResponse.success("Add hotel successful"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }
}
