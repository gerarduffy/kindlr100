package com.brunogtavares.minglr.chat;

import com.brunogtavares.minglr.user.User;
import com.brunogtavares.minglr.matches.Match;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatchedUserChatTest {
    @Test
    public void testMethod(){
        User testUser1 = new User("1" , "TestUsername1", 25, "http://sampleurl1", "This is a fake description1");
        User testUser2 = new User("2" , "TestUsername2", 45, "http://sampleurl2", "This is a fake description2");

        Chat testChatObject = new Chat("testMessage", true);

        Match testMatch = new Match(testUser1.getUserId(), testUser1.getUserName(), testUser1.getUserProfileImage());

        assertEquals("1", testMatch.getUserId());
        assertEquals("TestUsername1", testMatch.getName());
        assertEquals("http://sampleurl1", testMatch.getProfileImageUrl());

        String message = "Hello " + testUser2.getUserName() + " I would like to buy your book.";

        testChatObject.setMessage(message);

        String messageReceived = testChatObject.getMessage();

        assertEquals(message, messageReceived);
    }

}