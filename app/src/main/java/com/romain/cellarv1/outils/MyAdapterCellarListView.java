package com.romain.cellarv1.outils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.romain.cellarv1.R;
import com.romain.cellarv1.modele.WineBottle;
import java.util.ArrayList;


public class MyAdapterCellarListView extends BaseAdapter {

    private Context context;
    private ArrayList<WineBottle> arrayList;

    public MyAdapterCellarListView(Context context, ArrayList<WineBottle> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.activity_cellar_custom_list_view, null);

        ImageView image = (ImageView) convertView.findViewById(R.id.imageListView);
        TextView country = (TextView) convertView.findViewById(R.id.countryListView);
        TextView region = (TextView) convertView.findViewById(R.id.regionListView);
        TextView appellation = (TextView) convertView.findViewById(R.id.appellationListView);

        WineBottle wineBottle = arrayList.get(position);

        Tools tools = new Tools();
        image.setImageBitmap(tools.stringToBitmap(wineBottle.getImage()));

        country.setText(wineBottle.getCountry());
        region.setText(wineBottle.getRegion());
        appellation.setText(wineBottle.getAppellation());

        return convertView;
    }


}