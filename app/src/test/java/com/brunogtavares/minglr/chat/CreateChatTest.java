package com.brunogtavares.minglr.chat;

import com.brunogtavares.minglr.user.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreateChatTest {
    @Test
    public void testMethod(){
        User testUser1 = new User("1" , "TestUsername1", 25, "http://sampleurl1", "This is a fake description1");
        User testUser2 = new User("2" , "TestUsername2", 45, "http://sampleurl2", "This is a fake description2");

        Chat testChatObject = new Chat("testMessage", true);

        assertEquals("testMessage", testChatObject.getMessage());
        assertEquals(true, testChatObject.getCurrentUser());

        Chat testChatObject2 = new Chat("testMessage2", true, testUser1.getUserProfileImage());

        assertEquals("testMessage2", testChatObject2.getMessage());
        assertEquals(true, testChatObject2.getCurrentUser());
        assertEquals("http://sampleurl1", testChatObject2.getImageUrl());


    }

}