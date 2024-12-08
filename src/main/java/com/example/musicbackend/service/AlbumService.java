package com.example.musicbackend.service;

import com.example.musicbackend.model.Album;
import com.example.musicbackend.model.Track;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbum();
    Album saveAlbum(Album album);
    Album findbyName(String name);
    List<Track> getAllTracksInAlbum(Long albumId);
    List<Album> searchByTitle(String keyword);
}
