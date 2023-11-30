package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        Blog blog = blogRepository2.findById(blogId).orElse(null);

        image.setBlog(blog);
        imageRepository2.save(image);
        return image;
    }

    public void deleteImage(Integer id){
        Image image = imageRepository2.findById(id).orElse(null);
        if(image != null) imageRepository2.delete(image);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).orElse(null);

        int count = 0;
        if(image != null) {
            String imageDimensions = image.getDimensions();
            String[] partsImage = imageDimensions.split("X");
            String[] partsScreen = screenDimensions.split("X");

            int screenSize = Integer.parseInt(partsScreen[0]) * Integer.parseInt(partsScreen[1]);
            int imageSize = Integer.parseInt(partsImage[0]) * Integer.parseInt(partsImage[1]);

            count = screenSize / imageSize;
        }

        return count;
    }
}
