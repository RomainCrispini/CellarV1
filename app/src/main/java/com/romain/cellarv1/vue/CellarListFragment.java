package com.romain.cellarv1.vue;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.romain.cellarv1.R;
import com.romain.cellarv1.modele.AccesLocal;
import com.romain.cellarv1.modele.WineBottle;
import com.romain.cellarv1.outils.MyAdapterCellarListView;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CellarListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CellarListFragment extends Fragment {

    private AccesLocal accesLocal;

    private ListView listViewBDD;
    private OvershootInterpolator interpolator = new OvershootInterpolator();

    private ImageButton sortMap;
    private ImageButton sortColor;
    private ImageButton sortYear;
    private ImageButton sortApogee;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CellarListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CellarListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CellarListFragment newInstance(String param1, String param2) {
        CellarListFragment fragment = new CellarListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View cellarListFragment = inflater.inflate(R.layout.fragment_cellar_list, container, false);
        listViewBDD = (ListView) cellarListFragment.findViewById(R.id.listViewBDD);
        listViewBDD.setAlpha(0f);
        listViewBDD.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(2000).start();
        loadWineBottleInListView();

        sortMap = (ImageButton) cellarListFragment.findViewById(R.id.sortMap);


        return cellarListFragment;



    }

    private void loadWineBottleInListView() {

        //accesLocal = new AccesLocal(this);
        accesLocal = new AccesLocal(getContext());
        ArrayList<WineBottle> wineBottleList = (ArrayList<WineBottle>) accesLocal.recoverWineBottleList();

        //MyAdapterCellarListView myAdapterCellarListView = new MyAdapterCellarListView(this, wineBottleList);
        MyAdapterCellarListView myAdapterCellarListView = new MyAdapterCellarListView(getContext(), wineBottleList);
        listViewBDD.setAdapter(myAdapterCellarListView);
        myAdapterCellarListView.notifyDataSetChanged();

    }

}
