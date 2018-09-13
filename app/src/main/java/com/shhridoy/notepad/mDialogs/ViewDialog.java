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
 * Created by whoami on 9/14/2018.
 */

public class ViewDialog {
    private Context context;
    private Dialog dialog;
    private RelativeLayout rlSmall, rlMedium, rlDetail, rlGrid;
    private TextView textView;

    public ViewDialog(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
        dialog = new Dialog(this.context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.notes_view_selection_dialog);

        rlSmall = dialog.findViewById(R.id.notesViewSelectionRLSmallList);
        rlMedium = dialog.findViewById(R.id.notesViewSelectionRLMediumList);
        rlDetail = dialog.findViewById(R.id.notesViewSelectionRLDetailsList);
        rlGrid = dialog.findViewById(R.id.notesViewSelectionRLGridView);

        onClickListeners();

        dialog.show();
    }

    private void onClickListeners() {

        rlSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String s = "Small list";
                MyPreferences.setPreference(context, "Notes View", "Small list");
                textView.setText("Small list");
                dialog.dismiss();
            }
        });

        rlMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String s = "Medium list";
                MyPreferences.setPreference(context, "Notes View", "Medium list");
                textView.setText("Medium list");
                dialog.dismiss();
            }
        });

        rlDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String s = "List with details";
                MyPreferences.setPreference(context, "Notes View", "List with details");
                textView.setText("List with details");
                dialog.dismiss();
            }
        });

        rlGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String s = "Grid";
                MyPreferences.setPreference(context, "Notes View", "Grid");
                textView.setText("Grid");
                dialog.dismiss();
            }
        });
    }

}
