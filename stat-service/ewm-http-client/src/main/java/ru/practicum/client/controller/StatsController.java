package ru.practicum.client.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.client.client.StatsClient;
import ru.practicum.dto.EndpointHitDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class StatsController {

    private final StatsClient statsClient;

    @PostMapping("/hit")
    public ResponseEntity<Object> createHit(@RequestBody EndpointHitDto hitDto) {
        if (hitDto.getIp() == null || hitDto.getIp()
                .isEmpty()) {
            throw new IllegalArgumentException("Ip is null!");
        }
        log.debug("Gateway: creating hit");
        return statsClient.createHit(hitDto);
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam String start, @RequestParam String end,
                                           @RequestParam(required = false) List<String> uris, @RequestParam(required = false) Boolean unique) {

        LocalDateTime startDate = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endDate = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if (startDate.isAfter(endDate) || endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("The date is incorrect!");
        }
        log.debug("Gateway: getting stats");
        return statsClient.getStats(start, end, uris, unique);
    }
}
