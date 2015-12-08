/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author zakaridia
 */
public interface ProfileDAO {
    public Long save(ProfileEntity p);
    public void update(ProfileEntity p);
    public void delete(ProfileEntity p);
    public ProfileEntity findByUserId(Long userId);
}
