package test.com.disha;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ReqMeetingAdapter extends RecyclerView.Adapter<ReqMeetingAdapter.ViewHolder> {
    Context context;
    ArrayList<ReqMeetingItem> mList;

    public ReqMeetingAdapter(Context applicationContext, ArrayList<ReqMeetingItem> mList) {
        this.context = applicationContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.request_meeting,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String mPurpose,mStatus;
        mPurpose= mList.get(i).mPurpose;
        mStatus = mList.get(i).mStatus;
        viewHolder.txtStatus.setText(mStatus);
        viewHolder.txtPurpose.setText(mPurpose);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtStatus, txtPurpose;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPurpose = (TextView)itemView.findViewById(R.id.txtPurpose);
            txtStatus=(TextView)itemView.findViewById(R.id.txtStatus);
        }
    }
}
