/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PhysicalEntity;

/**
 *
 * @author zdiawara
 */
public interface PhysicalService {
    public Long save(PhysicalEntity p);
    public void update(PhysicalEntity p);
    public void delete(PhysicalEntity p);
    public PhysicalEntity findByUserId(Long profileId);
}
