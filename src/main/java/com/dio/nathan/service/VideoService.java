package com.dio.nathan.service;

import com.dio.nathan.model.Video;

/**
 * Interface that defines the strategy pattern in the Video domain
 **/

public interface VideoService {
    Video startSelenium(String url);
    Iterable<Video> getVideos();
    void create(Video video);
    void update(Long id, Video video);
    void delete(Long id);
    Video read(Long id);
}
