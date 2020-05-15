package com.example.wanderlust;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AutoCompletePlaceAdapter extends ArrayAdapter<PlaceItem> {
    private List<PlaceItem> placeListFull;
    public static Drawable imageDrawable;

    public AutoCompletePlaceAdapter(@NonNull Context context, @NonNull List<PlaceItem> placeList) {
        super(context, 0, placeList);
        placeListFull = new ArrayList<>(placeList);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return placeFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.place_autocomplete_row,parent,false);
        }

        TextView textViewName = convertView.findViewById(R.id.text_view_name);
        ImageView imageViewPlace = convertView.findViewById(R.id.image_view_place);
        PlaceItem placeItem = getItem(position);

        if(placeItem != null){
            textViewName.setText(placeItem.getPlaceName());
            imageViewPlace.setImageResource(placeItem.getPlaceImage());
        }
        return convertView;
    }

    private Filter placeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            List<PlaceItem> suggestions = new ArrayList<>();

            if(charSequence == null||charSequence.length() == 0) {
                suggestions.addAll(placeListFull);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (PlaceItem item : placeListFull) {
                    if (item.getPlaceName().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
            return  results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            clear();
            addAll((List)results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((PlaceItem) resultValue).getPlaceName();
        }
    };
}

