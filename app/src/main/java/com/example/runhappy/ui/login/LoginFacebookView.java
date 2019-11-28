package com.example.runhappy.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.runhappy.LoginFacebookObserver;
import com.example.runhappy.R;
import com.example.runhappy.UsuarioObserver;
import com.example.runhappy.activity.LoginPrincipalActivity;
import com.example.runhappy.model.Usuario;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class LoginFacebookView implements LoginFacebookObserver {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private ProgressDialog mDialog;

    private Context context;
    private LoginViewModel vmLogin;

    private String img;
    private String foto;
    private String name = "";
    private String email1 = "";
    private String acessToken;

    public LoginFacebookView(Context context){
        this.context = context;
    }

    public void inicialize(View view){

        vmLogin = new LoginViewModel(context);
        vmLogin.addFacebookObserver( this );

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setPermissions(Arrays.asList("public_profile", "email"));

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                mDialog = new ProgressDialog(context);
                mDialog.setMessage("Retrieving data...");
                mDialog.show();
                acessToken = loginResult.getAccessToken().getToken();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        mDialog.dismiss();
                        Log.d("response", response.toString());
                        getFacebookData(object);
                        try {
                            String id = object.getString("id");
                            foto = "https://graph.facebook.com/"+id+"/picture?height=120&width=120";
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            name = object.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            email1 = object.getString("email");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText( context, "Name: " + name + " Email: " + email1, Toast.LENGTH_LONG).show();

                        vmLogin.logar(email1, acessToken);

                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, picture.width(120).height(120)");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

    }

    private void getFacebookData(JSONObject object) {
        try {
            URL profilePicture = new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=250&height=250");
            img = profilePicture.toString();
            System.out.println(object.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onNotLoged() {
        vmLogin.registrar(name, email1, acessToken);
    }
}
