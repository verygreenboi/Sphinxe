package ng.codehaven.demola.sphinx.ui;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cat.ppicas.customtypeface.CustomTypeface;
import cat.ppicas.customtypeface.CustomTypefaceFactory;
import ng.codehaven.demola.sphinx.Common;
import ng.codehaven.demola.sphinx.R;
import ng.codehaven.demola.sphinx.ui.fragments.LoginFragment;
import ng.codehaven.demola.sphinx.ui.fragments.SignUpFragment;

public class DispatchActivity extends AppCompatActivity implements LoginFragment.OnLoginFragmentInteraction, View.OnClickListener {
    protected SharedPreferences sp;

    @InjectView(R.id.container)
    FrameLayout mContainer;
    @InjectView(R.id.login_signup)
    Button mLoginSignUpBtn;
    @InjectView(R.id.tv_forgot_password)
    TextView mForgot;
    @InjectView(R.id.tv_sign_up)
    TextView mSignUp;
    @InjectView(R.id.tv_login) TextView mLogin;

    private String mEmail, mPassword;
    private int currentFragment = 0;
    private boolean firstRun;

    private FragmentManager fm;

    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getLayoutInflater().setFactory(new CustomTypefaceFactory(
                this, CustomTypeface.getInstance()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
        ButterKnife.inject(this);

        sp = getSharedPreferences(Common.SHARED_PREF, Context.MODE_PRIVATE);

        switch (sp.getInt(Common.CURRENT_FRAGMENT, 0)){
            case 0:
                currentFragment = 0;
                mFragment = new LoginFragment();
                break;
            case 1:
                currentFragment = 1;
                mFragment = new SignUpFragment();
                break;
        }

        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, mFragment).commit();

        firstRun = true;

        mForgot.setOnClickListener(this);
        mForgot.setAlpha(0f);

        mSignUp.setOnClickListener(this);
        mLogin.setOnClickListener(this);

        mLoginSignUpBtn.setAlpha(0f);
        mLoginSignUpBtn.animate().translationY(-150).setDuration(500).setInterpolator(new LinearInterpolator()).alpha(1f).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                mLoginSignUpBtn.setOnClickListener(DispatchActivity.this);

                mForgot.animate().alpha(1f).setInterpolator(new LinearInterpolator()).setDuration(500).start();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

        }).start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Common.CURRENT_FRAGMENT, currentFragment).apply();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onValidEmail(String s) {
        mEmail = s;
    }

    @Override
    public void onPasswordLengthValid(String password) {
        mPassword = password;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.login_signup:
                if (!TextUtils.isEmpty(mEmail) || !TextUtils.isEmpty(mPassword)) {
//                    Toast.makeText(this, "Good!!!", Toast.LENGTH_LONG).show();
                    switch (currentFragment) {
                        // TODO: Implement login
                        case 0:
                            break;
                        // TODO: Implement sign up
                        case 1:
                            break;
                    }

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean(Common.FIRST_RUN_KEY, false).apply();
                    startActivity(new Intent(this, HomeActivity.class));
                    finish();
                }
                break;
            case R.id.tv_sign_up:
                if (currentFragment != 1) {
                    loadSignUpFragment();
                    firstRun = false;
                } else {
                    mSignUp.startAnimation(getShakeAnim());
                }
                break;
            case R.id.tv_login:
                if (currentFragment != 0){
                    loadLoginFragment();
                } else {
                    mLogin.startAnimation(getShakeAnim());
                }
                break;
        }
    }

    private void loadLoginFragment() {
        mLoginSignUpBtn.animate().translationY(-150).setDuration(500).setInterpolator(new LinearInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                fm.beginTransaction().replace(R.id.container, new LoginFragment()).commit();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginSignUpBtn.setText("Login");
                mForgot.animate().alpha(1f).setInterpolator(new LinearInterpolator()).setDuration(500).start();
                currentFragment = 0;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }

    private void loadSignUpFragment() {
        mForgot.animate().alpha(0f).setInterpolator(new LinearInterpolator()).setDuration(300).start();
        mLoginSignUpBtn.animate().translationY(0).setDuration(500).setInterpolator(new LinearInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginSignUpBtn.setText("Next");
                fm.beginTransaction().replace(R.id.container, new SignUpFragment()).commit();
                currentFragment = 1;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }

    private Animation getShakeAnim(){
        return AnimationUtils.loadAnimation(this, R.anim.shake);
    }

}
