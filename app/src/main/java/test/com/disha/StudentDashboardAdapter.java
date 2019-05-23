





















































































































package test.com.disha;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentDashboardAdapter extends RecyclerView.Adapter<StudentDashboardAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mQuestions = new ArrayList<>();

    public StudentDashboardAdapter(Context mContext, ArrayList<String> mQuestions) {
        this.mContext = mContext;
        this.mQuestions = mQuestions;
    }



    @NonNull
    @Override
    public StudentDashboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_student_dashboard,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }



    @Override
    public void onBindViewHolder(@NonNull StudentDashboardAdapter.ViewHolder viewHolder, int i) {

        String mQue = mQuestions.get(i);
        viewHolder.txtQuestion.setText(mQue);
    }



    @Override
    public int getItemCount() {
        return mQuestions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtQuestion;
        RelativeLayout adapQuestion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            adapQuestion = itemView.findViewById(R.id.adapQuestion);
        }
    }
}


