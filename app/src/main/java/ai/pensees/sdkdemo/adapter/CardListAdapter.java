package ai.pensees.sdkdemo.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ai.pensees.sdkdemo.R;
import ai.pensees.sdkdemo.model.CardModel;
import ai.pensees.sdkdemo.model.CardRecordModel;
import ai.pensees.sdkdemo.utils.TimeUtils;
import androidx.recyclerview.widget.RecyclerView;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.MyHolder> {

    private List<CardModel> mList;//数据源

    private ViewGroup viewGroup;

    public CardListAdapter(List<CardModel> list) {
        mList = list;
    }

    //创建ViewHolder并返回，后续item布局里控件都是从ViewHolder中取出
    @Override
    public MyHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        viewGroup = parent;
        //将我们自定义的item布局R.layout.tv_content转换为View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_record, parent, false);
        //将view传递给我们自定义的ViewHolder
        MyHolder holder = new MyHolder(view);
        //返回这个MyHolder实体
        return holder;
    }

    //通过方法提供的ViewHolder，将数据绑定到ViewHolder中
    @Override
    public void onBindViewHolder(MyHolder holder, @SuppressLint("RecyclerView") final int position) {
        final CardModel cardModel = mList.get(position);
        holder.tv_card_no.setText("CardNo:" + cardModel.getCardNo());
        holder.tv_userid.setText("UID:" + cardModel.getUserId());
        holder.tv_time.setText(TimeUtils.timeStamp2Date(cardModel.getCreateTime()));
    }

    //获取数据源总的条数
    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 自定义的ViewHolder
     */
    class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_card_no;
        TextView tv_userid;
        TextView tv_time;

        public MyHolder(View itemView) {
            super(itemView);
            tv_card_no = itemView.findViewById(R.id.tv_card_no);
            tv_userid = itemView.findViewById(R.id.tv_userid);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
