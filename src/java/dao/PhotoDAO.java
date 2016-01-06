/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author zakaridia
 */
public interface PhotoDAO {
    public Long save(PhotoEntity photo);
    public void update(PhotoEntity photo);
    public PhotoEntity find(Long id);
    public List<PhotoEntity> find(int limit);
    public List<PhotoEntity> findAll();
    public void delete(PhotoEntity photo);
}
