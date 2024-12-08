package com.example.musicbackend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrackResponse {
    private Long id;
    private String title;
    private String mp3Url;
    private String imageUrl;
    private String artist;

    public TrackResponse(Long id, String title, String mp3Url,String imageUrl) {
        super();
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.mp3Url = mp3Url;
    }
}
