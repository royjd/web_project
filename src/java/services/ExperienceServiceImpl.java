/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ExperienceDAO;
import dao.ExperienceEntity;
import dao.LocalisationDAO;
import dao.LocalisationEntity;
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
    
    @Resource
    LocalisationDAO localisationDao;
    
    @Override
    public boolean save(ExperienceEntity e){
        LocalisationEntity localisation = e.getLocalisation();
        Long id = localisationDao.save(localisation);
        localisation.setId(id);   
        experienceDao.save(e);
        return true;
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

    @Override
    public ExperienceEntity findById(Long id) {
        return experienceDao.findById(id);
    }
    
}
