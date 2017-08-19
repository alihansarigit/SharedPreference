package com.androidedu.kodluyoruz.sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.androidedu.kodluyoruz.sharedpreference.enums.SharedPreferenceEnum;
import com.androidedu.kodluyoruz.sharedpreference.enums.SharedPreferenceNameEnum;

public class SharedPreferenceActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    //view component
    private EditText edtUserName = null;
    private CheckBox chkSaveUserName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        initView();
    }

    private void initView() {

        edtUserName = (EditText) findViewById(R.id.activity_shared_preference_edtUserName);
        chkSaveUserName = (CheckBox) findViewById(R.id.activity_shared_preference_chkSaveUserName);

        initEvent();
    }

    private void initEvent() {

        chkSaveUserName.setOnCheckedChangeListener(this);

        edtUserName.setText(getUserName());

        if (!edtUserName.getText().toString().equalsIgnoreCase("UserName is empty")) {

            chkSaveUserName.setChecked(true);
        } else if (TextUtils.isEmpty(edtUserName.getText())) {

            chkSaveUserName.setChecked(false);
        } else {
            chkSaveUserName.setChecked(false);
        }
    }

    private void saveOrDeleteUserName(boolean isSave) {

        SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferenceNameEnum.SharedPreference.toString(), MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (isSave) {
            editor.putString(SharedPreferenceEnum.UserName.toString(), edtUserName.getText().toString());
        } else {
            editor.putString(SharedPreferenceEnum.UserName.toString(), "");
        }

//        editor.commit();
        editor.apply();
    }

    private String getUserName() {

        SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferenceNameEnum.SharedPreference.toString(), MODE_PRIVATE);

        return sharedPreferences.getString(SharedPreferenceEnum.UserName.toString(), "UserName is empty");
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        saveOrDeleteUserName(isChecked);

//        if(isChecked){
//            saveOrDeleteUserName(true);
//        }else{
//            saveOrDeleteUserName(false);
//        }
    }
}
