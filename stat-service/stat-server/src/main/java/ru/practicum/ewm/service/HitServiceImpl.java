package ru.practicum.ewm.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.HitDto;
import ru.practicum.ewm.StatsDto;
import ru.practicum.ewm.mapper.EndpointHitMapper;
import ru.practicum.ewm.repository.EndpointHitRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
class HitServiceImpl implements HitService {

    private final EndpointHitRepository repository;

    @Override
    public HitDto createHit(HitDto dto) {
        repository.save(EndpointHitMapper.toEndpointHit(dto));
        return dto;
    }

    @Override
    public List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        List<StatsDto> stats = new ArrayList<>();

        if (uris.isEmpty()) {
            uris = repository.findAllHitsBetweenDates(start, end);
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        for (String uri : uris) {
            long hits;
            if (unique) {
                hits = repository.findHitsByUriAndUniqueIp(start, end, uri);
            } else {
                hits = repository.findHitsByUriNotUniqueIp(start, end, uri);
            }

            stats.add(EndpointHitMapper.toViewStatsDto("ewm-main-service", uri, hits));
        }
        return stats.stream()
                .sorted(Comparator.comparingLong(x -> -x.getHits()))
                .collect(Collectors.toList());
    }
}
