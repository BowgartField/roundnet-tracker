package fr.hamchez.roundnettracker.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.Callable;

import fr.hamchez.roundnettracker.MainActivity;
import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.database.RoundnetSQLite;
import fr.hamchez.roundnettracker.database.dao.ConnectedDAO;
import fr.hamchez.roundnettracker.database.dao.PlayerDAO;
import fr.hamchez.roundnettracker.models.Connected;
import fr.hamchez.roundnettracker.models.Player;
import fr.hamchez.roundnettracker.ui.register.RegisterActivity;
import fr.hamchez.roundnettracker.utils.ObjectSharedPreferences;

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

        configureSQLiteDB();

        // Verify if user is already connected
        verifyIsConnected();

    }

    private void verifyIsConnected() {

        List<Player> connectedPlayer = new ConnectedDAO(this).getAll();

        System.out.println(connectedPlayer);

        if(connectedPlayer.size() > 0){
            goToMainActivity(connectedPlayer.get(0));
        }

    }

    private void configureSQLiteDB() {

        //this.deleteDatabase("Roundnet");

        RoundnetSQLite roundnetSQLite = new RoundnetSQLite(this);
        roundnetSQLite.getWritableDatabase();

        //roundnetSQLite.createTestPlayers();
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

                ObjectSharedPreferences.saveObjectToSharedPreference(this,"player",connectedPlayer);

                new ConnectedDAO(this).insert(connectedPlayer);
                goToMainActivity(connectedPlayer);

            }else{
                errorMessageTextView.setVisibility(View.VISIBLE);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void onRegisterButtonClick(View view){

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    private void goToMainActivity(Player player){

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("User", player);

        startActivity(intent);

    }
}