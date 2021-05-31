package sg.edu.np.week2practical;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

//import stoof
public class MainActivity extends AppCompatActivity {
    private final static String TAG= "Main Activity";
    private TextView madMessage;
    private TextView madDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent receiveData = getIntent();
        int id = receiveData.getIntExtra("Position", 0);
        User user1 = ListActivity.userList.get(id);

        madMessage = findViewById(R.id.helloWorld);
        madMessage.setText(user1.getName());
        madDescription = findViewById(R.id.loremText);
        madDescription.setText("Description " + user1.getDescription());

        Button button = findViewById(R.id.button2);
        if (user1.followed == true){
            button.setText("Unfollow");
        }
        else {
            button.setText("Follow");
        }

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Button Pressed!");
                //If user has not followed
                if (user1.followed == false) {
                    button.setText("Unfollow");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                    user1.followed = true;
                } else {
                    //If has followed
                    button.setText("Follow");
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                    user1.followed = false;
                }
            }
        });

    }

    private int randomNumber(){
        Random ranInt = new Random();
        int ranVal = ranInt.nextInt(1000000000);
        return ranVal;
    }

}