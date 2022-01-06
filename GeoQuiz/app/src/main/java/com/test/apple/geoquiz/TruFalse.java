package com.test.apple.geoquiz;

public class TruFalse {
    private int mQuestion;
    private Boolean mTrueQuestion;

    public TruFalse(int mQuestion, Boolean mTrueQuestion) {
        this.mQuestion = mQuestion;
        this.mTrueQuestion = mTrueQuestion;
    }

    public int getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public Boolean getmTrueQuestion() {
        return mTrueQuestion;
    }

    public void setmTrueQuestion(Boolean mTrueQuestion) {
        this.mTrueQuestion = mTrueQuestion;
    }
}
