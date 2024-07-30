package com.dio.nathan.service.impl;

import com.dio.nathan.model.Video;
import com.dio.nathan.model.VideoRepository;
import com.dio.nathan.service.SeleniumService;
import com.dio.nathan.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private SeleniumService seleniumService;

    @Override
    public Video startSelenium(String url) {
        seleniumService.setup();
        return seleniumService.scrapLink(url);
    }

    @Override
    public Iterable<Video> getVideos() {
        return videoRepository.findAll();
    }

    @Override
    public void create(Video video) {
        videoRepository.save(video);
    }

    @Override
    public void update(Long id, Video video) {
        Optional<Video> videoOptional = videoRepository.findById(id);
        if (videoOptional.isPresent()) {
            videoRepository.save(video);
        }
    }

    @Override
    public void delete(Long id) {
        videoRepository.deleteById(id);
    }

    @Override
    public Video read(Long id) {
        Optional<Video> video = videoRepository.findById(id);
        return video.orElse(null);
    }
}
