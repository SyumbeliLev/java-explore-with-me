package ru.practicum.ewm.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventShortDto;
import ru.practicum.ewm.dto.event.SearchEventParams;
import ru.practicum.ewm.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/events")
public class EventPublicController {
    private final EventService eventService;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping
    public List<EventShortDto> getAllEvents(@RequestParam(required = false) String text, @RequestParam(required = false) List<Long> categories,
                                            @RequestParam(required = false) Boolean paid, @RequestParam(required = false) String rangeStart,
                                            @RequestParam(required = false) String rangeEnd, @RequestParam(required = false) Boolean onlyAvailable,
                                            @RequestParam(required = false) String sort, @RequestParam(defaultValue = "0") int from,
                                            @RequestParam(defaultValue = "10") int size,
                                            HttpServletRequest request) {
        log.info("GET запрос на получения событий с фильтром");

        SearchEventParams searchEventParams = SearchEventParams.builder()
                .text(text)
                .categories(categories)
                .paid(paid)
                .rangeStart(rangeStart == null ? null : LocalDateTime.parse(rangeStart, formatter))
                .rangeEnd(rangeEnd == null ? null : LocalDateTime.parse(rangeEnd, formatter))
                .onlyAvailable(onlyAvailable)
                .sort(sort)
                .from(from)
                .size(size)
                .build();
        return eventService.getAllEventFromPublic(searchEventParams, request);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventById(@PathVariable(value = "eventId") @Min(1) Long eventId,
                                     HttpServletRequest request) {
        log.info("GET запрос на получения полной информации о событии с  id= {}", eventId);
        return eventService.getEventById(eventId, request);
    }
}