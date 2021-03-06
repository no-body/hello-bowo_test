package com.hello_bowo.alex.receptionhelper.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hello_bowo.alex.receptionhelper.ActivityDescPage;
import com.hello_bowo.alex.receptionhelper.Model.Activity;
import com.hello_bowo.alex.receptionhelper.Model.ICardObject;
import com.hello_bowo.alex.receptionhelper.Model.Room;
import com.hello_bowo.alex.receptionhelper.R;
import com.hello_bowo.alex.receptionhelper.RoomDescPage;

import java.util.List;

/**
 * Created by Alex on 13/06/2017.
 */

public class CardObjectAdapter extends RecyclerView.Adapter<CardObjectAdapter.MyViewHolder>{

    private Context _context;
    private List<ICardObject> _cardObjects;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price;
        public ImageView picture;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            price = (TextView) view.findViewById(R.id.price);
            picture = (ImageView) view.findViewById(R.id.picture);
        }
    }

    public CardObjectAdapter(Context _context, List<ICardObject> _cardObject) {
        this._context = _context;
        this._cardObjects = _cardObject;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardobject_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ICardObject cardObject = _cardObjects.get(position);
        holder.title.setText(cardObject.get_title());
        holder.price.setText(cardObject.get_price() + " euros");

        Glide
                .with(_context)
                .load(cardObject.get_picture())
               // .listener(requestListener)
                .error(R.drawable.ic_not_found)
                .into(holder.picture);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ICardObject cardObject = _cardObjects.get(position);
                Intent myIntent;

                if (cardObject instanceof Room) {
                    myIntent = new Intent(_context, RoomDescPage.class);
                    myIntent.putExtra("CardObject", (Room) cardObject);
                }
                    else //if (cardObject instanceof Activity)
                {
                    myIntent = new Intent(_context, ActivityDescPage.class);
                    myIntent.putExtra("CardObject", (Activity) cardObject);
                }

                _context.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _cardObjects.size();
    }


    public boolean isHeader(int position) {
        return position == 0;
    }
}
