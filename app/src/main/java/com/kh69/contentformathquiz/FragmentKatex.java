package com.kh69.contentformathquiz;

import static java.util.regex.Pattern.UNICODE_CASE;
import static java.util.regex.Pattern.UNICODE_CHARACTER_CLASS;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.kh69.contentformathquiz.remote.APIUtils;
import com.kh69.contentformathquiz.remote.QuestionService;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import katex.hourglass.in.mathlib.MathView;
import retrofit2.Call;
import retrofit2.Callback;

@RequiresApi(api = Build.VERSION_CODES.N)
public class FragmentKatex extends Fragment implements View.OnClickListener {
    private EditText mTvQuestion;

    private Button mRun;
    private Button mBtnText;
    private Button mBtnFunction;
    private Button mBtnReturn;
    private Button mBtnIndent;
    private Button mBtnCenter;
    private Button mBtnBold;
    private Button mBtnSaveQuestion;
    private Button mBtnSaveAnswer;

    private ImageView mBgImage;
    private MathView mKVAnswer;
    private NestedScrollView mNestedScrollView;
    private Question mQuestion;

    QuestionService mQuestionService;


    static final Pattern TRIM_UNICODE_PATTERN = Pattern.compile("^\\p{Blank}*(.*)\\p{Blank}$", UNICODE_CASE);
    static final Pattern SPLIT_SPACE_UNICODE_PATTERN = Pattern.compile("\\p{Blank}", UNICODE_CASE);

    public FragmentKatex() {
    }

    public static FragmentKatex newInstance() {
        FragmentKatex fragment = new FragmentKatex();
        return fragment;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_main, container, false);

        mQuestionService = APIUtils.getQuestionService();

        mQuestion = TabsActivity.sQuestion;
        String question = MainActivity.Companion.getValue();
        mTvQuestion = root.findViewById(R.id.tv_katex);
        mBtnSaveQuestion = root.findViewById(R.id.btn_question);
        mBtnSaveAnswer = root.findViewById(R.id.btn_answer);

        if (!mQuestion.getKatex_question().isEmpty()){
            if (!mQuestion.getKatex_answer().isEmpty()){
                mTvQuestion.setText(mQuestion.getKatex_question());
                mBtnSaveQuestion.setEnabled(false);
                mBtnSaveAnswer.setEnabled(false);

            }else{
                mTvQuestion.setText(mQuestion.getAnswer());
                mBtnSaveQuestion.setEnabled(false);
            }
        }else{
            mTvQuestion.setText(mQuestion.getText());
            mBtnSaveAnswer.setEnabled(false);

        }

        mRun = root.findViewById(R.id.btn_run);
        mBtnText = root.findViewById(R.id.btn_text);
        mBtnFunction = root.findViewById(R.id.btn_function);
        mBtnReturn = root.findViewById(R.id.btn_return);
        mBtnIndent = root.findViewById(R.id.btn_indent);
        mBtnCenter = root.findViewById(R.id.btn_center);
        mBtnBold = root.findViewById(R.id.btn_bold);


        mBgImage = root.findViewById(R.id.bg_image);
        mKVAnswer = root.findViewById(R.id.kv_answer);
        mNestedScrollView = root.findViewById(R.id.scroll_view);

        mTvQuestion.setOnTouchListener((view, motionEvent) -> {
            if (view.getId() == R.id.tv_katex) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_UP:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
            }
            return false;
        });

        mRun.setOnClickListener(view -> {
            mKVAnswer.setVisibility(View.VISIBLE);
            mBgImage.setVisibility(View.GONE);
            render();
        });
        mBtnText.setOnClickListener(view -> {
            String selectedText = getSelection();
            if (!selectedText.isEmpty()) {
//                decorateSelection(selectedText);
//                String[] trimAndSplitUnicode = trimSplitUnicodeBySpace(getActivity(),selectedText);
//                String[] trimAndSplitUnicode = selectedText.trim().split("\\s+");
//                String splited = null;
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    splited = Arrays.stream(trimAndSplitUnicode).collect(Collectors.joining("\\space ")); // https://stackoverflow.com/a/30220543/8872691
//                } else {
//                    splited = TextUtils.join("\\space ", trimAndSplitUnicode); // https://stackoverflow.com/a/33803041/8872691
//                }

                //https://stackoverflow.com/a/20887690/8872691
                String inputString = mTvQuestion.getText().toString();
                String modifiedString = getResources().getString(R.string.replace_text, decorateSelection(selectedText));
//                String selectionModifiedString = inputString.replace(selectedText, modifiedString);
//                mTvQuestion.setText(selectionModifiedString);

                int start = Math.max(mTvQuestion.getSelectionStart(), 0);
                int end = Math.max(mTvQuestion.getSelectionEnd(), 0);
                mTvQuestion.getText().replace(Math.min(start, end), Math.max(start, end),
                        modifiedString, 0, (modifiedString).length());


            }
        });
        mBtnFunction.setOnClickListener(view -> {
            String selectedText = getSelection();
            if (!selectedText.isEmpty()) {
                replaceSelection(R.string.replace_function, selectedText);
            }
        });

        mBtnReturn.setOnClickListener(this::onClick);
        mBtnIndent.setOnClickListener(this::onClick);
        mBtnCenter.setOnClickListener(this::onClick);
        mBtnBold.setOnClickListener(this::onClick);
        mBtnSaveQuestion.setOnClickListener(this::onClick);
        mBtnSaveAnswer.setOnClickListener(this::onClick);
        return root;
    }

    private String decorateSelection(String selectedText) {
        String[] trimAndSplitUnicode = selectedText.trim().split("\\s+");
        String decorated = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            decorated = Arrays.stream(trimAndSplitUnicode).collect(Collectors.joining("\\space ")); // https://stackoverflow.com/a/30220543/8872691
        } else {
            decorated = TextUtils.join("\\space ", trimAndSplitUnicode); // https://stackoverflow.com/a/33803041/8872691
        }
        return decorated;

//        //https://stackoverflow.com/a/20887690/8872691
//        String inputString=mTvQuestion.getText().toString();
//        Resources res = getResources();
//        String modifiedString = res.getString(R.string.replace_text, splited);
//
//        String selectionModifiedString=inputString.replace(selectedText,modifiedString);
//        mTvQuestion.setText(selectionModifiedString);

    }

    private void replaceSelection(int place_holder, String selectedText) {
        //https://stackoverflow.com/a/20887690/8872691
        String inputString = mTvQuestion.getText().toString();
        Resources res = getResources();
        String modifiedString = res.getString(place_holder, selectedText);
        String selectionModifiedString = inputString.replace(selectedText, modifiedString);
        mTvQuestion.setText(selectionModifiedString);
    }

    private String getSelection() {
        int startSelection = mTvQuestion.getSelectionStart();
        int endSelection = mTvQuestion.getSelectionEnd();
        return mTvQuestion.getText().toString().substring(startSelection, endSelection);
    }

    private void replaceSelectionWith(String textToInsert) {
        int start = Math.max(mTvQuestion.getSelectionStart(), 0);
        int end = Math.max(mTvQuestion.getSelectionEnd(), 0);
        mTvQuestion.getText().replace(Math.min(start, end), Math.max(start, end),
                textToInsert, 0, (textToInsert).length());
    }

    private void render() {
        mKVAnswer.setDisplayText(mTvQuestion.getText().toString());
    }

    public static String[] trimSplitUnicodeBySpace(Activity act, String str) {
        Toast.makeText(act, "trimSplitUnicodeBySpace: " + str, Toast.LENGTH_SHORT).show();
//        Log.e("SEMPS", "trimSplitUnicodeBySpace: ", str);
//        Matcher trimMatcher = TRIM_UNICODE_PATTERN.matcher(str);
//        boolean ignore = trimMatcher.matches(); // always true but must be called since it does the actual matching/grouping
//        return SPLIT_SPACE_UNICODE_PATTERN.split(Objects.requireNonNull(trimMatcher.group(1)));
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_question:
                mQuestion.setKatex_question(mTvQuestion.getText().toString());
                updateQuestion(mQuestion.getId(),mQuestion);
                break;
            case R.id.btn_answer:
                mQuestion.setKatex_answer(mTvQuestion.getText().toString());
                mQuestion.setEdited(true);
                updateQuestion(mQuestion.getId(),mQuestion);
                break;
            case R.id.btn_return:
                replaceSelectionWith(getResources().getString(R.string.replace_return));
                break;
            case R.id.btn_indent:
                replaceSelectionWith(getResources().getString(R.string.replace_indent));
                break;
            case R.id.btn_center:
                String selectedText = getSelection();
                if (!selectedText.isEmpty()) {
                    replaceSelection(R.string.replace_center, selectedText);
                }
                break;
            case R.id.btn_bold:
                String selectedText2 = getSelection();
                if (!selectedText2.isEmpty()) {
                    String inputString = mTvQuestion.getText().toString();
                    String modifiedString = getResources().getString(R.string.replace_bold, decorateSelection(selectedText2));
                    String selectionModifiedString = inputString.replace(selectedText2, modifiedString);
                    mTvQuestion.setText(selectionModifiedString);
                }
                break;
        }
    }
    public void updateQuestion(String id, Question question){
        Call<Question> call = mQuestionService.updateQuestion(id, question);
        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, retrofit2.Response<Question> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Question updated successfully!", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    public void createQuestion(Question question){
        Call<Question> call = mQuestionService.createQuestion(question);
        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, retrofit2.Response<Question> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(), "Question created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    public void deleteQuestion(String id){
        Call<Question> call = mQuestionService.deleteQuestion(id);
        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, retrofit2.Response<Question> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(), "Question deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
