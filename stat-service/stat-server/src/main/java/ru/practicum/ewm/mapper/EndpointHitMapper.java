package ru.practicum.ewm.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.ewm.EndpointHit;
import ru.practicum.ewm.ViewStats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class EndpointHitMapper {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ru.practicum.ewm.entity.EndpointHit toEndpointHit(EndpointHit endpointHit) {
        return ru.practicum.ewm.entity.EndpointHit.builder()
                .app(endpointHit.getApp())
                .ip(endpointHit.getIp())
                .timestamp(LocalDateTime.parse(endpointHit.getTimestamp(), DATE_TIME_FORMATTER))
                .uri(endpointHit.getUri())
                .build();
    }

    public ViewStats toViewStatsDto(String app, String uri, long hits) {
        return ViewStats.builder()
                .app(app)
                .uri(uri)
                .hits(hits)
                .build();
    }
}
