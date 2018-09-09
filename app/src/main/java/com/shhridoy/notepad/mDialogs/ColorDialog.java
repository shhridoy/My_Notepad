package com.shhridoy.notepad.mDialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.shhridoy.notepad.R;
import com.shhridoy.notepad.mUtilities.MyPreferences;

/**
 * Created by whoami on 8/14/2018.
 */

public class ColorDialog {

    private Context context;
    private int id;
    private Dialog dialog;

    private View view;
    private RelativeLayout rlRed, rlOrange, rlYellow, rlLightGreen, rlBlue, rlPurple, rlBlack, rlGrey, rlWhite;

    public ColorDialog (Context context, View view, int id) {
        this.context = context;
        this.id = id;
        this.view = view;
        dialog = new Dialog(this.context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.set_note_color_dialog);

        iniViews();

        if (MyPreferences.getPreference(this.context, "Default Color").equals("Red")) {
            rlRed.setBackgroundColor(context.getResources().getColor(R.color.md_grey_400));
        } else if (MyPreferences.getPreference(this.context, "Default Color").equals("Orange")) {
            rlOrange.setBackgroundColor(context.getResources().getColor(R.color.md_grey_400));
        } else if (MyPreferences.getPreference(this.context, "Default Color").equals("Yellow")) {
            rlYellow.setBackgroundColor(context.getResources().getColor(R.color.md_grey_400));
        } else if (MyPreferences.getPreference(this.context, "Default Color").equals("Light Green")) {
            rlLightGreen.setBackgroundColor(context.getResources().getColor(R.color.md_grey_400));
        } else if (MyPreferences.getPreference(this.context, "Default Color").equals("Blue")) {
            rlBlue.setBackgroundColor(context.getResources().getColor(R.color.md_grey_400));
        } else if (MyPreferences.getPreference(this.context, "Default Color").equals("Purple")) {
            rlPurple.setBackgroundColor(context.getResources().getColor(R.color.md_grey_400));
        } else if (MyPreferences.getPreference(this.context, "Default Color").equals("Black")) {
            rlBlack.setBackgroundColor(context.getResources().getColor(R.color.md_grey_400));
        } else if (MyPreferences.getPreference(this.context, "Default Color").equals("Grey")) {
            rlGrey.setBackgroundColor(context.getResources().getColor(R.color.md_grey_400));
        } else if (MyPreferences.getPreference(this.context, "Default Color").equals("White")) {
            rlWhite.setBackgroundColor(context.getResources().getColor(R.color.md_grey_400));
        } else {
            rlRed.setBackgroundColor(context.getResources().getColor(R.color.md_grey_400));
        }

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
                selectedBackground(rlRed);
                view.setBackgroundColor(context.getResources().getColor(R.color.md_red_500));
                MyPreferences.setPreference(context, "Default Color", "Red");
                dialog.dismiss();
            }
        });

        rlOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlOrange);
                view.setBackgroundColor(context.getResources().getColor(R.color.md_orange_500));
                MyPreferences.setPreference(context, "Default Color", "Orange");
                dialog.dismiss();
            }
        });

        rlYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlYellow);
                view.setBackgroundColor(context.getResources().getColor(R.color.md_yellow_600));
                MyPreferences.setPreference(context, "Default Color", "Yellow");
                dialog.dismiss();
            }
        });

        rlLightGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlLightGreen);
                view.setBackgroundColor(context.getResources().getColor(R.color.md_light_green_500));
                MyPreferences.setPreference(context, "Default Color", "Light Green");
                dialog.dismiss();
            }
        });

        rlBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlBlue);
                view.setBackgroundColor(context.getResources().getColor(R.color.md_blue_500));
                MyPreferences.setPreference(context, "Default Color", "Blue");
                dialog.dismiss();
            }
        });

        rlPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlPurple);
                view.setBackgroundColor(context.getResources().getColor(R.color.md_purple_500));
                MyPreferences.setPreference(context, "Default Color", "Purple");
                dialog.dismiss();
            }
        });

        rlBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlBlack);
                view.setBackgroundColor(Color.BLACK);
                MyPreferences.setPreference(context, "Default Color", "Black");
                dialog.dismiss();
            }
        });

        rlGrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlGrey);
                view.setBackgroundColor(context.getResources().getColor(R.color.md_grey_500));
                MyPreferences.setPreference(context, "Default Color", "Grey");
                dialog.dismiss();
            }
        });

        rlWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlWhite);
                view.setBackgroundColor(Color.WHITE);
                MyPreferences.setPreference(context, "Default Color", "White");
                dialog.dismiss();
            }
        });

    }

    private void selectedBackground(RelativeLayout rl) {
        rl.setBackgroundColor(context.getResources().getColor(R.color.md_grey_400));
        if (rl == rlRed) {
            rlOrange.setBackgroundColor(0);
            rlYellow.setBackgroundColor(0);
            rlLightGreen.setBackgroundColor(0);
            rlBlue.setBackgroundColor(0);
            rlPurple.setBackgroundColor(0);
            rlBlack.setBackgroundColor(0);
            rlGrey.setBackgroundColor(0);
            rlWhite.setBackgroundColor(0);
        } else if (rl == rlOrange) {
            rlRed.setBackgroundColor(0);
            rlYellow.setBackgroundColor(0);
            rlLightGreen.setBackgroundColor(0);
            rlBlue.setBackgroundColor(0);
            rlPurple.setBackgroundColor(0);
            rlBlack.setBackgroundColor(0);
            rlGrey.setBackgroundColor(0);
            rlWhite.setBackgroundColor(0);
        } else if (rl == rlYellow) {
            rlRed.setBackgroundColor(0);
            rlOrange.setBackgroundColor(0);
            rlLightGreen.setBackgroundColor(0);
            rlBlue.setBackgroundColor(0);
            rlPurple.setBackgroundColor(0);
            rlBlack.setBackgroundColor(0);
            rlGrey.setBackgroundColor(0);
            rlWhite.setBackgroundColor(0);
        } else if (rl == rlLightGreen) {
            rlRed.setBackgroundColor(0);
            rlOrange.setBackgroundColor(0);
            rlYellow.setBackgroundColor(0);
            rlBlue.setBackgroundColor(0);
            rlPurple.setBackgroundColor(0);
            rlBlack.setBackgroundColor(0);
            rlGrey.setBackgroundColor(0);
            rlWhite.setBackgroundColor(0);
        } else if (rl == rlBlue) {
            rlRed.setBackgroundColor(0);
            rlOrange.setBackgroundColor(0);
            rlYellow.setBackgroundColor(0);
            rlLightGreen.setBackgroundColor(0);
            rlPurple.setBackgroundColor(0);
            rlBlack.setBackgroundColor(0);
            rlGrey.setBackgroundColor(0);
            rlWhite.setBackgroundColor(0);
        } else if (rl == rlPurple) {
            rlRed.setBackgroundColor(0);
            rlOrange.setBackgroundColor(0);
            rlYellow.setBackgroundColor(0);
            rlLightGreen.setBackgroundColor(0);
            rlBlue.setBackgroundColor(0);
            rlBlack.setBackgroundColor(0);
            rlGrey.setBackgroundColor(0);
            rlWhite.setBackgroundColor(0);
        } else if (rl == rlBlack) {
            rlRed.setBackgroundColor(0);
            rlOrange.setBackgroundColor(0);
            rlYellow.setBackgroundColor(0);
            rlLightGreen.setBackgroundColor(0);
            rlBlue.setBackgroundColor(0);
            rlPurple.setBackgroundColor(0);
            rlGrey.setBackgroundColor(0);
            rlWhite.setBackgroundColor(0);
        } else if (rl == rlGrey) {
            rlRed.setBackgroundColor(0);
            rlOrange.setBackgroundColor(0);
            rlYellow.setBackgroundColor(0);
            rlLightGreen.setBackgroundColor(0);
            rlBlue.setBackgroundColor(0);
            rlPurple.setBackgroundColor(0);
            rlBlack.setBackgroundColor(0);
            rlWhite.setBackgroundColor(0);
        } else if (rl == rlWhite) {
            rlRed.setBackgroundColor(0);
            rlOrange.setBackgroundColor(0);
            rlYellow.setBackgroundColor(0);
            rlLightGreen.setBackgroundColor(0);
            rlBlue.setBackgroundColor(0);
            rlPurple.setBackgroundColor(0);
            rlBlack.setBackgroundColor(0);
            rlGrey.setBackgroundColor(0);
        }
    }

}
