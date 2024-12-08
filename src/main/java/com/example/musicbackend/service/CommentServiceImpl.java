package com.example.musicbackend.service;

import com.example.musicbackend.model.Comment;
import com.example.musicbackend.model.Track;
import com.example.musicbackend.model.User;
import com.example.musicbackend.repository.CommentRepository;
import com.example.musicbackend.repository.TrackRepository;
import com.example.musicbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrackRepository trackRepository;
    @Override
    public Comment saveComment(String content,Long trackId,Long userId){
        Comment comment = new Comment();
        ZoneId vietnamZone = ZoneId.of("Asia/Ho_Chi_Minh");
        ZonedDateTime now = ZonedDateTime.now(vietnamZone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        String formattedTimestamp = now.format(formatter);
        comment.setTimestamp(formattedTimestamp);
        User user = userRepository.findById(userId).orElse(null);
        Track track = trackRepository.findById(trackId).orElse(null);
        comment.setUser(user);
        comment.setTrack(track);
        comment.setContent(content);
        return commentRepository.save(comment);
    }
    @Override
    public List<Comment> getCommentInTrack(Long trackId){
        return commentRepository.getCommentInTrack(trackId);
    }
}
