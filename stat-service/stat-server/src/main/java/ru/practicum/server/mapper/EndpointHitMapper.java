package ru.practicum.server.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.ViewStatsDto;
import ru.practicum.server.model.EndpointHit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class EndpointHitMapper {
    public EndpointHit toEndpointHit(EndpointHitDto hitDto) {
        return EndpointHit.builder()
                .app(hitDto.getApp())
                .ip(hitDto.getIp())
                .timestamp(toDateFromString(hitDto.getTimestamp()))
                .uri(hitDto.getUri())
                .build();
    }

    public LocalDateTime toDateFromString(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public ViewStatsDto toViewStatsDto(String app, String uri, long hits) {
        return ViewStatsDto.builder()
                .app(app)
                .uri(uri)
                .hits(hits)
                .build();
    }
}
