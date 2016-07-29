package es.cubel.gametiles.fragments;


import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import es.cubel.gametiles.R;


/**
 * Created by cubel on 29/07/16.
 */
public class Home extends Fragment {
    /**
     * Log
     */
    private String logname = "Home (Fragment)";

    //Variables estaticas


    //Variables
    private View view;
    TextView txt_title;


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setText(R.string.app_name);



        //Devolvemos la vista
        return view;
    };

};