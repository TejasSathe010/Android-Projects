package com.test.apple.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mTextView;
    private Button mNextButton;
    private Button mCheatButton;
    boolean mIsCheater;

    boolean answerIsTrue;
    private String TAG = "MainActivity";
    private static final String INDEX = "index";

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart Started..");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN,false);

    }

    private TruFalse[] mQuestionBank = new TruFalse[]{
            new TruFalse(R.string.question_oceans, true),
            new TruFalse(R.string.question_mideast, false),
            new TruFalse(R.string.question_africa, false),
            new TruFalse(R.string.question_americas, true),
            new TruFalse(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate Started..");

        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(INDEX);
        }

        mTextView = (TextView)findViewById(R.id.textView);
        mNextButton = (Button)findViewById(R.id.next);
        final int question = mQuestionBank[mCurrentIndex].getmQuestion();
        mTextView.setText(question);


        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mCheatButton = (Button)findViewById(R.id.cheat_button);

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CheatActivity.class);
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,answerIsTrue);
                //startActivity(i);
                startActivityForResult(i,0);
            }
        });

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1)%mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(INDEX,mCurrentIndex);
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getmQuestion();
        mTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        answerIsTrue = mQuestionBank[mCurrentIndex].getmTrueQuestion();
        int messageResId = 0;
        if(mIsCheater){
            messageResId = R.string.judgment_toast;
        }else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        toast.show();

    }
}
