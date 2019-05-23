package test.com.disha;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static test.com.disha.StudentDashboard.flagForMeeting;


public class ProfListAdapter extends RecyclerView.Adapter<ProfListAdapter.ViewHolder> {
    Context context;
    ArrayList<ProfListItem> mList;
    int index;
  //  String mProfPhone;

    public ProfListAdapter(Context context,
                           ArrayList<ProfListItem> mList)
    {
        this.context = context;

        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.prof_list,viewGroup,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

         String mProfName,mCollegeName,mResearch;


        mCollegeName=mList.get(i).mCollegeName;

        mResearch=mList.get(i).mResearch;

        mProfName=mList.get(i).mProfName;

       // String mProfPhone= mList.get(i).mProfNum;

        index = i;


        viewHolder.txtResearch.setText(mResearch);

        viewHolder.txtProfName.setText(mProfName);

        viewHolder.txtCollegName.setText(mCollegeName);

        viewHolder.linearLayoutProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                index = viewHolder.getAdapterPosition();

                String mProfPhone = mList.get(index).mProfNum;

                if(flagForMeeting.equals("true")){

                    Intent intent = new Intent(context,Meeting.class);

                    intent.putExtra("mProfNum",mProfPhone);

                    context.startActivity(intent);

                }

                else
                    {

                    Intent intent = new Intent(context,AskQuestion.class);

                    intent.putExtra("mProfNum",mProfPhone);

                    context.startActivity(intent);

                }
            }
        }
        );
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtProfName , txtCollegName, txtResearch;
        LinearLayout linearLayoutProf;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtResearch = (TextView)itemView.findViewById(R.id.txtResearch);
            txtProfName = (TextView)itemView.findViewById(R.id.txtProfName);
            txtCollegName = (TextView)itemView.findViewById(R.id.txtCollegeName);
            linearLayoutProf = (LinearLayout)itemView.findViewById(R.id.linearLayoutProf);
        }
    }
}
