package com.brunogtavares.minglr.chat;

import com.brunogtavares.minglr.user.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReceiveMessageTest {
    @Test
    public void testMethod(){
        User testUser1 = new User("1" , "TestUsername1", 25, "http://sampleurl1", "This is a fake description1");
        User testUser2 = new User("2" , "TestUsername2", 45, "http://sampleurl2", "This is a fake description2");

        Chat testChatObject = new Chat("testMessage", true);

        assertEquals("testMessage", testChatObject.getMessage());
        assertEquals(true, testChatObject.getCurrentUser());

        String toBeReceived = "Hello " + testUser2.getUserName();

        testChatObject.setMessage(toBeReceived);

        String received = testChatObject.getMessage();

        assertEquals(toBeReceived,received);
        assertEquals(true, testChatObject.getCurrentUser());
    }

}