package com.shhridoy.notepad.mDialogs;

import android.app.Dialog;
import android.app.UiAutomation;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shhridoy.notepad.R;
import com.shhridoy.notepad.mDatabase.DBAdapter;
import com.shhridoy.notepad.mUtilities.MyPreferences;

/**
 * Created by whoami on 8/14/2018.
 */

public class ColorDialog {

    private Context context;
    private Dialog dialog;

    private RelativeLayout rlRed, rlOrange, rlYellow, rlLightGreen, rlBlue, rlPurple, rlBlack, rlGrey, rlWhite;

    public ColorDialog (Context context, int id) {
        this.context = context;
        dialog = new Dialog(this.context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.set_note_color_dialog);

        iniViews();

        clickListeners();

        dialog.show();
    }

    private void iniViews() {
        rlRed = dialog.findViewById(R.id.setColorRLRed);
        rlOrange = dialog.findViewById(R.id.setColorRLOrange);
        rlYellow = dialog.findViewById(R.id.setColorRLYellow);
        rlLightGreen = dialog.findViewById(R.id.setColorRLLightGreen);
        rlBlue = dialog.findViewById(R.id.setColorRLBlue);
        rlPurple = dialog.findViewById(R.id.setColorRLPurple);
        rlBlack = dialog.findViewById(R.id.setColorRLBlack);
        rlGrey = dialog.findViewById(R.id.setColorRLGrey);
        rlWhite = dialog.findViewById(R.id.setColorRLWhite);
    }

    private void clickListeners() {

        rlRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlRed.setBackgroundColor(context.getResources().getColor(R.color.md_red_500));

            }
        });

        rlOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rlYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rlLightGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rlBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rlPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rlBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rlGrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rlWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
