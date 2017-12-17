package com.chutipon.reviewx.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ProgressBar;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.manager.CheckExistUserManager;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private final String TAG = "MainActivity";
    private static MainActivity instance;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isLoggedIn()) {
            redirect();
        }

//          For getting app keyhash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.chutipon.reviewx",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        } catch (NoSuchAlgorithmException ignored) {
        }

        instance = this;
        redirect();
        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        callbackManager = CallbackManager.Factory.create();
        progressBar = findViewById(R.id.progress_bar);

        LoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "onSuccess: ");
                redirect();
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel: ");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d(TAG, "onError: ");
                exception.printStackTrace();
            }
        });
    }

    public static MainActivity getInstance() {
        return instance;
    }

    private void redirect() {
        if (isLoggedIn()) {
            Log.d(TAG, "redirect: Access token " + AccessToken.getCurrentAccessToken().getUserId());
            CheckExistUserManager.getInstance().startCheckExistUser(AccessToken.getCurrentAccessToken().getUserId());
        }
    }

    public void redirectToPage(Class cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


    public boolean isLoggedIn() {
        return AccessToken.getCurrentAccessToken() != null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
