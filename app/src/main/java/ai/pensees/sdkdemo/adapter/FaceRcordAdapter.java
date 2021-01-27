package ai.pensees.sdkdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ai.pensees.sdkdemo.R;
import ai.pensees.sdkdemo.model.FaceRecord;

public class FaceRcordAdapter extends RecyclerView.Adapter<FaceRcordAdapter.ViewHolder>{

    private List<FaceRecord> faceRecordList;

    public FaceRcordAdapter(List<FaceRecord> faceRecordList) {
        this.faceRecordList = faceRecordList;
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
                FaceRecord faceRecord = faceRecordList.get(position);


            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FaceRecord faceRecord = faceRecordList.get(position);
    }

    @Override
    public int getItemCount() {
        return faceRecordList.size();
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
