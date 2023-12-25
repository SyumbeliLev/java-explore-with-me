package ru.practicum.ewm.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.ewm.EndpointHitDto;
import ru.practicum.ewm.ViewStats;
import ru.practicum.ewm.entity.EndpointHit;


@UtilityClass
public class EndpointHitMapper {
    public EndpointHit toEndpointHit(EndpointHitDto endpointHit) {
        return EndpointHit.builder()
                .app(endpointHit.getApp())
                .ip(endpointHit.getIp())
                .timestamp(endpointHit.getTimestamp())
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
