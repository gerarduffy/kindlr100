package com.brunogtavares.minglr.user;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreateUserTest {
    @Test
    public void testMethod(){
        User testUser1 = new User("1" , "TestUsername1", 25, "http://sampleurl1", "This is a fake description1");

        String id = testUser1.getUserId();
        String name = testUser1.getUserName();
        int age = testUser1.getUserAge();
        String image = testUser1.getUserProfileImage();
        String description = testUser1.getUserDescription();

        assertEquals(id, testUser1.getUserId());
        assertEquals(name, testUser1.getUserName());
        assertEquals(age, testUser1.getUserAge());
        assertEquals(image, testUser1.getUserProfileImage());
        assertEquals(description, testUser1.getUserDescription());

    }
}