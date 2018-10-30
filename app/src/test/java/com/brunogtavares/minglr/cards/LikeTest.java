package com.brunogtavares.minglr.cards;

import com.brunogtavares.minglr.user.User;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LikeTest {

    @Test
    public void testMethod(){
        User testUser = new User("1" , "TestUsername", 25, "http://sampleurl", "This is a fake description");
        Card testCard = new Card("true", "false", "Testbook");

        String userLiker = testUser.getUserName();

        ArrayList<String> likes = new ArrayList<String>();
        likes.add(userLiker);

        testCard.setLikers(likes);

        assertEquals("TestUsername", testCard.getLikers().get(0));

    }

}