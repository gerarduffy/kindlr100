package com.brunogtavares.minglr.cards;

import com.brunogtavares.minglr.user.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreateExchangeCardTest {
    @Test
    public void testMethod(){
        User testUser = new User("1" , "TestUsername", 25, "http://sampleurl", "This is a fake description");
        Card testCard = new Card("true", "false", "Testbook");

        String exchangeTest = testCard.exchange;

        assertEquals("true", exchangeTest);
        assertEquals("Testbook", testCard.title);
    }

}