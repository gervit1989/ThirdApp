package com.skillbranch.thirdapp.ui.custom_views;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.skillbranch.thirdapp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthPanel extends LinearLayout {

    private static final String TAG = "AuthPanel";

    public static final int LOGIN_STATE = 0;
    public static final int IDLE_STATE = 1;

    private int mCustomState = 1;

    /**
     * Блок авторизации
     */
    @BindView(R.id.auth_card)
    CardView mAuthCard;

    /**
     * Поле изменения email
     */
    @BindView(R.id.login_mail_et)
    EditText mMailEdit;

    /**
     * Поле изменения pass
     */
    @BindView(R.id.login_pass_et)
    EditText mPassEdit;

    /**
     * Кнопка Войти
     */
    @BindView(R.id.login_start_btn)
    Button mStartBtn;

    /**
     * Кнопка Показать каталог
     */
    @BindView(R.id.login_show_btn)
    Button mShowBtn;

    /**
     * Конструктор
     * @param context
     * @param attrs
     */
    public AuthPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // TODO: 22-10-2016 validate and save state for email input

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        showViewFromState();

        mPassEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (checkUserPassInput()){
                    mPassEdit.setError("!");
                }
            }
        });
        mMailEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (checkUserMailInput()){
                    mMailEdit.setError("!");
                }
            }
        });
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.state = mCustomState;
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState)state;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCustomState(savedState.state);
    }

    public void setCustomState(int customState) {
        mCustomState = customState;
        showViewFromState();
    }

    private void showLoginState(){
        mAuthCard.setVisibility(VISIBLE);
        mShowBtn.setVisibility(GONE);
    }

    private void showIdleState(){
        mAuthCard.setVisibility(GONE);
        mShowBtn.setVisibility(VISIBLE);
    }

    private void showViewFromState(){
        if (mCustomState == LOGIN_STATE){
            showLoginState();
        }
        else {
            showIdleState();
        }
    }

    public String getUserEmail(){
        return String.valueOf(mMailEdit.getText());
    }

    public String getUserPass(){
        return String.valueOf(mPassEdit.getText());
    }

    public void setUserEmail(String mail){
        mMailEdit.setText(mail);
    }

    public void setUserPass(String pass){
        mPassEdit.setText(pass);
    }

    public boolean isIdle(){
        return mCustomState == IDLE_STATE;
    }

    public boolean checkUserMailInput(){
        boolean isErr = false;
        if (getUserEmail()==null){
            isErr = true;
        }else {
            String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(getUserEmail());
            if(!matcher.matches()){
                isErr = true;
            }
        }
        return isErr;
    }

    public boolean checkUserPassInput(){
        boolean isErr = false;
        if (getUserPass()==null){
            isErr = true;
        }else {
            if(getUserPass().length()<8){
                isErr = true;
            }
        }
        return isErr;
    }

    static class SavedState extends BaseSavedState{

        private int state;

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>(){
            public SavedState createFromParcel(Parcel in){
                return new SavedState(in);
            }

            public SavedState[] newArray(int size){
                return new SavedState[size];
            }
        };

        public SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in){
            super(in);
            state = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(state);
        }
    }
}
