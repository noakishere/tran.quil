package com.example.tranquil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MainMenu extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageButton myProfileBtn;

    TextView randomQuote, quoteAuthor;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature((Window.FEATURE_NO_TITLE)); // hiding the title
        getSupportActionBar().hide(); // hiding the title bar
        setContentView(R.layout.activity_main_menu);
        bottomNavigationView = findViewById(R.id.bottomNavigationBarMainMenu);

        // declaring ImageButtons
        myProfileBtn = (ImageButton)findViewById(R.id.myProfileBtnMainMenu);

        randomQuote = findViewById(R.id.dailyQuoteTextViewMainMenu);
        quoteAuthor = findViewById(R.id.dailyQuoteAuthorTextViewMainMenu);
        mQueue = Volley.newRequestQueue(this);

        // registering click events for top navigation
        myProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyProfile.class);
                intent.putExtra("currentActivity", "MainMenu");
                startActivity(intent);
            }
        });

        // set highlighted menu
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        // menu navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.meditationPage:
                        Intent intentMeditation = new Intent(MainMenu.this, Meditation.class);
                        startActivity(intentMeditation);
                        return true;
                    case R.id.sleepPage:
                        Intent intentSleep = new Intent(MainMenu.this, Sleep.class);
                        startActivity(intentSleep);
                        return true;
                }
                return false;
            }
        });

        jsonParse();
    }

    private void jsonParse(){
        String url = "https://www.json-generator.com/api/json/get/bZLaMujvIi?indent=2";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Quotes");

                            Random rand = new Random();
                            int index = rand.nextInt((jsonArray.length() - 1) + 1) + 1;

                            JSONObject jsonQuote = jsonArray.getJSONObject(index);
                            String jsonStr = jsonQuote.getString("quote");
                            String jsonAuth = jsonQuote.getString("quoteAuthor");

                            randomQuote.setText(jsonStr);
                            quoteAuthor.setText(jsonAuth);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}