/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ExperienceDAO;
import dao.ExperienceEntity;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author zakaridia
 */
@Service
public class ExperienceServiceImpl implements ExperienceService {
    @Resource
    ExperienceDAO experienceDao;
    
    @Override
    public Long save(ExperienceEntity e){
        return experienceDao.save(e);
    }

    @Override
    public List<ExperienceEntity> findExperiencesForProfil(Long profileId) {
        return experienceDao.findExperiencesForProfil(profileId);
    }

    @Override
    public void update(ExperienceEntity e) {
        experienceDao.update(e);
    }

    @Override
    public void delete(ExperienceEntity e){
        experienceDao.delete(e);
    }
    
}
