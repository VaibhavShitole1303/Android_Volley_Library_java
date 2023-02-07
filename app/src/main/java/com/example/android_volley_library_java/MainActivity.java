package com.example.android_volley_library_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
     // creating the variable that hold the URL of the API
    private  static  final String URL="https://api.github.com/users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // adding recycler view
        RecyclerView userList=findViewById(R.id.userList);
        userList.setLayoutManager(new LinearLayoutManager(this));


        // create the string request  which has 3 parameters
        // 1st is URL from which data has to fetch
        //2nd is Response.Listener<String> in which wee get the data
       // 3rd is error what we get
        StringRequest request= new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("tag",response);
                // to get this fetch data we use Gson
                // create Gson object with the help of GsonBuilder
                GsonBuilder gsonBuilder=new GsonBuilder();
                Gson gson=gsonBuilder.create();

                User[] users= gson.fromJson(response,User[].class);
                // we bind recycler view with the response

                userList.setAdapter(new Adapter(MainActivity.this,users));
                Log.d("tag",""+users);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             Log.d("tag ","something went wrong ");
                Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_LONG).show();
            }
        });
        // we need to create the queue in which we have to add request because
        // Volley works like when we have we need the data then volley check that is data is in cash
        // if not then it go to API to fetch data
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
       //create the queue and add the request to it




    }
}