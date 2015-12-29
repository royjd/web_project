/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PostEntity;

/**
 *
 * @author Karl Lauret
 */
public interface PostService {

    public Long createPost(PostEntity p);

    public PostEntity findByID(Long postID);
    
}
