package com.example.musicbackend.service;

import com.example.musicbackend.model.Comment;

import java.util.List;

public interface CommentService {
    Comment saveComment(String content,Long trackId,Long userId);
    List<Comment> getCommentInTrack(Long trackId);
}
