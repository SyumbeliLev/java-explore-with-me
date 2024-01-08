package ru.practicum.ewm.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.comment.CommentDto;
import ru.practicum.ewm.service.CommentService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping(path = "/comments")
public class CommentPublicController {
    private final CommentService commentService;

    @GetMapping("/{eventId}")
    public List<CommentDto> getRequestListAllCommentsEvent(@PathVariable long eventId,
                                                           @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                                           @RequestParam(defaultValue = "10") @Positive int size) {
        log.info("GET запрос на получение всех комментариев своего события с id = {} ", eventId);
        return commentService.getCommentEvent(eventId, from, size);
    }
}