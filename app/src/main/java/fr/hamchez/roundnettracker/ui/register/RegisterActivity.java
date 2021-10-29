package fr.hamchez.roundnettracker.ui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.database.dao.PlayerDAO;
import fr.hamchez.roundnettracker.models.Player;

public class RegisterActivity extends AppCompatActivity {

    EditText firstnameEditText;
    EditText lastnameEditText;
    EditText emailEditText;
    EditText usernameEditText;
    EditText birthdayEditText;
    EditText passwordEditText;
    EditText confirmPasswordEditText;

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstnameEditText = findViewById(R.id.firstnameEditText);
        lastnameEditText = findViewById(R.id.lastnameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        birthdayEditText = findViewById(R.id.birthdayEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setEnabled(false);

        addTextWatchers();

    }

    private void addTextWatchers() {

        firstnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerButton.setEnabled(
                        !firstnameEditText.getText().toString().isEmpty() && !lastnameEditText.getText().toString().isEmpty() &&
                        !emailEditText.getText().toString().isEmpty() && !usernameEditText.getText().toString().isEmpty() &&
                        !birthdayEditText.getText().toString().isEmpty() && !passwordEditText.getText().toString().isEmpty() &&
                        !confirmPasswordEditText.getText().toString().isEmpty() &&
                        !passwordEditText.getText().equals(confirmPasswordEditText.getText())
                );
            }


        });

        lastnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerButton.setEnabled(
                        !firstnameEditText.getText().toString().isEmpty() && !lastnameEditText.getText().toString().isEmpty() &&
                                !emailEditText.getText().toString().isEmpty() && !usernameEditText.getText().toString().isEmpty() &&
                                !birthdayEditText.getText().toString().isEmpty() && !passwordEditText.getText().toString().isEmpty() &&
                                !confirmPasswordEditText.getText().toString().isEmpty() &&
                                !passwordEditText.getText().equals(confirmPasswordEditText.getText())
                );
            }


        });

        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerButton.setEnabled(
                        !firstnameEditText.getText().toString().isEmpty() && !lastnameEditText.getText().toString().isEmpty() &&
                                !emailEditText.getText().toString().isEmpty() && !usernameEditText.getText().toString().isEmpty() &&
                                !birthdayEditText.getText().toString().isEmpty() && !passwordEditText.getText().toString().isEmpty() &&
                                !confirmPasswordEditText.getText().toString().isEmpty() &&
                                !passwordEditText.getText().equals(confirmPasswordEditText.getText())
                );
            }


        });

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerButton.setEnabled(
                        !firstnameEditText.getText().toString().isEmpty() && !lastnameEditText.getText().toString().isEmpty() &&
                                !emailEditText.getText().toString().isEmpty() && !usernameEditText.getText().toString().isEmpty() &&
                                !birthdayEditText.getText().toString().isEmpty() && !passwordEditText.getText().toString().isEmpty() &&
                                !confirmPasswordEditText.getText().toString().isEmpty() &&
                                !passwordEditText.getText().equals(confirmPasswordEditText.getText())
                );
            }


        });

        birthdayEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerButton.setEnabled(
                        !firstnameEditText.getText().toString().isEmpty() && !lastnameEditText.getText().toString().isEmpty() &&
                                !emailEditText.getText().toString().isEmpty() && !usernameEditText.getText().toString().isEmpty() &&
                                !birthdayEditText.getText().toString().isEmpty() && !passwordEditText.getText().toString().isEmpty() &&
                                !confirmPasswordEditText.getText().toString().isEmpty() &&
                                !passwordEditText.getText().equals(confirmPasswordEditText.getText())
                );
            }


        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerButton.setEnabled(
                        !firstnameEditText.getText().toString().isEmpty() && !lastnameEditText.getText().toString().isEmpty() &&
                                !emailEditText.getText().toString().isEmpty() && !usernameEditText.getText().toString().isEmpty() &&
                                !birthdayEditText.getText().toString().isEmpty() && !passwordEditText.getText().toString().isEmpty() &&
                                !confirmPasswordEditText.getText().toString().isEmpty() &&
                                !passwordEditText.getText().equals(confirmPasswordEditText.getText())
                );
            }


        });


        confirmPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerButton.setEnabled(
                        !firstnameEditText.getText().toString().isEmpty() && !lastnameEditText.getText().toString().isEmpty() &&
                                !emailEditText.getText().toString().isEmpty() && !usernameEditText.getText().toString().isEmpty() &&
                                !birthdayEditText.getText().toString().isEmpty() && !passwordEditText.getText().toString().isEmpty() &&
                                !confirmPasswordEditText.getText().toString().isEmpty() &&
                                !passwordEditText.getText().equals(confirmPasswordEditText.getText())
                );
            }


        });
    }

    public void onRegister(View view){

        Player player = new Player(
            0,
            firstnameEditText.getText().toString(),
            lastnameEditText.getText().toString(),
            usernameEditText.getText().toString(),
            emailEditText.getText().toString(),
            passwordEditText.getText().toString(),
            birthdayEditText.getText().toString(),
            0
        );

        new Thread(() -> {

            PlayerDAO playerDAO = new PlayerDAO(this);
            boolean result = playerDAO.insert(player);

            Toast toast;
            if(result){
                toast = Toast.makeText(this, R.string.registerUserOK, Toast.LENGTH_LONG);
            }else{
                toast = Toast.makeText(this,R.string.registerUserError,Toast.LENGTH_LONG);
            }

            toast.show();

            finish();

        }).start();

    }

}