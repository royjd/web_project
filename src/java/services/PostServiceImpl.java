/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PostDAO;
import dao.PostEntity;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Karl Lauret
 */
@Service
public class PostServiceImpl implements PostService {

    @Resource
    PostDAO postDao;

    @Override
    public Long createPost(PostEntity p) {
        return postDao.save(p);
    }

    @Override
    public PostEntity findByID(Long postID) {
        return postDao.findByPostId(postID);
    }

}
