package com.romain.cellarv1.outils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.romain.cellarv1.R;
import com.romain.cellarv1.modele.WineBottle;
import java.util.ArrayList;


public class MyAdapterCellarRecyclerView extends RecyclerView.Adapter<MyAdapterCellarRecyclerView.CellarViewHolder> {

    private ArrayList<WineBottle> wineBottleArrayList;

    // Constructeur
    public MyAdapterCellarRecyclerView(ArrayList<WineBottle> arrayList) {
        wineBottleArrayList = arrayList;
    }

    public static class CellarViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageWineColor;
        public ImageView image;
        public TextView country;
        public TextView region;
        public TextView appellation;
        public TextView domain;
        public TextView year;

        public CellarViewHolder(@NonNull View itemView) {
            super(itemView);
            imageWineColor = itemView.findViewById(R.id.imageWineColorListView);
            image = itemView.findViewById(R.id.imageBottleListView);
            country = itemView.findViewById(R.id.countryListView);
            region = itemView.findViewById(R.id.regionListView);
            appellation = itemView.findViewById(R.id.appellationListView);
            domain = itemView.findViewById(R.id.domainListView);
            year = itemView.findViewById(R.id.yearListView);
        }
    }

    @NonNull
    @Override
    public CellarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cellar_custom_list_view, parent, false);
        CellarViewHolder cellarViewHolder = new CellarViewHolder(v);
        return cellarViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CellarViewHolder holder, int position) {
        WineBottle currentItem = wineBottleArrayList.get(position);

        Tools tools = new Tools();

        holder.image.setImageBitmap(tools.stringToBitmap(currentItem.getImage()));
        holder.country.setText(currentItem.getCountry());
        holder.region.setText(currentItem.getRegion());
        holder.appellation.setText(currentItem.getAppellation());
        holder.domain.setText(currentItem.getDomain());
        holder.year.setText(currentItem.getYear().toString());

        switch(currentItem.getWineColor().trim()) {
            case "Rouge" :
                holder.imageWineColor.setImageResource(R.drawable.red_wine_listview);
                break;
            case "Rose" :
                holder.imageWineColor.setImageResource(R.drawable.rose_wine_listview);
                break;
            case "Blanc" :
                holder.imageWineColor.setImageResource(R.drawable.white_wine_listview);
                break;
            case "Effervescent" :
                holder.imageWineColor.setImageResource(R.drawable.champ_wine_listview);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return this.wineBottleArrayList.size();
    }

}
