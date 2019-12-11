package com.example.runhappy.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.runhappy.R;
import com.example.runhappy.model.Usuario;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginFacebookView {

    private LoginButton loginButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    private ProgressDialog mDialog;

    private Context context;
    private LoginViewModel vmLogin;

    public LoginFacebookView(Context context){
        this.context = context;
    }

    public void inicialize(View view, CallbackManager callbackManager){

        vmLogin = LoginViewModel.getInstance(context);
        loginButton = view.findViewById(R.id.login_button);
        loginButton.setPermissions(Arrays.asList("public_profile", "email"));

        firebaseAuth = FirebaseAuth.getInstance();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                firebaseLogin(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                alert("Operação cancelada");
            }

            @Override
            public void onError(FacebookException exception) {
                alert("Erro no login do facebook");
            }
        });

    }

    private void firebaseLogin(AccessToken accessToken) {

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            listener = new FirebaseAuth.AuthStateListener() {
                                @Override
                                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    if (user != null){
                                        Usuario usuario = new Usuario(user.getUid(), user.getDisplayName(), user.getEmail());
                                        vmLogin.onUsuarioLoged(usuario);
                                    }
                                }
                            };
                        } else {
                            alert("Erro de autenticação com facebook");
                        }
                    }
                });
    }

    private void alert(String mensagem){
        Toast.makeText( context, mensagem, Toast.LENGTH_LONG).show();
    }
}
