package ru.practicum.ewm.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.SearchEventParamsAdmin;
import ru.practicum.ewm.dto.request.UpdateEventAdminRequest;
import ru.practicum.ewm.service.EventService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping(path = "/admin/events")
@RequiredArgsConstructor
@Validated
@Slf4j
public class EventAdminController {
    private final EventService eventService;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping
    public List<EventFullDto> searchEvents(@RequestParam(required = false) List<Long> users, @RequestParam(required = false) List<String> states,
                                           @RequestParam(required = false) List<Long> categories, @RequestParam(required = false) String rangeStart,
                                           @RequestParam(required = false) String rangeEnd, @RequestParam(defaultValue = "0") int from,
                                           @RequestParam(defaultValue = "10") int size) {
        log.info("GET запрос на получение списка событий");
        SearchEventParamsAdmin searchEventParamsAdmin = SearchEventParamsAdmin.builder()
                .users(users)
                .categories(categories)
                .rangeStart(rangeStart == null ? null : LocalDateTime.parse(rangeStart, formatter))
                .rangeEnd(rangeEnd == null ? null : LocalDateTime.parse(rangeEnd, formatter))
                .states(states)
                .from(from)
                .size(size)
                .build();
        return eventService.getAllEventFromAdmin(searchEventParamsAdmin);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEventByAdmin(@PathVariable(value = "eventId") @Min(1) Long eventId,
                                           @RequestBody @Valid UpdateEventAdminRequest inputUpdate) {
        log.info("PATCH запрос на обновление списка событий");
        return eventService.updateEventFromAdmin(eventId, inputUpdate);
    }
}