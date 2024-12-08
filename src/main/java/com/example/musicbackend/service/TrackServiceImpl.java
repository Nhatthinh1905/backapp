package com.example.musicbackend.service;

import com.example.musicbackend.model.Track;
import com.example.musicbackend.repository.TrackRepository;
import com.example.musicbackend.response.TrackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrackServiceImpl implements TrackService{
    @Autowired
    private TrackRepository trackRepository;


    @Override
    public List<TrackResponse> getAllTrack() {
        List<Track> tracks = trackRepository.findAll();
        List<TrackResponse> trackResponses = new ArrayList<>();

        for (Track track : tracks) {
            TrackResponse trackResponse = new TrackResponse(
                    track.getId(),
                    track.getTitle(),
                    track.getMp3Url(),
                    track.getImageUrl(),
                    track.getArtist().getName()
            );
            trackResponses.add(trackResponse);
        }

        return trackResponses;
    }
    @Override
    public List<TrackResponse> searchTrackByTitle(String keyword){
        List<Track> tracks = trackRepository.searchTrackByTitle(keyword);
        List<TrackResponse> trackResponses = new ArrayList<>();

        for (Track track : tracks) {
            TrackResponse trackResponse = new TrackResponse(
                    track.getId(),
                    track.getTitle(),
                    track.getMp3Url(),
                    track.getImageUrl(),
                    track.getArtist().getName()
            );
            trackResponses.add(trackResponse);
        }

        return trackResponses;
    }

    @Override
    public Track saveTrack( Track track) {
        return trackRepository.save(track);
    }
    @Override
    public void deleteTrack(Long id){
        trackRepository.deleteById(id);
    }
}
