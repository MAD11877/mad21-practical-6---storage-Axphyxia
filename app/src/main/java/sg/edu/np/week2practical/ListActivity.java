package sg.edu.np.week2practical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    static ArrayList<User> userList = new ArrayList<User>();
    private final static String TAG= "List Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Populate list with 20 user objects
        for(int i = 0;i < 20; i++){

            userList.add( new User("Name " + randomNumber(), "Description " + randomNumber(), randomNumber(), randomBool()));
        }


        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        UserViewAdapter mAdapt = new UserViewAdapter(userList, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapt);


        //ImageView middleButton = findViewById(R.id.middleButton);

        /*middleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                userQuery();
            }
        });*/
    }

    private int randomNumber(){
        Random ranInt = new Random();
        int ranVal = ranInt.nextInt(1000000000);
        return ranVal;
    }

    private Boolean randomBool(){
        Random ranBool = new Random();
        boolean ranBol = ranBool.nextBoolean();
        return ranBol;
    }

    //Week 3 code
    /*private void userQuery(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Madness");
        builder.setTitle("Profile");
        builder.setCancelable(false);

        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int raNo = randomNumber();

                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                intent.putExtra("Number", raNo);
                startActivity(intent);

            }
        });

        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }*/

}