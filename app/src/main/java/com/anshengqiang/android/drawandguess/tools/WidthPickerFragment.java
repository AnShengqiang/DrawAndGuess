package com.anshengqiang.android.drawandguess.tools;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.anshengqiang.android.drawandguess.MainActivity;
import com.anshengqiang.android.drawandguess.R;

/**
 * Created by anshengqiang on 2017/4/15.
 */

public class WidthPickerFragment extends DialogFragment {

    private static final String ARG_WIDTH = "width";

    private TextView mTextView;
    private SeekBar mSeekBar;

    public static WidthPickerFragment newInstance(int width) {

        Bundle args = new Bundle();
        args.putInt(ARG_WIDTH, width);

        WidthPickerFragment fragment = new WidthPickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_pen_width, null);

        int width = getArguments().getInt(ARG_WIDTH);
        mTextView = (TextView) view.findViewById(R.id.pen_width_text_view);
        mTextView.setText(String.valueOf(width));
        mSeekBar = (SeekBar) view.findViewById(R.id.pen_width_seek_bar);
        mSeekBar.setProgress(width);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTextView.setText(String.valueOf(progress));
                MainActivity activity = (MainActivity) getActivity();
                activity.setPenWidth(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.pen_width_title)
                .setView(view)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
