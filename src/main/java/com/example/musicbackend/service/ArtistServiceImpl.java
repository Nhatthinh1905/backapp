package com.example.musicbackend.service;

import com.example.musicbackend.model.Artist;
import com.example.musicbackend.model.Track;
import com.example.musicbackend.repository.ArtistRepository;
import com.example.musicbackend.response.TrackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService{
    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public List<Artist> getAllArtist(){
        return artistRepository.findAll();
    }
    @Override
    public Artist findbyName(String name){
        return artistRepository.findByName(name);
    }
    @Override
    public Artist saveArtist(Artist artist){
        return artistRepository.save(artist);
    }
    @Override
    public List<TrackResponse> getAllTracksOfArtist(Long ArtistId){
        Artist artist = artistRepository.findById(ArtistId).orElse(null);
        if(artist != null){
            List<Track> tracks= artist.getTracks();
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
        return Collections.emptyList();
    }
    @Override
    public List<Artist> searchByName(String keyword){
        return artistRepository.searchArtistByName(keyword);
    }
}
