package xyz.aungpyaephyo.padc.myanmarattractions.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.aungpyaephyo.padc.myanmarattractions.R;
import xyz.aungpyaephyo.padc.myanmarattractions.controllers.ControllerAccountControl;
import xyz.aungpyaephyo.padc.myanmarattractions.utils.MyanmarAttractionsConstants;
import xyz.aungpyaephyo.padc.myanmarattractions.views.PasswordVisibilityListener;

/**
 * Created by aung on 7/15/16.
 */
public class LoginFragment extends Fragment {
    @BindView(R.id.lbl_login_title)
    TextView lblLoginTitle;

    @BindView(R.id.et_login_email)
    EditText etLoginEmail;

    @BindView(R.id.et_login_password)
    EditText etLoginPassword;

    @BindView(R.id.goToPasswrod)
    TextView goToPasswrod;

    @BindView(R.id.goToRegister)
    TextView goToRegister;

    private ControllerAccountControl mControllerAccountControl;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mControllerAccountControl = (ControllerAccountControl) context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, rootView);


        goToPasswrod.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getContext(),"Go to password forget",Toast.LENGTH_SHORT).show();
            }
        });

        goToRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getContext(),"Go to Register",Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = getFragmentManager();
                RegisterFragment registerFragment = RegisterFragment.newInstance();
                fragmentManager.beginTransaction()
                        .replace(R.id.fl_container, registerFragment)
                        .commit();
            }
        });

        lblLoginTitle.setText(Html.fromHtml(getString(R.string.lbl_login_title)));
        etLoginPassword.setOnTouchListener(new PasswordVisibilityListener());
        return rootView;
    }
    @OnClick(R.id.btn_login)
    public void onTapLogin(Button btnLogin) {
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            //One of the required data is empty

            if (TextUtils.isEmpty(email)) {
                etLoginEmail.setError(getString(R.string.error_missing_email));
            }

            if (TextUtils.isEmpty(password)) {
                etLoginPassword.setError(getString(R.string.error_missing_password));
            }
        } else if (!isEmailValid(email)) {
            //Email address is not in the right format.
            etLoginEmail.setError(getString(R.string.error_email_is_not_valid));
        } else {
            //Checking on client side is done here.
            mControllerAccountControl.onLogin(email, password);
        }
    }
    public boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(MyanmarAttractionsConstants.EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

}