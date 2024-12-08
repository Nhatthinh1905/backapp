package com.example.musicbackend.service;

import com.example.musicbackend.model.Track;
import com.example.musicbackend.response.TrackResponse;

import java.util.List;

public interface TrackService {
    List<TrackResponse> getAllTrack();
    Track saveTrack( Track track);
    void deleteTrack(Long id);
    List<TrackResponse> searchTrackByTitle(String keyword);
}
