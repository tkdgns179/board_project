package com.example.board_project.service;

import com.example.board_project.domain.image.Image;
import com.example.board_project.domain.image.ImageRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Getter @Setter
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional
    public void saveImages(String[] images, int id) {
        if (images != null) {
            for(String image : images) {
                imageRepository.insertImage(image, id);
            }
        }
    }
}
