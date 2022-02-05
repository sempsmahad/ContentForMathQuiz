package com.kh69.contentformathquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kh69.contentformathquiz.remote.APIUtils;
import com.kh69.contentformathquiz.remote.QuestionService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionListActivity extends AppCompatActivity {

    private List<Question> mQuestions = new ArrayList<>();
    QuestionService mQuestionService;
    RecyclerView rv_questions;
    private Toolbar toolbar;
    private QuestionAdapter mQuestionAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_question_list);
//        parent_view = findViewById(R.id.lyt_parent);
        initToolbar();
        initComponent();
    }

    private void initComponent() {

        mQuestionService = APIUtils.getQuestionService();
        rv_questions = findViewById(R.id.rv_questions);
        rv_questions.setLayoutManager(new LinearLayoutManager(this));
        rv_questions.setHasFixedSize(true);


        mQuestionAdapter = new QuestionAdapter(this, mQuestions);
////        Toast.makeText(QuestionListActivity.this, ""+mQuestions.size(), Toast.LENGTH_SHORT).show();
        rv_questions.setAdapter(mQuestionAdapter);
        getQuestionsList();
//        mQuestionAdapter.setOnClickListener(new QuestionAdapter.OnClickListener() {
//            @Override
//            public void onItemClick(View view, Question obj, int pos) {
//                    Question question = mQuestionAdapter.getItem(pos);
//                    TabsActivity.sQuestion = question;
//                    Toast.makeText(QuestionListActivity.this, ""+question.getId(), Toast.LENGTH_SHORT).show();
//
//                startActivity(new Intent(QuestionListActivity.this,TabsActivity.class));
//            }
//
//            @Override
//            public void onItemLongClick(View view, Question obj, int pos) {
//
//            }
//        });

    }

    private void getQuestionsList() {
        Call<com.kh69.contentformathquiz.Response> call = mQuestionService.getQuestions();
//        final ArrayList<Question>[] questions = new ArrayList[]{new ArrayList<>()};
        call.enqueue(new Callback<com.kh69.contentformathquiz.Response>() {

            @Override
            public void onResponse(Call<com.kh69.contentformathquiz.Response> call, Response<com.kh69.contentformathquiz.Response> response) {
                if(response.isSuccessful()){
                    mQuestions = response.body().getAlldata();
                    mQuestionAdapter = new QuestionAdapter(QuestionListActivity.this, mQuestions);
                    rv_questions.setAdapter(mQuestionAdapter);

                    mQuestionAdapter.setOnClickListener(new QuestionAdapter.OnClickListener() {
                        @Override
                        public void onItemClick(View view, Question obj, int pos) {
                            Question question = mQuestionAdapter.getItem(pos);
                            TabsActivity.sQuestion = question;
                            Toast.makeText(QuestionListActivity.this, ""+question.getId(), Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(QuestionListActivity.this,TabsActivity.class));
                        }

                        @Override
                        public void onItemLongClick(View view, Question obj, int pos) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<com.kh69.contentformathquiz.Response> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Questions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.red_600);

    }
}
