package com.example.musicbackend.repository;

import com.example.musicbackend.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track,Long> {
    @Query("select t from Track t where t.title like %:keyword%")
    List<Track> searchTrackByTitle(String keyword);
}
