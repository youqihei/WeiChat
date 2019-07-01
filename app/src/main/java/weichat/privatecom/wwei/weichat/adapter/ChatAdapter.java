package weichat.privatecom.wwei.weichat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.bean.ChatBean;
import weichat.privatecom.wwei.weichat.bean.ChatRecordBean;

/**
 * Created by Administrator on 2019/6/18.
 */

public class ChatAdapter  extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{

    private Context mContext;
    private List<ChatRecordBean.Group> grouplist ;
    private List<ChatRecordBean.User> userlist ;
    public ChatAdapter(Context context,List<ChatRecordBean.Group> grouplist,List<ChatRecordBean.User> userlist )
    {
        mContext = context;
        this.grouplist = grouplist;
        this.userlist = userlist;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_chat_item,null);
        ViewHolder viewHolder  =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
           holder.tv_title.setText(grouplist.get(position).getGroupid());
           holder.tv_content.setText(grouplist.get(position).getGroupid());
    }

    @Override
    public int getItemCount() {
        return grouplist.size()+userlist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        private TextView tv_title;
        private TextView tv_content;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.iv_photo);
            tv_title = (TextView)itemView.findViewById(R.id.tv_title);
            tv_content = (TextView)itemView.findViewById(R.id.tv_content);
        }
    }
}
