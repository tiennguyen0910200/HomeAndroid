package com.example.accountmanagement5system;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStructure;
import android.widget.EditText;
import android.widget.Toast;

import java.text.BreakIterator;

public class AccountsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        EditText txtAccUser = (EditText) findViewById(R.id.txtAccUser);
        EditText txtAccPwd = (EditText) findViewById(R.id.txtAccPwd);
        boolean isUpdate = false;
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("mode");
        if (b != null) {
            isUpdate = b.getBoolean("mode_update");
            if (isUpdate) {
                Accounts accounts = (Accounts) b.getSerializable("data");
                txtAccUser.setText(accounts.acc_user);
                txtAccUser.setEnabled(false);
                txtAccPwd.setText(accounts.acc_pwd);
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void save(View view) {
        boolean isUpdate = false;
        BreakIterator txtAccPwd = null;
        if (!isUpdate) {
            ViewStructure txtAccUser = null;
            String accUser = txtAccUser.getText().toString();
            String accPwd = txtAccPwd.getText().toString();
            txtAccUser.setEnabled(true);
            new WSHandling(AccountsActivity.this, accUser, accPwd).execute(WSHandling.CREATE);
            Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
        } else {
            String accPwd = txtAccPwd.getText().toString();
            new WSHandling(AccountsActivity.this, accounts.acc_user, accPwd).execute(WSHandling.UPDATE);
            Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
        }
        switch (view.getId()) {
            case R.id.btnSave_Exit:
                finish();
        }
    }
}