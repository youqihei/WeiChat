package weichat.privatecom.wwei.weichat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.dbflow.dbbean.FNameTable;

/**
 * Created by Administrator on 2019/7/9.
 */

public class CommuteAdapter extends RecyclerView.Adapter<CommuteAdapter.ViewHolder> {
    private Context context;
    private List<FNameTable> list;
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView iv_photo;
        private TextView tv_title;
        public ViewHolder(View view)
        {
            super(view);
             iv_photo = (ImageView)view.findViewById(R.id.iv_photo);
             tv_title = (TextView)view.findViewById(R.id.tv_title);
        }
    }
    public CommuteAdapter(Context context, List<FNameTable> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_commute_item,null);
        CommuteAdapter.ViewHolder viewHolder  =new CommuteAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_title.setText(list.get(position).getUsername());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
