package com.example.musicbackend.service;

import com.example.musicbackend.model.Playlist;
import com.example.musicbackend.model.Track;

import java.util.List;

public interface PlayListService {
    Playlist savePlaylist(Playlist playlist,Long userId);
    List<Playlist> getAllPlayListbyUser(Long userId);
    Playlist findByName(String name);
    void addTracktoPlaylist(Long trackId,Long playlistId);
    boolean checkIfTrackExistsInPlaylist(Long trackId,Long playlistId);
    void removeTrackfromPlaylist(Long trackId,Long playlistId);
    List<Track> getTracksInPlaylist(Long playlistId);
    void deletePlaylist(Long playlistId);
}
