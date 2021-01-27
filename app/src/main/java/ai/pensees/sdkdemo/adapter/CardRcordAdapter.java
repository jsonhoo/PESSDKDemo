package ai.pensees.sdkdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import ai.pensees.sdkdemo.R;
import ai.pensees.sdkdemo.model.RfidRecord;

public class CardRcordAdapter extends RecyclerView.Adapter<CardRcordAdapter.ViewHolder>{

    private List<RfidRecord> rfidRecordList;

    public CardRcordAdapter(List<RfidRecord> rfidRecordList) {
        this.rfidRecordList = rfidRecordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.crushView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                RfidRecord rfidRecord = rfidRecordList.get(position);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RfidRecord rfidRecord = rfidRecordList.get(position);

    }

    @Override
    public int getItemCount() {
        return rfidRecordList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        View crushView;
        TextView tv_user_name;
        TextView tv_user_card;
        TextView tv_mark_time;
        TextView tv_state;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            crushView = itemView;
            tv_state = (TextView) itemView.findViewById(R.id.tv_state);
            tv_user_name = (TextView) itemView.findViewById(R.id.tv_user_name);
            tv_user_card = (TextView) itemView.findViewById(R.id.tv_user_card);
            tv_mark_time = (TextView) itemView.findViewById(R.id.tv_mark_time);
        }
    }

}