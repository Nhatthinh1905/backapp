package com.example.musicbackend.service;

import com.example.musicbackend.model.Artist;
import com.example.musicbackend.response.TrackResponse;

import java.util.List;

public interface ArtistService {
    List<Artist> getAllArtist();
    Artist saveArtist(Artist artist);
    Artist findbyName(String name);
    List<TrackResponse> getAllTracksOfArtist(Long ArtistId);
    List<Artist> searchByName(String keyword);
}
