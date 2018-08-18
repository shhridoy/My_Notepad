package com.shhridoy.notepad.mDialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.shhridoy.notepad.R;

/**
 * Created by whoami on 8/14/2018.
 */

public class ColorDialog {

    private Context context;
    private int id;
    private Dialog dialog;
    private String selectedColor = null;

    private RelativeLayout rlRed, rlOrange, rlYellow, rlLightGreen, rlBlue, rlPurple, rlBlack, rlGrey, rlWhite;

    public ColorDialog (Context context, int id) {
        this.context = context;
        this.id = id;
        dialog = new Dialog(this.context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.set_note_color_dialog);

        iniViews();

        clickListeners();

        dialog.show();
    }

    public String getSelectedColor() {
        return selectedColor;
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
                selectedColor = "Red";
            }
        });

        rlOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlOrange);
                selectedColor = "Orange";
            }
        });

        rlYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlYellow);
                selectedColor = "Yellow";
            }
        });

        rlLightGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlLightGreen);
                selectedColor = "LightGreen";
            }
        });

        rlBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlBlue);
                selectedColor = "Blue";
            }
        });

        rlPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlPurple);
                selectedColor = "Purple";
            }
        });

        rlBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlBlack);
                selectedColor = "Black";
            }
        });

        rlGrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlGrey);
                selectedColor = "Grey";
            }
        });

        rlWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBackground(rlWhite);
                selectedColor = "White";
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
