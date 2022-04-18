package com.kh69.contentformathquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private Context mContext;
    private List<Question> mQuestions;
    private OnClickListener onClickListener = null;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public QuestionAdapter(Context context, List<Question> questions) {
        this.mContext = context;
        this.mQuestions = questions;
    }

    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {

        final Question question = mQuestions.get(position);
        holder.id.setText(question.getId());
        holder.year.setText(String.valueOf(question.getYear()));

        if(!question.getEdited()){
            holder.lyt_parent.setOnClickListener(v -> {
                Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
                if (onClickListener == null) return;
                onClickListener.onItemClick(v, question, position);
            });
        }else{
            holder.lyt_parent.setVisibility(View.GONE); //https://stackoverflow.com/a/52786556/8872691
        }
    }
    public Question getItem(int position) {
        return mQuestions.get(position);
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView id, year;
        public ImageView image;
        public RelativeLayout lyt_checked, lyt_image;
        public View lyt_parent;

        public ViewHolder(View view) {
            super(view);

            id = view.findViewById(R.id.from);
            year = view.findViewById(R.id.year);
            lyt_parent = view.findViewById(R.id.lyt_parent_item);
        }
    }

    public interface OnClickListener {
        void onItemClick(View view, Question obj, int pos);
        void onItemLongClick(View view, Question obj, int pos);
    }

}
