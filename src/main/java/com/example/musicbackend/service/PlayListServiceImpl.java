package com.example.musicbackend.service;

import com.example.musicbackend.model.Playlist;
import com.example.musicbackend.model.Track;
import com.example.musicbackend.model.User;
import com.example.musicbackend.repository.PlaylistRepository;
import com.example.musicbackend.repository.TrackRepository;
import com.example.musicbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayListServiceImpl implements PlayListService{

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrackRepository trackRepository;

    @Override
    public Playlist savePlaylist(Playlist playlist,Long userId){

        User user = userRepository.findById(userId).orElse(null);
        playlist.setUser(user);
        Playlist saved  = playlistRepository.save(playlist);
        user.addPlaylist(saved);
        return saved ;
    }
    @Override
    public List<Playlist> getAllPlayListbyUser(Long userId){

        return playlistRepository.findAllPlaylistbyUserId(userId);
    }
    @Override
    public Playlist findByName(String name){
        return playlistRepository.findByName(name);
    }
    @Override
    public void addTracktoPlaylist(Long trackId,Long playlistId){
        Track track = trackRepository.findById(trackId).orElse(null);
        Playlist playlist = playlistRepository.findById(playlistId).orElse(null);
        playlist.addTrack(track);
        playlistRepository.save(playlist);
    }
    @Override
    public boolean checkIfTrackExistsInPlaylist( Long trackId,Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElse(null);

        if (playlist == null) {
            return false;
        }
        return playlist.getTracks().stream().anyMatch(track -> track.getId().equals(trackId));
    }
    @Override
    public void removeTrackfromPlaylist(Long trackId,Long playlistId){
        Track track = trackRepository.findById(trackId).orElse(null);
        Playlist playlist = playlistRepository.findById(playlistId).orElse(null);
        playlist.removeTrack(track);
        playlistRepository.save(playlist);
    }
    @Override
    public List<Track> getTracksInPlaylist(Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElse(null);
        return playlist.getTracks();
    }
    @Override
    public void deletePlaylist(Long playlistId){
        playlistRepository.deleteById(playlistId);
    }
}
