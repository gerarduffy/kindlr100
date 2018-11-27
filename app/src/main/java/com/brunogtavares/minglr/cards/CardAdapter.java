package com.brunogtavares.minglr.cards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brunogtavares.minglr.R;
import com.brunogtavares.minglr.cards.Card;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class CardAdapter extends ArrayAdapter<Card> {

    private Context mContext;

    public CardAdapter(Context context, int resourceId, List<Card> items) {
        super(context, resourceId, items);
        this.mContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Card cardItem = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.tv_card_user_name);
        ImageView profilePicture = (ImageView) convertView.findViewById(R.id.iv_card_image);
        TextView tags = (TextView) convertView.findViewById(R.id.tags);
        TextView sale = (TextView) convertView.findViewById(R.id.sale);
        String allTags = "";
        for (int i = 0; i < cardItem.tags.size(); i++) {
            allTags += cardItem.tags.get(i);
            if (i != cardItem.tags.size()-1) {
                allTags +=  ", ";
            }
        }

        name.setText(cardItem.getTitle());
        tags.setText(allTags);
        if (cardItem.exchange.equals("false")) {
            sale.setText("Price: " + cardItem.price);
        }

//        // If image url is assigned to default, it will automatically assign a default image.
//        if(cardItem.getcoverImageUrl().equals("default")) {
//            Glide.with(convertView.getContext()).load(R.mipmap.ic_launcher).into(profilePicture);
//        }
//        else {
//            Glide.clear(profilePicture);
//            Glide.with(convertView.getContext()).load(cardItem.getProfileImageUrl()).into(profilePicture);
//        }


        return convertView;
    }
}
