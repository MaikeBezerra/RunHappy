package com.example.runhappy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.runhappy.R;
import com.example.runhappy.data.SQLite.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.data.SQLite.UsuarioDAOSQLite;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.login.LoginViewModel;
import com.example.runhappy.ui.usuario.UsuarioViewModel;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class LoginPrincipalActivity extends AppCompatActivity {

    private UsuarioDAO usuarioDAO;
    private EditText email;
    private EditText senha;
    CallbackManager callbackManager;
    LoginButton loginButton;
    private Bitmap imagemUsuario;
    private String img;
    ProgressDialog mDialog;
    String name = "";
    String email1 = "";
    Intent telaInicial;
    String foto;

    private UsuarioViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SQLiteHandle handle = new SQLiteHandle(getApplicationContext());
        usuarioDAO = new UsuarioDAOSQLite(handle);

        email = findViewById(R.id.username);
        senha = findViewById(R.id.password);

        viewModel = new UsuarioViewModel( getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setPermissions(Arrays.asList("public_profile", "email"));

        telaInicial = new Intent(getApplicationContext(), TelaInicialActivity.class);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                mDialog = new ProgressDialog(LoginPrincipalActivity.this);
                mDialog.setMessage("Retrieving data...");
                mDialog.show();
                final String acessToken = loginResult.getAccessToken().getToken();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        mDialog.dismiss();
                        Log.d("response", response.toString());
                        getFacebookData(object);
                        try {
                            telaInicial.putExtra("nome", object.getString("name"));
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
                        Toast.makeText(getApplicationContext(), "Name: " + name + " Email: " + email1, Toast.LENGTH_LONG).show();
                        telaInicial.putExtra("nome", name);
                        telaInicial.putExtra("email", email1);
                        telaInicial.putExtra("imagem",foto);

                        if (usuarioDAO.findByEmail(email1).equals(null)) {

                            Usuario c = new Usuario(name, email1, acessToken);
                            viewModel.adicionarUsuario(c);
                        }

                        startActivity(telaInicial);

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
        telaInicial.putExtra("nome", name);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void login(View view) {
        LoginViewModel vmLogin = new LoginViewModel(getApplicationContext());
        vmLogin.logar(email.getText().toString(), senha.getText().toString());

        if (vmLogin.getUsuario() != null) {
            Intent intent = new Intent(getApplicationContext(), TelaInicialActivity.class);
            startActivity(intent);
            finish();
        }
    }


}