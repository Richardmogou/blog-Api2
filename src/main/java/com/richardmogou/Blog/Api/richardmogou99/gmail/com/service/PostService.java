package com.richardmogou.Blog.Api.richardmogou99.gmail.com.service;

import com.richardmogou.Blog.Api.richardmogou99.gmail.com.dto.PostDTO;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.dto.PostResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface PostService {
    PostDTO creatPost(PostDTO postDTO);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getPostbyId(long Id);

    PostDTO UpdatePostById(long id, PostDTO postDTO);

    void deletePost(long id);

}
