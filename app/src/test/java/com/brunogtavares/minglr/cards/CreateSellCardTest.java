package com.brunogtavares.minglr.cards;

import com.brunogtavares.minglr.user.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreateSellCardTest {
    @Test
    public void testMethod(){
        User testUser = new User("1" , "TestUsername", 25, "http://sampleurl", "This is a fake description");
        Card testCard = new Card(testUser.getUserId(), "Harry Potter","false", "true", Long.valueOf(100));

        assertEquals("true", testCard.sell);
        assertEquals("false", testCard.exchange);
        assertEquals(Long.valueOf(100), testCard.price);

        assertEquals("1", testCard.userId);
    }

}