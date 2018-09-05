package com.shhridoy.notepad.mFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shhridoy.notepad.MainActivity;
import com.shhridoy.notepad.R;
import com.shhridoy.notepad.mDialogs.ColorDialog;

/**
 * Created by whoami on 9/5/2018.
 */

public class SettingsFragment extends Fragment {

    private RelativeLayout defaultColorRL;
    private LinearLayout defaultFontSizeLL, setPasswordLL;
    private View defaultColorView;
    private TextView defaultFontSizeDetailsTV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.settings_fragment, container, false);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        if (((AppCompatActivity)getActivity()) != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        }
        if (((MainActivity) getActivity()) != null) {
            //noinspection ConstantConditions
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //noinspection ConstantConditions
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        }

        iniViews(rootView);

        onClickListeners();

        return rootView;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.settings_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //noinspection ConstantConditions
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        switch (item.getItemId()) {
            case R.id.action_refresh:
                Toast.makeText(getContext(), "Refreshing....", Toast.LENGTH_LONG).show();
                break;
            case android.R.id.home:
                if (fragmentManager.findFragmentByTag("Settings") != null && fragmentManager.findFragmentByTag("Settings").isVisible()) {
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Settings")).commit();
                }
                break;
        }

        return false;
    }

    private void iniViews(View v) {
        defaultColorRL = v.findViewById(R.id.settingsDefaultColorRL);
        defaultColorView = v.findViewById(R.id.settingsDefaultColorView);
        defaultFontSizeLL = v.findViewById(R.id.settingsFontSizeLL);
        setPasswordLL = v.findViewById(R.id.settingsSetPasswordLL);
        defaultFontSizeDetailsTV = v.findViewById(R.id.settingsFontSizeDetailsTV);
    }

    private void onClickListeners() {

        // DEFAULT COLOR RELATIVE LAYOUT ON CLICK LISTENER
        defaultColorRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDialog colorDialog = new ColorDialog(getContext(), -1);

                if (colorDialog.getSelectedColor() != null) {
                    switch (colorDialog.getSelectedColor()) {

                        case "Red":
                            defaultColorView.setBackgroundColor(getResources().getColor(R.color.md_red_500));
                            break;

                        case "Orange":
                            defaultColorView.setBackgroundColor(getResources().getColor(R.color.md_orange_500));
                            break;

                        case "Yellow":
                            defaultColorView.setBackgroundColor(getResources().getColor(R.color.md_yellow_600));
                            break;

                        case "LightGreen":
                            defaultColorView.setBackgroundColor(getResources().getColor(R.color.md_light_green_500));
                            break;

                        case "Blue":
                            defaultColorView.setBackgroundColor(getResources().getColor(R.color.md_blue_600));
                            break;

                        case "Purple":
                            defaultColorView.setBackgroundColor(getResources().getColor(R.color.md_purple_500));
                            break;

                        case "Black":
                            defaultColorView.setBackgroundColor(Color.BLACK);
                            break;

                        case "Grey":
                            defaultColorView.setBackgroundColor(getResources().getColor(R.color.md_grey_600));
                            break;

                        case "White":
                            defaultColorView.setBackgroundColor(Color.WHITE);
                            break;

                        default:
                            defaultColorView.setBackgroundColor(getResources().getColor(R.color.md_red_500));

                    }
                }
            }
        });

        defaultFontSizeLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        getContext(),
                        "Default Font size "+defaultFontSizeDetailsTV.getText().toString(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });

        setPasswordLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                if (fragmentManager.findFragmentByTag("Password Fragment") != null) {
                    fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("Password Fragment")).commit();
                    fragmentManager.beginTransaction().add(android.R.id.content, new MasterPasswordFragment(), "Password Fragment").commit();
                } else {
                    fragmentManager.beginTransaction().add(android.R.id.content, new MasterPasswordFragment(), "Password Fragment")
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }
}
