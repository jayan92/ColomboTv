package com.xicigny.colombotv.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.FacebookSdk;
import com.facebook.GraphResponse;
import com.google.android.gms.plus.model.people.Person;
import com.xicigny.colombotv.MainActivity;
import com.xicigny.colombotv.R;
import com.xicigny.colombotv.app.AppConfig;
import com.xicigny.colombotv.app.AppController;
import com.xicigny.colombotv.helper.FacebookHelper;
import com.xicigny.colombotv.helper.GooglePlusHelper;
import com.xicigny.colombotv.helper.SQLiteHandler;
import com.xicigny.colombotv.helper.SessionManager;
import com.xicigny.colombotv.utils.KeyHashGenerator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class LoginActivity extends Activity implements FacebookHelper.OnFbSignInListener, GooglePlusHelper.OnGoogleSignInListener {
    private static final String TAG = RegisterActivity.class.getSimpleName();

    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog progressDialog;
    private SessionManager session;
    private SQLiteHandler db;

    private FacebookHelper fbConnectHelper;
    private Button fbSignInButton;

    private GooglePlusHelper gSignInHelper;
    private Button gSignInButton;

    private ProgressBar progressBar;
    private boolean isFbLogin = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                // Check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()) {
                    // login user
                    checkLogin(email, password);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });

        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        KeyHashGenerator.generateKey(this);
        fbConnectHelper = new FacebookHelper(this, this);

        fbSignInButton = (Button) findViewById(R.id.fb_sign_in_button);
        fbSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                fbConnectHelper.connect();
                isFbLogin = true;
            }
        });

        gSignInHelper = new GooglePlusHelper(this, this);

        gSignInButton = (Button) findViewById(R.id.main_g_sign_in_button);
        gSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                gSignInHelper.connect();
                isFbLogin = false;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        gSignInHelper.onActivityResult(requestCode, resultCode, data);
        fbConnectHelper.onActivityResult(requestCode, resultCode, data);
        if (isFbLogin) {
            progressBar.setVisibility(View.VISIBLE);
            isFbLogin = false;
        }
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }


    @Override
    public void OnFbSignInComplete(GraphResponse graphResponse, String error) {
        progressBar.setVisibility(View.GONE);
        if (error == null) {
            try {
                JSONObject jsonObject = graphResponse.getJSONObject();
                String id = jsonObject.getString("id");
                String profileImg = "http://graph.facebook.com/" + id + "/picture?type=large";
                Log.d("url", profileImg);
                lodMain(jsonObject.getString("name"), jsonObject.getString("email"), profileImg, "facebook");


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void OnGSignInComplete(Person mPerson, String emailAddress, String error) {
        progressBar.setVisibility(View.GONE);
        String name = "";
        String url = "";
        if (mPerson != null) {
            name = mPerson.getDisplayName();
            url = mPerson.getUrl();

            lodMain(name, emailAddress, url, "google");
        }
    }

    public void lodMain(String name, String email, String profileImg, String login) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("profileImg", profileImg);
        intent.putExtra("login", login);
        startActivity(intent);
    }

    protected void checkLogin(final String email, final String pwd) {
        progressDialog.setMessage("Logging in ...");
        showDialog();
        Thread t = new Thread() {

            public void run() {
                Looper.prepare(); //For Preparing Message Pool for the child Thread
                HttpClient client = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
                HttpResponse response;
                JSONObject json = new JSONObject();

                try {
                    HttpPost post = new HttpPost(AppConfig.URL_LOGIN);
                    json.put("name", email);
                    json.put("encryptedPassword", pwd);
                    StringEntity se = new StringEntity(json.toString());
                    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    post.setEntity(se);
                    response = client.execute(post);

                    /*Checking response */
                    if (response != null) {
                        InputStream in = response.getEntity().getContent(); //Get the data in the entity
                        JSONObject jObj = new JSONObject(AppConfig.getStringFromInputStream(in));
                        Log.d("response", jObj.toString());
                        String code = jObj.getString("code");
                        hideDialog();
                        // Check for error node in json
                        if (!code.equals("0")) {
                            // user successfully logged in
                            // Create login session
                            session.setLogin(true);

                            // Inserting row in users table
                            db.addUser("name", email, "uid", "created_at");

                            // Launch main activity
                            Intent intent = new Intent(LoginActivity.this,
                                    MainActivity.class);
                            intent.putExtra("name", email);
                            intent.putExtra("login", "system");
                            startActivity(intent);
                            finish();
                        } else {
                            // Error in login. Get the error message
                            String errorMsg = jObj.getString("error_msg");
                            Toast.makeText(getApplicationContext(),
                                    errorMsg, Toast.LENGTH_LONG).show();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Error, Cannot Estabilish Connection", Toast.LENGTH_LONG).show();
                }

                Looper.loop(); //Loop in the message queue
            }
        };

        t.start();
    }
}
