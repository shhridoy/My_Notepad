package com.shhridoy.notepad.mFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.shhridoy.notepad.R;
import com.shhridoy.notepad.mUtilities.MyPreferences;

/**
 * Created by whoami on 8/18/2018.
 */

public class MasterPasswordFragment extends Fragment {

    private TextView currentPasswordTitleTV, newPasswordTitleTV, userHintTV, currentPasswordWarningTV, newPasswordWarningTV;
    private EditText currentPasswordET, newPasswordET1, newPasswordET2;
    private Button saveButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.master_password_fragment, container, false);

        initViews(rootView);

        if (!MyPreferences.getPreference(getContext(), "Password").equals("Default")) {
            currentPasswordTitleTV.setVisibility(View.GONE);
            currentPasswordET.setVisibility(View.GONE);
        }

        currentPasswordWarningTV.setVisibility(View.GONE);
        newPasswordWarningTV.setVisibility(View.GONE);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPasswordET.getText().toString().trim().length() == 0) {
                    Toast.makeText(getContext(), "Enter you current password!", Toast.LENGTH_LONG).show();
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
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
