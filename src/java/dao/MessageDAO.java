/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Karl Lauret
 */
public interface MessageDAO {

    public void save(MessageEntity m);

    public void update(MessageEntity m);

    public void delete(MessageEntity m);
}
