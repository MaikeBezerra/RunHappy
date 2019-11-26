package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.usuario.UsuarioFormViewModel;
import com.example.runhappy.ui.usuario.UsuarioViewModel;
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

public class CadastroActivity extends AppCompatActivity {

    private UsuarioViewModel viewModel;
    private UsuarioFormViewModel formViewModel;
    CallbackManager callbackManager;
    private LoginButton loginButton;
    ProgressDialog mDialog;
    private String foto;
    private String name;
    private String email1;
    private Intent telaInicial;
    private UsuarioDAO usuarioDAO;
    private SQLiteHandle handle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setTitle("Cadastro");

        viewModel = new UsuarioViewModel(this, getApplicationContext());
        formViewModel = new UsuarioFormViewModel(this);

        handle = new SQLiteHandle(this);
        usuarioDAO = new UsuarioDAOSQLite(handle);
        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button2);
        loginButton.setPermissions(Arrays.asList("public_profile", "email"));
        telaInicial = new Intent(getApplicationContext(), TelaInicialActivity.class);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                mDialog = new ProgressDialog(CadastroActivity.this);
                mDialog.setMessage("Fazendo login...");
                mDialog.show();
                final String acessToken = loginResult.getAccessToken().getToken();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        mDialog.dismiss();
                        Log.d("response", response.toString());

                        try {

                            telaInicial.putExtra("nome", object.getString("name"));
                            String id = object.getString("id");
                            foto = "https://graph.facebook.com/" + id + "/picture?height=120&width=120";
                            name = object.getString("name");
                            email1 = object.getString("email");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(getApplicationContext(), "Name: " + name + " Email: " + email1, Toast.LENGTH_LONG).show();
                        telaInicial.putExtra("nome", name);
                        telaInicial.putExtra("email", email1);
                        telaInicial.putExtra("imagem", foto);

                        if (usuarioDAO.findByEmail(email1) == null) {

                            Usuario c = new Usuario(name, email1, acessToken);
                            viewModel.adicionarUsuario(c);
                        }
                        startActivity(telaInicial);
                    } });

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
            } );
        telaInicial.putExtra("nome", name);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void cadastrar(View view) {
        Usuario usuario = formViewModel.getUsuario();
        viewModel.adicionarUsuario(usuario);
    }


}
