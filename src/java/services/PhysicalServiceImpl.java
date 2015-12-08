/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PhysicalDAO;
import dao.PhysicalEntity;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author zdiawara
 */
@Service
public class PhysicalServiceImpl implements PhysicalService{
    
    @Resource
    PhysicalDAO physicalDao;
    
    @Override
    public Long save(PhysicalEntity p) {
        return physicalDao.save(p);
    }

    @Override
    public void update(PhysicalEntity p) {
        physicalDao.update(p);
    }

    @Override
    public void delete(PhysicalEntity p) {
        physicalDao.delete(p);
    }

    @Override
    public PhysicalEntity findByUserId(Long profileId) {
       return physicalDao.findByUserId(profileId);
    }
    
}
