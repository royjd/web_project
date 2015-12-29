/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Karl Lauret
 */
public interface PostDAO {

    public Long save(PostEntity p);

    public void update(PostEntity p);

    public void delete(PostEntity p);

    public PostEntity findByPostId(Long postId);

}