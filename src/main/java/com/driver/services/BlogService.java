package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    @Autowired
    ImageService imageService;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        Date date = new Date();
        blog.setPubDate(date);

        User user = userRepository1.findById(userId).orElse(null);
        blog.setUser(user);

        blogRepository1.save(blog);
        return blog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images

        Blog blog = blogRepository1.findById(blogId).orElse(null);

        if(blog != null) {
            List<Image> images = blog.getImageList();
            blogRepository1.delete(blog);
            for(Image i: images) {
                imageService.deleteImage(i.getId());
            }
        }
    }
}
