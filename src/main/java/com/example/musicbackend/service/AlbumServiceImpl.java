package com.example.musicbackend.service;

import com.example.musicbackend.model.Album;
import com.example.musicbackend.model.Track;
import com.example.musicbackend.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService{
    @Autowired
    private AlbumRepository albumRepository;


    @Override
    public List<Album> getAllAlbum(){
        return albumRepository.findAll();
    }
    @Override
    public Album findbyName(String name){
        return albumRepository.findByName(name);
    }

    @Override
    public Album saveAlbum(Album album){
        return albumRepository.save(album);
    }
    @Override
    public List<Track> getAllTracksInAlbum(Long albumId) {
        Album album = albumRepository.findById(albumId).orElse(null);
        if (album != null) {
            return album.getTracks();
        }
        return Collections.emptyList();
    }
    @Override
    public List<Album> searchByTitle(String keyword){
       return albumRepository.searchAlbumByTitle(keyword);
    }
}
