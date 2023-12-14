package ru.practicum.ewm.service;


import ru.practicum.ewm.HitDto;
import ru.practicum.ewm.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface HitService {

    HitDto createHit(HitDto dto);

    List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);

}
