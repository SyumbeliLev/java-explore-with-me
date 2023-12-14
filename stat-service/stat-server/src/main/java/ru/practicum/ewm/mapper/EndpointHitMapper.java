package ru.practicum.ewm.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.ewm.HitDto;
import ru.practicum.ewm.StatsDto;
import ru.practicum.ewm.entity.EndpointHit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class EndpointHitMapper {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public EndpointHit toEndpointHit(HitDto hitDto) {
        return EndpointHit.builder()
                .app(hitDto.getApp())
                .ip(hitDto.getIp())
                .timestamp(LocalDateTime.parse(hitDto.getTimestamp(), DATE_TIME_FORMATTER))
                .uri(hitDto.getUri())
                .build();
    }

    public StatsDto toViewStatsDto(String app, String uri, long hits) {
        return StatsDto.builder()
                .app(app)
                .uri(uri)
                .hits(hits)
                .build();
    }
}
