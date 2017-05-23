package com.example.yiseo.bs2017;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class tabWishList extends Fragment{

    static final String[] LIST_MENU = {"B1", "B2", "B3"} ;

    public static tabWishList newInstance(){
        Bundle args = new Bundle();
        tabWishList twl = new tabWishList();
        twl.setArguments(args);
        return twl;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_wishlist,null);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU) ;
        ListView listview = (ListView) view.findViewById(R.id.BookWishView) ;
        listview.setAdapter(adapter) ;
        return view;
    }
}
