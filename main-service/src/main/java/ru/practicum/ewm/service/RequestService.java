package ru.practicum.ewm.service;


import ru.practicum.ewm.dto.request.ParticipationRequestDto;

import java.util.List;

public interface RequestService {
    ParticipationRequestDto addNewRequest(long userId, long eventId);

    List<ParticipationRequestDto> getRequestsByUserId(long userId);

    ParticipationRequestDto cancelRequest(long userId, long requestId);
}