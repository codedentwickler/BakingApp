package com.example.codedentwickler.bakingapp.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.codedentwickler.bakingapp.R;


/**
 * Created by codedentwickler on 4/12/17.
 */

public class MessageUtils {

    public static void showMessage(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void showMessage(View view, String message, View.OnClickListener listener) {

        Context context = view.getContext();
        Snackbar snackbar = Snackbar.make(view, message
                , Snackbar.LENGTH_LONG)
                .setAction(R.string.undo,listener)
                .setActionTextColor(Color.WHITE);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(
               context.getResources().getColor(R.color.colorAccent));
        snackbar.show();
    }


}
