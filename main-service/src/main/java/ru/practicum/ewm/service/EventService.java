package ru.practicum.ewm.service;


import ru.practicum.ewm.dto.event.*;
import ru.practicum.ewm.dto.request.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EventService {

    List<EventFullDto> getAllEventFromAdmin(SearchEventParamsAdmin searchEventParamsAdmin);

    EventFullDto updateEventFromAdmin(long eventId, UpdateEventAdminRequest inputUpdate);

    List<EventShortDto> getEventsByUserId(long userId, Integer from, Integer size);

    EventFullDto addNewEvent(long userId, NewEventDto input);

    EventFullDto getEventByUserIdAndEventId(long userId, long eventId);

    EventFullDto updateEventByUserIdAndEventId(long userId, long eventId, UpdateEventUserRequest inputUpdate);

    List<ParticipationRequestDto> getAllParticipationRequestsFromEventByOwner(long userId, long eventId);

    EventRequestStatusUpdateResult updateStatusRequest(long userId, long eventId, EventRequestStatusUpdateRequest inputUpdate);

    List<EventShortDto> getAllEventFromPublic(SearchEventParams searchEventParams, HttpServletRequest request);

    EventFullDto getEventById(long eventId, HttpServletRequest request);
}