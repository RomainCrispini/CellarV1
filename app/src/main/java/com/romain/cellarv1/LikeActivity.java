package com.romain.cellarv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class LikeActivity extends AppCompatActivity {

    SpaceNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_like);

        navigationView = findViewById(R.id.space);
        //navigationView.initWithSaveInstanceState(savedInstanceState);
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_person_outline_black_24dp));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_view_list_black_24dp));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_favorite_border_black_24dp));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_search_black_24dp));

        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(LikeActivity.this,"SCAN", Toast.LENGTH_SHORT).show();
                navigationView.setCentreButtonSelectable(true);

            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

                if (itemIndex == 0){
                    Toast.makeText(LikeActivity.this, "USER", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LikeActivity.this, UserActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up, R.anim.slide_out_up);
                } else if (itemIndex == 1) {
                    Toast.makeText(LikeActivity.this, "CELLAR", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LikeActivity.this, CellarActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up, R.anim.slide_out_up);
                } else if (itemIndex == 2) {
                    Toast.makeText(LikeActivity.this, "LIKE", Toast.LENGTH_SHORT).show();
                } else if (itemIndex == 3) {
                    Toast.makeText(LikeActivity.this, "SEARCH", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LikeActivity.this, SearchActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up, R.anim.slide_out_up);
                } else {
                    Toast.makeText(LikeActivity.this, "BUG", Toast.LENGTH_SHORT).show();
                }

                // switch(itemIndex){
                //       case 0:
                //          Toast.makeText(MainActivity.this, "USER", Toast.LENGTH_SHORT).show();
                //         Intent intent = new Intent(MainActivity.this, UserActivity.class);
                //          intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //         startActivityForResult(intent, 0);
                //        break;
                //    case 1:
                //        Toast.makeText(MainActivity.this, "CELLAR", Toast.LENGTH_SHORT).show();
                //        Intent intent = new Intent(MainActivity.this, CellarActivity.class);
                //        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //        startActivityForResult(intent, 0);
                //        break;
                //    case 2:
                //        Toast.makeText(MainActivity.this, "LIKE", Toast.LENGTH_SHORT).show();
                //       Intent intent = new Intent(MainActivity.this, LikeActivity.class);
                //      intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //       startActivityForResult(intent, 0);
                //       break;
                //   case 3:
                //       Toast.makeText(MainActivity.this, "SEARCH", Toast.LENGTH_SHORT).show();
                //      Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                //       intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //       startActivityForResult(intent, 0);
                //       break;
                //   default:
                //       Toast.makeText(MainActivity.this, "BUG", Toast.LENGTH_SHORT).show();
                // }

            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {

            }
        });


    }

}