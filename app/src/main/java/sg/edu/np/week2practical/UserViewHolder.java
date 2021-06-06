package sg.edu.np.week2practical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView name, description;
    ImageView image, imageBig;
    public UserViewHolder(View itemView){
        super(itemView);
        name = itemView.findViewById(R.id.name);
        description = itemView.findViewById(R.id.description);
        image = itemView.findViewById(R.id.userRecyclerView);
        imageBig = itemView.findViewById(R.id.image_big);
    }
}
