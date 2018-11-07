package com.brunogtavares.minglr.FirebaseData;


public final class  FirebaseContract {

    private FirebaseContract(){}

    public static abstract class FirebaseEntry {

        public static final String TABLE_USERS = "Users";

        // This direct child from Users, stores user's info
        public static final String COLUMN_SEX = "sex";
        public static final String COLUMN_BIRTHDAY = "birthday";

        public static final String COLUMN_SEX_FEMALE = "Female";
        public static final String COLUMN_SEX_MALE = "Male";

        public static final String COLUMN_CONNECTIONS = "connections";
        public static final String COLUMN_NOPE = "nope";
        public static final String COLUMN_YEP = "yep";
        public static final String COLUMN_MATCHES = "matches";
        public static final String COLUMN_LIKERS = "likers";
        public static final String COLUMN_DISLIKERS = "dislikers";

        public static final String TABLE_CHAT = "Chat";
        public static final String COLUMN_CHAT_ID = "ChatId";
        public static final String COLUMN_CHAT_TEXT = "text";
        public static final String COLUMN_CREATED_BY_USER = "createdByUser";
        public static final String COLUMN_IMAGE_URL = "imageUrl";

        public static final String COLUMN_ABOUT_ME = "aboutMe" ;

        // Posts
        public static final String TABLE_POSTS = "Posts";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_SELL = "sell";
        public static final String COLUMN_EXCHANGE = "exchange";
        public static final String COLUMN_NAME= "owner";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_PROFILE_IMAGE_URL = "profileImageUrl";
        public static final String COLUMN_USERID = "userID";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_TAGS = "tags";

    }
}
