package test.com.disha.tearcher;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.ArrayList;

import test.com.disha.R;
import test.com.disha.StudentDashboardAdapter;

public class MyadaptorIT extends RecyclerView.Adapter<MyadaptorIT.ViewHolder> {
    ArrayList <String> mQuestions1 ;
    Context context;

public MyadaptorIT (ArrayList<String> mQuestions1 ,Context context )
{
    this.mQuestions1= mQuestions1;
    this.context=context;

}
    @NonNull
    @Override
    public MyadaptorIT.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.listitemit,viewGroup,
                        false);

        ViewHolder holder = new ViewHolder(view);

        return holder;    }

    @Override
    public void onBindViewHolder(@NonNull MyadaptorIT.ViewHolder viewHolder, int i) {

        String mQue1 = mQuestions1.get(i);

        viewHolder.txtQuestion1.setText(mQue1);
    }

    @Override
    public int getItemCount() {
      return  mQuestions1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtQuestion1;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestion1 = itemView.findViewById(R.id.ITStream);


        }
    }
}
