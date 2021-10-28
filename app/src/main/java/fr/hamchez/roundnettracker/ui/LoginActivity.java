package fr.hamchez.roundnettracker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.Callable;

import fr.hamchez.roundnettracker.MainActivity;
import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.database.RoundnetSQLite;
import fr.hamchez.roundnettracker.database.dao.PlayerDAO;
import fr.hamchez.roundnettracker.models.Player;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView errorMessageTextView;

    private Button signinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signinButton = findViewById(R.id.signinButton);
        errorMessageTextView = findViewById(R.id.errorMessage);

        signinButton.setEnabled(false);

        addTextWatchers();


        this.deleteDatabase("Roundnet");


        RoundnetSQLite roundnetSQLite = new RoundnetSQLite(this);
        roundnetSQLite.getWritableDatabase();

        roundnetSQLite.createTestPlayers();

    }

    private void addTextWatchers(){

        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                signinButton.setEnabled(
                    !usernameEditText.getText().toString().isEmpty() &&  !passwordEditText.getText().toString().isEmpty()
                );
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                signinButton.setEnabled(
                        !usernameEditText.getText().toString().isEmpty() &&  !passwordEditText.getText().toString().isEmpty()
                );
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void onSigninButtonClick(View view){

        Callable<Player> getUser = () -> new PlayerDAO(this).login(usernameEditText.getText().toString(),passwordEditText.getText().toString());

        try{

            Player connectedPlayer = getUser.call();

            if(connectedPlayer != null){

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("User", connectedPlayer);

                startActivity(intent);

            }else{
                errorMessageTextView.setVisibility(View.VISIBLE);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void onRegisterButtonClick(View view){


    }
}