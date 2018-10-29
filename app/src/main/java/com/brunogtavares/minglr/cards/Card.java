package com.brunogtavares.minglr.cards;

import com.brunogtavares.minglr.user.User;

import java.util.ArrayList;

public class Card {
    public String exchange;
    public String sell;
    public String title;
    public int price;
    public String userId;
    public String userName;
    public String profileImageUrl;
    public ArrayList<String> likers = new ArrayList<String>();
    public ArrayList<String> dislikers = new ArrayList<String>();

    public Card(){};

    public Card(String exchange, String sell, String title) {
        this.exchange = exchange;
        this.sell = sell;
        this.title = title;
    }

    public Card(String userId, String title, String exchange, String sell, int price) {
        this.exchange = exchange;
        this.price = price;
        this.sell = sell;
        this.title = title;
    }


    public Card(String userId, String userName, String profileImageUrl, String title, String exchange, String sell, int price) {
        this.userId = userId;
        this.userName = userName;
        this.profileImageUrl = profileImageUrl;
        this.title = title;
        this.exchange = exchange;
        this.sell = sell;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
