package weichat.privatecom.wwei.weichat.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
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
    private List<ChatBean> list ;
    private ChatCallback chatcallback;
    public ChatAdapter(Context context,List<ChatBean>list,ChatCallback chatcallback)
    {
        this.chatcallback = chatcallback;
        mContext = context;
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_chat_item,null);
        ViewHolder viewHolder  =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
           holder.tv_title.setText(list.get(position).getTitle());
           holder.tv_content.setText(list.get(position).getContent());
           holder.constraint_bottom.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   chatcallback.startnewFragment(list.get(position).getType(),list.get(position).getTitle());
               }
           });
    }
   public interface  ChatCallback
    {
        void startnewFragment(int type,String friendname);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        private TextView tv_title;
        private TextView tv_content;
        private ConstraintLayout constraint_bottom;
        public ViewHolder(View itemView) {
            super(itemView);
            constraint_bottom = (ConstraintLayout)itemView.findViewById(R.id.constraint_bottom);
            imageView = (ImageView)itemView.findViewById(R.id.iv_photo);
            tv_title = (TextView)itemView.findViewById(R.id.tv_title);
            tv_content = (TextView)itemView.findViewById(R.id.tv_content);
        }
    }
}
