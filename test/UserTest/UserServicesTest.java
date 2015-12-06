/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserTest;

import dao.UserEntity;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import services.UserService;
import services.UserServiceImpl;

/**
 *
 * @author Karl Lauret
 */
public class UserServicesTest {

    private UserService us;
    private String username1;
    private UserEntity user1;

    public UserServicesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        this.us = new UserServiceImpl();
        this.username1 = "Karl";
    }

    @After
    public void tearDown() {
        //this.us.delete(this.user1);
    }

    @Test
    public void userAddTest() {
        //assertTrue(this.us.add(this.username1));
        //this.user1 = this.us.findByUsername(this.username1);
        //assertTrue(this.user1!=null);
        //assertEquals(this.user1.getId(), this.us.findByID(this.user1.getId()));

    }
}
