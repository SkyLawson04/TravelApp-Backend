package com.travelapp.backend.festival.controller;

import com.travelapp.backend.common.api.ApiResponse;
import com.travelapp.backend.destination.model.Destination;
import com.travelapp.backend.festival.model.Festival;
import com.travelapp.backend.festival.service.FestivalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Festival")
@RequestMapping("/api/v1/festivals")
public class FestivalController {
    private final FestivalService festivalService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllFestivals() {
        try {
            List<Festival> flights = festivalService.getAll();
            return ResponseEntity.ok(ApiResponse.success("Get all festivals successful", flights));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody Festival festival) {
        try {
            festivalService.add(festival);
            return ResponseEntity.ok(ApiResponse.success("Add festival successful"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }
}
