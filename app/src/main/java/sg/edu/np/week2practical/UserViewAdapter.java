package sg.edu.np.week2practical;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserViewAdapter extends RecyclerView.Adapter<UserViewHolder> {

    ArrayList<User> data;
    Context context;

    public UserViewAdapter(ArrayList<User> input, Context ccontext){
        data = input;
        context =  ccontext;
    }

    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.userrecycleview,parent,false);
        return new UserViewHolder(item);
    }

    public void onBindViewHolder(UserViewHolder holder, int position){
        User user = data.get(position);
        holder.name.setText(user.name);
        holder.description.setText(user.description);

        if (user.getName().endsWith("7")) {
            holder.imageBig.setVisibility(View.VISIBLE);
        }
        else {
            holder.imageBig.setVisibility(View.GONE);
        }

        holder.image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Profile");
                        builder.setMessage(user.name);
                        builder.setNegativeButton("Close", null);
                        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context,MainActivity.class);
                                intent.putExtra("Position", position);
                                context.startActivity(intent);
                            }
                        });
                androidx.appcompat.app.AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public int getItemCount(){
        return data.size();
    }
}
