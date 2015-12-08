/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.LocalisationDAO;
import dao.LocalisationEntity;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author zdiawara
 */
@Service
public class LocalisationServiceImpl implements LocalisationService {

    @Resource
    LocalisationDAO localisationDao;
    
    @Override
    public Long save(LocalisationEntity l) {
        return localisationDao.save(l);
    }

    @Override
    public void update(LocalisationEntity l) {
        localisationDao.update(l);
    }

    @Override
    public void delete(LocalisationEntity l) {
        localisationDao.delete(l);
    }

    @Override
    public LocalisationEntity findForExperience(Long experienceId) {
        return localisationDao.findForExperience(experienceId);
    }
    
}
