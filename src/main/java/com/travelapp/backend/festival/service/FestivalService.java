package com.travelapp.backend.festival.service;

import com.travelapp.backend.festival.model.Festival;

import java.util.List;

public interface FestivalService {
    void add(Festival festival);

    List<Festival> getAll();
}
