package cegeka.scoaladevalori.ro.theultimatetaskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginIn extends AppCompatActivity {

    EditText mUserEt;
    EditText mPassEt;
    Button mLoginBtn;
    User user = null;
    List<User> users;

    private static final String USERNAME = "vianu_user";
    private static final String PASSWORD = "vianu_pass";
    public static ArrayList<ToDoItem> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // onCreate e metoda
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);

        /**
         * buton simulare crush
        Button crashButton = new Button(this);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Crashlytics.getInstance().crash(); // Force a crash
            }
        });
        addContentView(crashButton,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        */

        mUserEt = (EditText) findViewById(R.id.input_usern);
        mPassEt = (EditText) findViewById(R.id.input_userp);
        mLoginBtn = (Button) findViewById(R.id.b_login);
        users = new ArrayList<>();
        mList = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(openFileInput("user.txt"));
            Object obj = null;
            while ((obj = ois.readObject()) != null){
                User u =(User) obj;
                users.add(u);}
            ois.close();
        }catch (IOException ex){
            Toast.makeText(LoginIn.this, "Nu s-a putut deschide fisierul",Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException ex){
            Toast.makeText(LoginIn.this, "Problema la citirea din fisier",Toast.LENGTH_LONG).show();

        }
        mLoginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                for( User u : users) {
                    if ((mUserEt.getText().toString().equals(USERNAME) &&
                            mPassEt.getText().toString().equals(PASSWORD)) ||
                            (mUserEt.getText().toString().equals(user.username) &&
                                    mPassEt.getText().toString().equals(user.password))) {

                        Intent intent = new Intent(LoginIn.this, MenuActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("user", user);
                        //intent.putExtra("user", user);
                        intent.putExtras(b);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginIn.this,
                                "Invalid Credentials",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK) {
            //user = (User)data.getSerializableExtra("user");
            List<User> users = null;
            try {
                ObjectInputStream ois = new ObjectInputStream(openFileInput("user.txt"));
                while (ois.readObject() != null){
                    User u =(User) ois.readObject();
                    users.add(u);}
                ois.close();
            }catch (IOException ex){

            } catch (ClassNotFoundException ex){

            }
            Toast.makeText(LoginIn.this, users.size()+"", Toast.LENGTH_LONG).show();
        }
    }

    public void OpenRegisterActivity(View view) {
        Intent intent = new Intent(LoginIn.this,
                Register.class);
        startActivityForResult(intent, 1);
    }
}
