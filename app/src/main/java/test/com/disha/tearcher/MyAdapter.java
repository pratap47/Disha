package test.com.disha.tearcher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.com.disha.R;

public  class MyAdapter extends RecyclerView.Adapter<MyAdapter.Viewholder> {
    List <ListItem> list;
    Context context;

    public MyAdapter(List<ListItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.Viewholder holder, int position) {
        ListItem l = list.get(position);
        holder.Name.setText(l.getname());
        holder.purpose.setText(l.getpupose());
        holder.studentphoneno.setText(l.getStudentphoneno());

    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class Viewholder extends RecyclerView.ViewHolder{
        TextView Name;
        TextView purpose;
        TextView studentphoneno;
        public Viewholder(View itemView) {
            super(itemView);
            Name = (TextView)itemView.findViewById(R.id.txtSubcode);
            purpose =(TextView)itemView.findViewById(R.id.txtSub);
            studentphoneno=(TextView)itemView.findViewById(R.id.txtname);

        }
    }
}
