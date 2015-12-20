/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ProfileDAO;
import dao.ProfileEntity;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author zakaridia
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    @Resource
    ProfileDAO profileDao;
    
    @Override
    public Long save(ProfileEntity p) {
        return profileDao.save(p);
    }

    @Override
    public void update(ProfileEntity p) {
        profileDao.update(p);
    }

    @Override
    public ProfileEntity findByUserId(Long userId) {
        return profileDao.findByUserId(userId);
    }

    @Override
    public void delete(ProfileEntity p) {
        profileDao.delete(p);
    }
    
}
