/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ExperienceEntity;
import java.util.List;

/**
 *
 * @author zakaridia
 */
public interface ExperienceService {
    public boolean save(ExperienceEntity e);
    public void update(ExperienceEntity e);
    public void delete(ExperienceEntity e);
    public ExperienceEntity findById(Long id);
    public List<ExperienceEntity> findExperiencesForProfil(Long profileId);
    
}
