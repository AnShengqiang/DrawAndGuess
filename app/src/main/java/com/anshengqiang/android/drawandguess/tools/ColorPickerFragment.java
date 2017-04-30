package com.anshengqiang.android.drawandguess.tools;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.anshengqiang.android.drawandguess.MainActivity;
import com.anshengqiang.android.drawandguess.R;

/**
 * Created by anshengqiang on 2017/4/15.
 */

public class ColorPickerFragment extends DialogFragment{

    private static final String TAG = "ColorPickerFragment";
    private static final String ARG_COLOR = "color";

    private Button mDarkGreyButton;
    private Button mGreenButton;
    private Button mRedButton;
    private Button mPurpleButton;
    private Button mBlueButton;
    private Button mBlackButton;
    private Button mEraserButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_color, null);

        mEraserButton = (Button) v.findViewById(R.id.eraser_button);
        mEraserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                mEraserButton.setText(getResources().getText(R.string.chose));
                MainActivity activity = (MainActivity) getActivity();
                activity.setColor(getResources().getColor(R.color.white));
            }
        });

        mDarkGreyButton = (Button) v.findViewById(R.id.dark_grey_button);
        mDarkGreyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                mDarkGreyButton.setText(getResources().getText(R.string.chose));
                MainActivity activity = (MainActivity) getActivity();
                activity.setColor(getResources().getColor(R.color.darkgrey));
            }
        });

        mGreenButton = (Button) v.findViewById(R.id.green_button);
        mGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                mGreenButton.setText(getResources().getText(R.string.chose));
                MainActivity activity = (MainActivity) getActivity();
                activity.setColor(getResources().getColor(R.color.green));
            }
        });

        mRedButton = (Button) v.findViewById(R.id.red_button);
        mRedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                mRedButton.setText(getResources().getText(R.string.chose));
                MainActivity activity = (MainActivity) getActivity();
                activity.setColor(getResources().getColor(R.color.red));
            }
        });

        mPurpleButton = (Button) v.findViewById(R.id.purple_button);
        mPurpleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                mPurpleButton.setText(getResources().getText(R.string.chose));
                MainActivity activity = (MainActivity) getActivity();
                activity.setColor(getResources().getColor(R.color.purple));
            }
        });

        mBlueButton = (Button) v.findViewById(R.id.blue_button);
        mBlueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                mBlueButton.setText(getResources().getText(R.string.chose));
                MainActivity activity = (MainActivity) getActivity();
                activity.setColor(getResources().getColor(R.color.blue));
            }
        });

        mBlackButton = (Button) v.findViewById(R.id.black_button);
        mBlackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                mBlackButton.setText(getResources().getText(R.string.chose));
                MainActivity activity = (MainActivity) getActivity();
                activity.setColor(getResources().getColor(R.color.black));
            }
        });



        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.color_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

    private void clearText(){
        mDarkGreyButton.setText("");
        mGreenButton.setText("");
        mRedButton.setText("");
        mPurpleButton.setText("");
        mBlueButton.setText("");
        mBlackButton.setText("");
    }


}
