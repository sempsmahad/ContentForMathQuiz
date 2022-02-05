package com.kh69.contentformathquiz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.kh69.contentformathquiz.remote.APIUtils;
import com.kh69.contentformathquiz.remote.QuestionService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentQuestionsList extends Fragment {
    private List<Question> mQuestions = new ArrayList<>();
    QuestionService mQuestionService;
    RecyclerView rv_questions;
    private Toolbar toolbar;


    public FragmentQuestionsList() {
    }

    public static FragmentQuestionsList newInstance() {
        FragmentQuestionsList fragment = new FragmentQuestionsList();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_main, container, false);

        initToolbar(root);

        rv_questions = root.findViewById(R.id.rv_questions);
        mQuestionService = APIUtils.getQuestionService();
//        getQuestionsList();

        return root;
    }

    private void initToolbar(View root) {

    }

}
