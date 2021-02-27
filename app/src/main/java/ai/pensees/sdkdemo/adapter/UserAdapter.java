package ai.pensees.sdkdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ai.pensees.sdkdemo.R;
import ai.pensees.sdkdemo.model.UserModel;
import ai.pensees.sdkdemo.utils.TimeUtils;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private List<UserModel> userModelList;

    public UserAdapter(List<UserModel> userModelList) {
        this.userModelList = userModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.crushView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UserModel userModel = userModelList.get(position);


            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel userModel = userModelList.get(position);
        Picasso.get().load(userModel.getPhotoServerUrl()).into( holder.img_avatar);

        holder.tv_user_name.setText(userModel.getUserName());
        holder.tv_mark_time.setText(TimeUtils.conversionTime(""+userModel.getCreateTime()));
        holder.tv_user_card.setText(userModel.getCarNo());
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        View crushView;
        ImageView img_avatar;
        TextView tv_user_name;
        TextView tv_user_card;
        TextView tv_mark_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            crushView = itemView;
            img_avatar = (ImageView) itemView.findViewById(R.id.img_avatar);
            tv_user_name = (TextView) itemView.findViewById(R.id.tv_user_name);
            tv_user_card = (TextView) itemView.findViewById(R.id.tv_user_card);
            tv_mark_time = (TextView) itemView.findViewById(R.id.tv_mark_time);
        }
    }

}
