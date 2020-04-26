package com.romain.cellarv1.outils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.romain.cellarv1.R;
import com.romain.cellarv1.modele.WineBottle;
import com.romain.cellarv1.vue.CellarActivity;

import java.util.ArrayList;
import java.util.List;


public class MyAdapterCellarRecyclerView extends RecyclerView.Adapter<MyAdapterCellarRecyclerView.CellarViewHolder> {

    ArrayList<WineBottle> wineBottleArrayList;

    Context mContext;
    Cursor mCursor;

    // Constructeur
    public MyAdapterCellarRecyclerView(Context mContext, ArrayList<WineBottle> arrayList) {
        wineBottleArrayList = arrayList;
        this.mContext = mContext;
    }

    public static class CellarViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageWineColor;
        public ImageView image;
        public TextView region;
        public TextView appellation;
        public TextView domain;
        public TextView year;

        public CardView cardView;
        public CardView pastilleImageBottle;

        public ImageButton essai;


        public CellarViewHolder(@NonNull View itemView) {
            super(itemView);
            imageWineColor = itemView.findViewById(R.id.imageWineColorListView);
            image = itemView.findViewById(R.id.imageBottleListView);
            region = itemView.findViewById(R.id.regionListView);
            appellation = itemView.findViewById(R.id.appellationListView);
            domain = itemView.findViewById(R.id.domainListView);
            year = itemView.findViewById(R.id.yearListView);

            cardView = itemView.findViewById(R.id.cardView);
            pastilleImageBottle = itemView.findViewById(R.id.pastilleImageBottle);

            essai = itemView.findViewById(R.id.essai);

        }
    }

    @NonNull
    @Override
    public CellarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_cellar_custom_list_view, viewGroup, false);
        CellarViewHolder cellarViewHolder = new CellarViewHolder(v);
        //return new CellarViewHolder(v);
        return cellarViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CellarViewHolder holder, int position) {

        if(!mCursor.moveToPosition(position)) {
            return;
        }

        //Glide.with(mContext).load(wineBottleArrayList.get(position).getImage()).into(holder.cardView);

        // On applique l'animation sur la pastille de la bouteille
        holder.pastilleImageBottle.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_image_cardview));

        // On applique l'animation sur la CardView
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_animation_cardview));

        // On set les infos dans le cardview layout
        WineBottle currentItem = wineBottleArrayList.get(position);

        //long id = mCursor.getLong(mCursor.getColumnIndex());

        Tools tools = new Tools();
        holder.image.setImageBitmap(tools.stringToBitmap(currentItem.getImage()));

        holder.region.setText(currentItem.getRegion());
        holder.appellation.setText(currentItem.getAppellation());
        holder.domain.setText(currentItem.getDomain());
        holder.year.setText(currentItem.getYear().toString());

        //holder.essai.setBackgroundColor(Color.YELLOW);

        // On set la couleur du vin sous la pastille de l'image de l'étiquette
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

        // On set la CardView d'un coeur si la bouteille est like
        switch(currentItem.getLike()) {
            case 0 :
                holder.essai.setImageResource(R.drawable.icon_like_cardview);
                holder.essai.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
                break;
            case 1 :
                holder.essai.setImageResource(R.drawable.icon_navbar_scan);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return this.wineBottleArrayList.size();
    }

}
