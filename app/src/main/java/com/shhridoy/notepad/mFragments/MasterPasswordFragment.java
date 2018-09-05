package com.shhridoy.notepad.mFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shhridoy.notepad.MainActivity;
import com.shhridoy.notepad.R;
import com.shhridoy.notepad.mUtilities.MyPreferences;

/**
 * Created by whoami on 8/18/2018.
 */

public class MasterPasswordFragment extends Fragment {

    private TextView currentPasswordTitleTV, newPasswordTitleTV, userHintTV, currentPasswordWarningTV, newPasswordWarningTV;
    private EditText currentPasswordET, newPasswordET1, newPasswordET2;
    private Button saveButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.master_password_fragment, container, false);

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

        initViews(rootView);

        if (MyPreferences.getPreference(getContext(), "Password").equals("Default")) {
            currentPasswordTitleTV.setVisibility(View.GONE);
            currentPasswordET.setVisibility(View.GONE);
        }

        currentPasswordWarningTV.setVisibility(View.GONE);
        newPasswordWarningTV.setVisibility(View.GONE);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!MyPreferences.getPreference(getContext(), "Password").equals("Default")) {
                    if (currentPasswordET.getText().toString().trim().length() == 0) {
                        currentPasswordWarningTV.setText("*Put your current password first.");
                        currentPasswordWarningTV.setVisibility(View.VISIBLE);
                    } else if (!currentPasswordET.getText().toString().trim().equals(MyPreferences.getPreference(getContext(), "Password"))) {
                        currentPasswordWarningTV.setText("*Password is not correct.");
                        currentPasswordWarningTV.setVisibility(View.VISIBLE);
                    }
                }

                if (newPasswordET1.getText().toString().trim().length() == 0) {
                    newPasswordWarningTV.setVisibility(View.VISIBLE);
                    newPasswordWarningTV.setText("*Enter new password.");
                    currentPasswordWarningTV.setVisibility(View.GONE);
                } else if (!newPasswordET1.getText().toString().trim().equals(newPasswordET2.getText().toString().trim())) {
                    newPasswordWarningTV.setVisibility(View.VISIBLE);
                    newPasswordWarningTV.setText("*Password doesn't match.");
                    currentPasswordWarningTV.setVisibility(View.GONE);
                }

                if (MyPreferences.getPreference(getContext(), "Password").equals("Default")) {
                    if (newPasswordET1.getText().toString().trim().equals(newPasswordET2.getText().toString().trim())) {
                        MyPreferences.setPreference(getContext(), "Password", newPasswordET1.getText().toString().trim());

                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        if (fragmentManager.findFragmentByTag("Password Fragment") != null) {
                            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("Password Fragment")).commit();
                        }

                    }
                } else {
                    if (currentPasswordET.getText().toString().trim().equals(MyPreferences.getPreference(getContext(), "Password")) &&
                            newPasswordET1.getText().toString().trim().equals(newPasswordET2.getText().toString().trim())) {
                        MyPreferences.setPreference(getContext(), "Password", newPasswordET1.getText().toString().trim());

                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        if (fragmentManager.findFragmentByTag("Password Fragment") != null) {
                            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("Password Fragment")).commit();
                        }
                    }
                }

            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.master_password_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //noinspection ConstantConditions
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        int id = item.getItemId();

        if (id == R.id.action_clear_password) {
            Toast.makeText(getContext(), "Clear password", Toast.LENGTH_LONG).show();
        } else if (id == android.R.id.home) {
            if (fragmentManager.findFragmentByTag("Password Fragment") != null && fragmentManager.findFragmentByTag("Password Fragment").isVisible()) {
                fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Password Fragment")).commit();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void initViews(View v) {
        currentPasswordTitleTV = v.findViewById(R.id.masterPasswordTitle1TV);
        newPasswordTitleTV = v.findViewById(R.id.masterPasswordTitle2TV);
        userHintTV = v.findViewById(R.id.masterPasswordUserHintTV);
        currentPasswordWarningTV = v.findViewById(R.id.masterPasswordCurrentWarningTV);
        newPasswordWarningTV = v.findViewById(R.id.masterPasswordNewWarningTV);

        currentPasswordET = v.findViewById(R.id.masterPasswordETCurrent);
        newPasswordET1 = v.findViewById(R.id.masterPasswordET1);
        newPasswordET2 = v.findViewById(R.id.masterPasswordET2);

        saveButton = v.findViewById(R.id.masterPasswordSaveButton);
    }
}
