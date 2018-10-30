package com.brunogtavares.minglr.cards;

import com.brunogtavares.minglr.user.User;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HasUserLikedTest {
    @Test
    public void testMethod(){
        User testUser1 = new User("1" , "TestUsername1", 25, "http://sampleurl1", "This is a fake description1");
        User testUser2 = new User("2" , "TestUsername2", 45, "http://sampleurl2", "This is a fake description2");
        User testUser3 = new User("3" , "TestUsername3", 30, "http://sampleurl3", "This is a fake description3");

        Card testCard = new Card("true", "false", "Testbook");

        String userLiker1 = testUser1.getUserName();
        String userLiker2 = testUser2.getUserName();
        String userLiker3 = testUser3.getUserName();

        ArrayList<String> likes = new ArrayList<String>();
        likes.add(userLiker1);
        likes.add(userLiker2);
        likes.add(userLiker3);

        testCard.setLikers(likes);

        //return the list of likers from the card
        ArrayList<String> returnedLikes = testCard.getLikers();

        String toFind = testUser2.getUserName();
        Boolean likerFound = false;

        for(int i=0; i<returnedLikes.size(); i++){
            if(returnedLikes.get(i).equals(toFind)){
                likerFound = true;
            }
        }

        assertEquals(likerFound, true);

    }


}