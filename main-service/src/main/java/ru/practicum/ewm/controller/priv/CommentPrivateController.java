package ru.practicum.ewm.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.comment.CommentDto;
import ru.practicum.ewm.dto.comment.NewCommentDto;
import ru.practicum.ewm.dto.comment.UpdateCommentDto;
import ru.practicum.ewm.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentPrivateController {
    private final CommentService commentService;

    @PostMapping("/users/{userId}/events/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto addComment(@PathVariable long userId,
                                 @PathVariable long eventId,
                                 @Valid @RequestBody NewCommentDto newCommentDto) {
        log.info("POST запрос на добавление комментария: {}", newCommentDto);
        return commentService.createComment(userId, eventId, newCommentDto);
    }

    @PatchMapping("/users/{userId}/{commentId}")
    public CommentDto patchRequestByUser(@PathVariable long userId, @PathVariable long commentId,
                                         @Valid @RequestBody UpdateCommentDto updateCommentDto) {

        log.info("PATCH запрос на обновление пользователем с userId = {}  комментария с commentId = {} ", userId, commentId);
        return commentService.patchByUser(userId, commentId, updateCommentDto);
    }

    @GetMapping("/users/{userId}/comments")
    public List<CommentDto> getRequestListUser(@PathVariable long userId) {
        log.info("GET запрос на получение комментариев пользователя с userId = {} ", userId);
        return commentService.getCommentUser(userId);
    }

    @DeleteMapping("/users/{userId}/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable long userId, @PathVariable long commentId) {
        log.info("DELETE запрос на удаление комментария id = {} пользователем id = {} ", userId, commentId);
        commentService.deleteComment(userId, commentId);
    }

    @GetMapping("/users/{userId}/{commentId}")
    public CommentDto getComment(@PathVariable long userId, @PathVariable long commentId) {
        log.info("GET запрос на получения комментария id = {} пользователем id = {} ", commentId, userId);
        return commentService.getUserCommentByUserAndCommentId(userId, commentId);
    }
}