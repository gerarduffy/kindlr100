package com.brunogtavares.minglr.matches;

import com.brunogtavares.minglr.chat.Chat;
import com.brunogtavares.minglr.user.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreateMatchTest {
    @Test
    public void testMethod(){
        User testUser1 = new User("1" , "TestUsername1", 25, "http://sampleurl1", "This is a fake description1");
        User testUser2 = new User("2" , "TestUsername2", 45, "http://sampleurl2", "This is a fake description2");

        //Chat testChatObject = new Chat("testMessage", true);

        Match testMatch = new Match(testUser1.getUserId(), testUser1.getUserName(), testUser1.getUserProfileImage());

        //testing the match
        assertEquals("1", testMatch.getUserId());
        assertEquals("TestUsername1", testMatch.getName());
        assertEquals("http://sampleurl1", testMatch.getProfileImageUrl());
    }

}