package com.brunogtavares.minglr.cards;

import com.brunogtavares.minglr.user.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Card {
    public String exchange;
    public String sell;
    public String title;
    public Long price;
    public String userId;
    public String userName;
    public String coverImageUrl = "default";
    public ArrayList<String> likers = new ArrayList<String>();
    public ArrayList<String> dislikers = new ArrayList<String>();

    public Card(){};

    public Card(String exchange, String sell, String title) {
        this.exchange = exchange;
        this.sell = sell;
        this.title = title;
    }

    public Card(String userId, String title, String exchange, String sell, Long price) {
        this.userId = userId;
        this.exchange = exchange;
        this.price = price;
        this.sell = sell;
        this.title = title;
    }


//    public Card(String userId, String userName, String coverImageUrl, String title, String exchange, String sell, int price) {
//        this.userId = userId;
//        this.userName = userName;
//        this.coverImageUrl = coverImageUrl;
//        this.title = title;
//        this.exchange = exchange;
//        this.sell = sell;
//    }

    public String getTitle() {
        return this.title;
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

    public String getcoverImageUrl() {
        return coverImageUrl;
    }

    public void setcoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public ArrayList<String> getLikers() {
        return this.likers;
    }

    public ArrayList<String> getDislikers () {
        return this.dislikers;
    }

    public void setDislikers(ArrayList<String> dislikers) {
        this.dislikers = dislikers;
    }

    public void setLikers(ArrayList<String> likers) {
        this.likers = likers;
    }

}
