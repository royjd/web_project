/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ExperienceDAO;
import dao.ExperienceEntity;
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

    @Resource
    ExperienceDAO experienceDao;

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

    @Override
    public ExperienceEntity getLastExperienceByUser(Long userID) {
        return this.experienceDao.findLastExperienceForUser(userID);
    }

    @Override
    public ExperienceEntity getLastExperienceByProfile(Long profileID) {
        return this.experienceDao.findLastExperienceForProfile(profileID);
    }

}
