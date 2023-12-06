package ru.practicum.server.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.ViewStatsDto;
import ru.practicum.server.repository.EndpointHitRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.server.mapper.EndpointHitMapper.*;


@Service
@RequiredArgsConstructor
class StatsServiceImpl implements StatsService {

    private final EndpointHitRepository repository;

    @Override
    public EndpointHitDto saveHit(EndpointHitDto dto) {
        repository.save(toEndpointHit(dto));
        return dto;
    }

    @Override
    public List<ViewStatsDto> getStats(String start, String end, List<String> uris, Boolean unique) {
        List<ViewStatsDto> stats = new ArrayList<>();
        if (uris == null || uris.isEmpty()) {
            uris = repository.findAllHitsBetweenDates(toDateFromString(start), toDateFromString(end));
        }
        for (String uri : uris) {
            long hits;
            if (Boolean.TRUE.equals(unique)) {
                hits = repository.findHitsByUriAndUniqueIp(toDateFromString(start), toDateFromString(end), uri);
            } else {
                hits = repository.findHitsByUriNotUniqueIp(toDateFromString(start), toDateFromString(end), uri);
            }

            stats.add(toViewStatsDto("ewm-main-service", uri, hits));
        }
        return stats.stream()
                .sorted(Comparator.comparingLong(x -> -x.getHits()))
                .collect(Collectors.toList());
    }
}
