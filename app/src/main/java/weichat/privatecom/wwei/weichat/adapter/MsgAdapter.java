package weichat.privatecom.wwei.weichat.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.bean.ChatMessageBean;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<ChatMessageBean> msgList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;

        public ViewHolder(View itemView) {
            super(itemView);
            leftLayout=(LinearLayout) itemView.findViewById(R.id.left_layout);
            rightLayout=(LinearLayout)itemView.findViewById(R.id.right_layout);
            leftMsg=(TextView) itemView.findViewById(R.id.left_msg);
            rightMsg=(TextView) itemView.findViewById(R.id.right_msg);
        }
    }

    public MsgAdapter(List<ChatMessageBean> msgList) {
        this.msgList = msgList;
    }

    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_chatfriend_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgAdapter.ViewHolder holder, int position) {
        ChatMessageBean chatMessageBean =msgList.get(position);
        if(msgList.get(position).isMine()) {
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightMsg.setText(chatMessageBean.getMessage());
        }
        else
        {
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(chatMessageBean.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }
}