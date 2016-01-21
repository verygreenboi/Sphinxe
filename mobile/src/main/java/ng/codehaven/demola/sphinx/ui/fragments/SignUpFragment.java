package ng.codehaven.demola.sphinx.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import net.glassstones.library.ui.widget.EditTextViewDrawableView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ng.codehaven.demola.sphinx.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    @InjectView(R.id.et_email)
    EditTextViewDrawableView mEmailView;
    @InjectView(R.id.til_email)
    TextInputLayout mEmailInputLayout;
    @InjectView(R.id.til_password) TextInputLayout mPasswordLayout;
    @InjectView(R.id.til_confirm) TextInputLayout mConfirmLayout;
    @InjectView(R.id.et_password)
    EditTextViewDrawableView mPasswordView;


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ButterKnife.inject(this, v);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPasswordLayout.animate().scaleX(1).scaleY(1).setDuration(300).setInterpolator(new LinearInterpolator()).start();
        mConfirmLayout.animate().scaleX(1).scaleY(1).setDuration(600).setInterpolator(new LinearInterpolator()).start();
    }
}
