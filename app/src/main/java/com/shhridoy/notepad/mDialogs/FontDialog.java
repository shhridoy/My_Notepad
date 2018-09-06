package com.shhridoy.notepad.mDialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shhridoy.notepad.R;
import com.shhridoy.notepad.mUtilities.MyPreferences;


/**
 * Created by whoami on 9/6/2018.
 */

public class FontDialog {

    private Context context;
    private Dialog dialog;
    private RelativeLayout rlSmall, rlMedium, rlLarge;
    private TextView textView;

    public FontDialog (Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
        dialog = new Dialog(this.context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.font_size_selection_dialog);

        rlSmall = dialog.findViewById(R.id.fontSizeSelectionRLSmall);
        rlMedium = dialog.findViewById(R.id.fontSizeSelectionRLMedium);
        rlLarge = dialog.findViewById(R.id.fontSizeSelectionRLLarge);

        onClickListeners();

        dialog.show();
    }

    private void onClickListeners() {
        rlSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPreferences.setPreference(context, "Font Size", "Small");
                textView.setText("Small");
                dialog.dismiss();
            }
        });

        rlMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPreferences.setPreference(context, "Font Size", "Medium");
                textView.setText("Medium");
                dialog.dismiss();
            }
        });

        rlLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPreferences.setPreference(context, "Font Size", "Large");
                textView.setText("Large");
                dialog.dismiss();
            }
        });
    }

}
