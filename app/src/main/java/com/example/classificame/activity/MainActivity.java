package com.example.classificame.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.classificame.R;
import com.example.classificame.config.ConfigFirebase;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;

    private Button buttonCadastrar, buttonLoginEmail;
    private LoginButton buttonFacebook;
    private SignInButton buttonGoogle;
    private CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        callbackManager = CallbackManager.Factory.create();
        auth = ConfigFirebase.getAuth();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        buttonCadastrar = findViewById(R.id.button_cadastrar);
        buttonLoginEmail = findViewById(R.id.button_login_email);
        buttonFacebook = findViewById(R.id.button_facebook);
        buttonGoogle = findViewById(R.id.button_google);

        buttonGoogle.setSize(SignInButton.SIZE_WIDE);
        buttonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });

        //loginButton.setLoginText("Entrar com o Facebook");
        buttonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAcessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("Facebook:", "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Facebook:", "facebook:onError", error);
            }
        });

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CadastroActivity.class));
            }
        });

        buttonLoginEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarLogin();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w("GoogleLogin:  ", "Cadastro com Google falhou.", e);
            }
        }
    }

    public void verificarLogin() {
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(this, ContainerActivity.class));
            finish();
        }
    }

    public void handleFacebookAcessToken(AccessToken token) {
        Log.d("Facebook: ", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d("Facebook: ", "signInWithCredential:success");
                            startActivity(new Intent(MainActivity.this, CadastroActivity.class));
                            finish();
                        } else {
                            Log.w("FacebookLogin: ", "Login com Facebook falhou.", task.getException());
                            Toast.makeText(MainActivity.this, "Falha na autenticação", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("GoogleSignIn: ", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("GoogleSignIn: ", "signInWithCredential:success");
                            startActivity(new Intent(MainActivity.this, CadastroActivity.class));
                            finish();
                        } else {
                            Log.w("GoogleSignIn: ", "Cadastro com Google falhou", task.getException());
                            Toast.makeText(MainActivity.this, "Falha na autenticação.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
