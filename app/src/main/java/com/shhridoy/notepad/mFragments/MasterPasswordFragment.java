package com.shhridoy.notepad.mFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shhridoy.notepad.R;

/**
 * Created by whoami on 8/18/2018.
 */

public class MasterPasswordFragment extends Fragment {

    private TextView currentPasswordTitleTV, newPasswordTitleTV, userHintTV;
    private EditText currentPasswordET, newPasswordET1, newPasswordET2;
    private Button saveButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.master_password_fragment, container, false);

        initViews(rootView);

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

        currentPasswordET = v.findViewById(R.id.masterPasswordETCurrent);
        newPasswordET1 = v.findViewById(R.id.masterPasswordET1);
        newPasswordET2 = v.findViewById(R.id.masterPasswordET2);

        saveButton = v.findViewById(R.id.masterPasswordSaveButton);
    }
}
