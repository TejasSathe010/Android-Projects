package com.test.apple.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.test.apple.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.test.apple.geoquiz.answer_shown";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        
    }

    boolean mAnswerIsTrue;
    Button mShowAnswer;
    TextView mAnswerTextview;
    private void setAnswerShownResult(boolean isAnswerShown){
        Intent dt = new Intent();
        dt.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,dt);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        mShowAnswer = (Button)findViewById(R.id.showanswerbutton);
        mAnswerTextview = (TextView)findViewById(R.id.answer_text);

        setAnswerShownResult(false);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue){
                    mAnswerTextview.setText(R.string.true_button);
                }else {
                    mAnswerTextview.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
    }

}
