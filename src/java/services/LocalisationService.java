/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.LocalisationEntity;

/**
 *
 * @author zdiawara
 */
public interface LocalisationService {
    public Long save(LocalisationEntity l);
    public void update(LocalisationEntity l);
    public void delete(LocalisationEntity l);
    public LocalisationEntity findForExperience(Long experienceId );
}
