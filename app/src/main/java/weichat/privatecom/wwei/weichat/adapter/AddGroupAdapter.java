package weichat.privatecom.wwei.weichat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.dbflow.dbbean.FNameTable;

/**
 * Created by Administrator on 2019/7/25.
 */

public class AddGroupAdapter extends RecyclerView.Adapter<AddGroupAdapter.ViewHolder> {
    private Context context;
    private List<FNameTable> list;
    private Listener listener;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView iv_photo;
        private TextView tv_title;
        private CheckBox checkBox;
        public ViewHolder(View view)
        {
            super(view);
            iv_photo = (ImageView)view.findViewById(R.id.iv_photo);
            tv_title = (TextView)view.findViewById(R.id.tv_title);
            checkBox = (CheckBox)view.findViewById(R.id.addgroup_cb_agree);
        }
    }
    public AddGroupAdapter(Context context, List<FNameTable> list,Listener listener)
    {
        this.listener = listener;
        this.context = context;
        this.list = list;
    }

    @Override
    public AddGroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_addgroup_item,null);
        AddGroupAdapter.ViewHolder viewHolder  = new AddGroupAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AddGroupAdapter.ViewHolder holder, final int position) {
        holder.tv_title.setText(list.get(position).getUsername());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    listener.itemchecked(true,position,list.get(position).getUserid());
                }
                else
                {
                    listener.itemchecked(false,position,list.get(position).getUserid());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface Listener
    {
        void itemchecked(boolean ischecked,int position,int userid);
    }
}
