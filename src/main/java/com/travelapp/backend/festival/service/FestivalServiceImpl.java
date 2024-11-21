package com.travelapp.backend.festival.service;

import com.travelapp.backend.festival.model.Festival;
import com.travelapp.backend.festival.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService{
    private final FestivalRepository festivalRepository;


    @Override
    public void add(Festival festival) {
        festivalRepository.save(festival);
    }

    @Override
    public List<Festival> getAll() {
        return festivalRepository.findAll();
    }
}
