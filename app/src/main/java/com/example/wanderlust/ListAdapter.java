package com.example.wanderlust;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> ID;
    ArrayList<String> Name;
    ArrayList<String> PhoneNumber;



    public ListAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> name,
            ArrayList<String> phone
    ) {

        this.context = context2;
        this.ID = id;
        this.Name = name;
        this.PhoneNumber = phone;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {
        Holder holder;
        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.items, null);

            holder = new Holder();
            holder.Name_TextView = (TextView) child.findViewById(R.id.textViewNAME);
            holder.PhoneNumberTextView = (TextView) child.findViewById(R.id.textViewPHONE_NUMBER);
            holder.PlaceImage = (ImageView) child.findViewById(R.id.list_image);
            child.setTag(holder);

        }else{
            holder = (Holder) child.getTag();
        }
        if(PhoneNumber.get(position).equals("Delhi")) {
            holder.PlaceImage.setImageResource(R.drawable.delhi);
        }
        else if(PhoneNumber.get(position).equals("Ahmedabad")){
            holder.PlaceImage.setImageResource(R.drawable.ahmedabad);
        }
        else if(PhoneNumber.get(position).equals("Bangalore")){
            holder.PlaceImage.setImageResource(R.drawable.blr_slide_1);
        }
        else if(PhoneNumber.get(position).equals("Chennai")){
            holder.PlaceImage.setImageResource(R.drawable.chennai);
        }
        else if(PhoneNumber.get(position).equals("Hyderabad")){
            holder.PlaceImage.setImageResource(R.drawable.hydrabad);
        }
        else if(PhoneNumber.get(position).equals("Jaipur")){
            holder.PlaceImage.setImageResource(R.drawable.jaipur);
        }
        else if(PhoneNumber.get(position).equals("Kolkata")){
            holder.PlaceImage.setImageResource(R.drawable.kolkata);
        }
        else if(PhoneNumber.get(position).equals("Mumbai")){
            holder.PlaceImage.setImageResource(R.drawable.mumbai);
        }
        else if(PhoneNumber.get(position).equals("Panjim")){
            holder.PlaceImage.setImageResource(R.drawable.panaji);
        }
        else if(PhoneNumber.get(position).equals("Pune")){
            holder.PlaceImage.setImageResource(R.drawable.pune);
        }
        holder.Name_TextView.setText(Name.get(position));
        holder.PhoneNumberTextView.setText(PhoneNumber.get(position));

        return child;
    }

    public class Holder {
        TextView Name_TextView;
        TextView PhoneNumberTextView;
        ImageView PlaceImage;
    }

}