package com.example.bharath_instagram;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private static final String userName = "bharath914";
    private static final String passCode = "Bharath@914";

    String incorrectPass = "Incorrect PassWord";
    String invalidPass = "Invalid PassWord";
    String InvalidUserName = "Invalid UserName";
    TextInputLayout textInputLayoutUserName;

    TextInputLayout textInputLayoutPass;
    TextInputEditText username;
    TextInputEditText password;

    TextView englishUS;
    ConstraintLayout constraintLayout;

    MaterialButton login;

    String getUserName;
    String getPassCode;

    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.black));
        setContentView(R.layout.activity_main);

        //
        textInputLayoutUserName = findViewById(R.id.TextInputLayout1);
        textInputLayoutPass = findViewById(R.id.TextInputLayout2);
        username = (TextInputEditText) textInputLayoutUserName.getEditText();
        password = (TextInputEditText) textInputLayoutPass.getEditText();
        assert password != null;
        getPassCode = password.getText().toString();
        getUserName = username.getText().toString();
        englishUS = findViewById(R.id.language);
        ImageView imageView = findViewById(R.id.instagramLogoLogin);
        constraintLayout = findViewById(R.id.constraint1);
        login = findViewById(R.id.Btn_Login);


        SharedPreferences sharedPreferences = getSharedPreferences("Insta", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        login.setOnClickListener(v -> {


            if (!isValidInstagramUsername(username.getText().toString())) {
                showALert(InvalidUserName, "Please enter the correct instagram username");
            } else if (!validateUsrname(username.getText().toString())) {
                showALert("Invalid Username/Password", "Enter your correct credentials");
            } else if (!checkpass(password.getText().toString())) {
                showALert(incorrectPass, msg);
            }  else if (isValidInstagramUsername(username.getText().toString())
                    && validateUsrname(username.getText().toString())
                    && checkpass(password.getText().toString())

            ) {
                startProfile();
                editor.putBoolean("User", true);
                editor.apply();


            }
        });
    }

    private void startProfile() {

        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    private void showALert(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Handle OK button click
                        dialogInterface.dismiss();
                    }
                });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    private boolean checkpass(String pass) {
        if (password.length() < 8) {
            msg = "Password should be at least 8 characters long.";
            return false;
        }

        boolean containsUpperCase = false;
        boolean containsSpecialChar = false;
        boolean containsDigit = false;

        for (char c : pass.toCharArray()) {
            if (Character.isUpperCase(c)) {
                containsUpperCase = true;
            } else if (Character.isDigit(c)) {
                containsDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                containsSpecialChar = true;
            }
        }

        StringBuilder message = new StringBuilder();

        if (!containsUpperCase) {
            message.append("Password should contain at least one uppercase letter.\n");
        }
        if (!containsSpecialChar) {
            message.append("Password should contain at least one special character.\n");
        }
        if (!containsDigit) {
            message.append("Password should contain at least one digit.\n");
        }
        msg = message.toString();
        return message.length() == 0;
    }

    public boolean validatePass(String pass) {
        return passCode.equals(pass);
    }

    public boolean isValidInstagramUsername(String username) {
        // Check if the username length is within the allowed range
        if (username.length() < 1 || username.length() > 30) {
            return false;
        }

        // Check if the username contains only allowed characters
        if (!username.matches("^[a-zA-Z0-9._]+$")) {
            return false;
        }

        // Check if periods and underscores are used correctly
        if (username.startsWith(".") ||
                username.endsWith(".") || username.startsWith("-") ||
                username.contains("..") || username.contains("__") ||
                username.contains(".@") || username.contains("@.") ||
                username.endsWith("-")
        ) {
            return false;
        }

        return true; // Username passed all checks
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    public boolean validateUsrname(String str) {
        return userName.equals(str);
    }
}



