package com.brunogtavares.minglr.cards;

import com.brunogtavares.minglr.user.User;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DislikeTest {
    @Test
    public void testMethod(){
        User testUser = new User("1" , "TestUsername", 25, "http://sampleurl", "This is a fake description");
        Card testCard = new Card("true", "false", "Testbook");

        String userDisliker = testUser.getUserName();

        ArrayList<String> dislikes = new ArrayList<String>();
        dislikes.add(userDisliker);

        testCard.setDislikers(dislikes);

        assertEquals("TestUsername", testCard.getDislikers().get(0));


    }

}