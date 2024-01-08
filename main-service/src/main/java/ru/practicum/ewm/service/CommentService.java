package ru.practicum.ewm.service;


import ru.practicum.ewm.dto.comment.CommentDto;
import ru.practicum.ewm.dto.comment.NewCommentDto;
import ru.practicum.ewm.dto.comment.UpdateCommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long userId, long eventId, NewCommentDto commentDto);

    CommentDto patchByUser(long userId, long commentId, UpdateCommentDto updateCommentDto);

    List<CommentDto> getCommentUser(long userId);

    CommentDto getUserCommentByUserAndCommentId(long userId, long commentId);

    List<CommentDto> getCommentEvent(long eventId, Integer from, Integer size);

    void deleteComment(long userId, long commentId);

    void deleteCommentByAdmin(long commentId);

    List<CommentDto> search(String text, Integer from, Integer size);
}