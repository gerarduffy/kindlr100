package com.brunogtavares.minglr.chat;

import com.brunogtavares.minglr.user.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class SendMessageTest {
    @Test
    public void testMethod(){
        User testUser1 = new User("1" , "TestUsername1", 25, "http://sampleurl1", "This is a fake description1");
        User testUser2 = new User("2" , "TestUsername2", 45, "http://sampleurl2", "This is a fake description2");

        Chat testChatObject = new Chat("This is the test message that we send", true);

        String newMessage = "Hi, my name is " + testUser1.getUserName();

        testChatObject.setMessage(newMessage);

        assertEquals(newMessage, testChatObject.getMessage());
    }

}