package cegeka.scoaladevalori.ro.theultimatetaskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;

public class MenuActivity extends AppCompatActivity {

    Button mLogoutBtn;
    Button mProfileBtn;
    Button mAddActivityBtn;
    Button mListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        mLogoutBtn = (Button) findViewById(R.id.b_login);
        /*
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, LoginIn.class);
                startActivity(intent);

            }
        });
        */
        mProfileBtn = (Button) findViewById(R.id.profileBtn);
        mAddActivityBtn = (Button) findViewById(R.id.addActivityBtn);
        mLogoutBtn = (Button) findViewById(R.id.listBtn);

    }
    public void OpenNewToDoActivity(View view) {
        Intent intent = new Intent(MenuActivity.this, AddToDoActivity.class);
        startActivity(intent);
    }

    public void OpenTodoListActivity(View view) {
        Intent intent = new Intent(MenuActivity.this, ToDoListActivity.class);
        startActivity(intent);
    }

    public void OpenProfileActivity(View view) {
        Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
        startActivity(intent);

    }
}