package com.example.atharva.health;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTasks extends AsyncTask<String,Void,String>{
    Context ctx;
    AlertDialog alertDialog;
    String type;

    BackgroundTasks(Context ctx)
    {
        this.ctx=ctx;
    }

    @Override
    protected String doInBackground(String... strings) {

        String reg_url="http://hackathon-theabhinavjain.c9users.io/register.php";
        String log_url="http://hackathon-theabhinavjain.c9users.io/login.php";
        String method=strings[0];
        if(method.equals("register")) {
            String uname = strings[1];
            String pass = strings[2];
            String name=strings[3];
            String pin=strings[4];
            String desc=strings[5];
            type=strings[6];
            try {

                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                if (httpURLConnection != null) {
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("user_username", "UTF-8") + "=" + URLEncoder.encode(uname, "UTF-8")
                            + "&" + URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8")
                            + "&" + URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")
                            + "&" + URLEncoder.encode("user_pin", "UTF-8") + "=" + URLEncoder.encode(pin, "UTF-8")
                            + "&" + URLEncoder.encode("user_description", "UTF-8") + "=" + URLEncoder.encode(desc, "UTF-8")
                            + "&" + URLEncoder.encode("user_type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    IS.close();
                    return "Registration success...";}
                    else
                {
                    return  "Connection failure ...";
                }
                }catch(MalformedURLException e){
             //   Toast.makeText(ctx,e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }catch(IOException e){
              //  Toast.makeText(ctx,e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();

                }


            }
            else if(method.equals("login")){
            String uname = strings[1];
            String pass = strings[2];
            try {

                URL url = new URL(log_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                if (httpURLConnection != null) {
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("user_username", "UTF-8") + "=" + URLEncoder.encode(uname, "UTF-8")
                            + "&" + URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
                    String response="";
                    String line="";
                    while ((line=bufferedReader.readLine())!=null)
                    {
                        response+=line;
                    }
                    bufferedReader.close();
                    IS.close();
                    httpURLConnection.disconnect();
                    return response;
                }
                else
                {
                    return  "Connection failure ...";
                }
            }catch(MalformedURLException e){
                //   Toast.makeText(ctx,e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }catch(IOException e){
                //  Toast.makeText(ctx,e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();

            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog=new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Info ...");
    }

    @Override
    protected void onPostExecute(String result) {
       // super.onPostExecute(result);
        if(result.equals("Registration success...")) {
            Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
        }
        else
        {
         //   alertDialog.setMessage(result);
            Log.d(ctx.getClass().getSimpleName(),result);
            result=result.toString().trim();
            if(result.equals("Doctor"))
            {
                    Intent intent = new Intent(ctx, UserActivity.class);
                    ctx.startActivity(intent);
            }
            else if(result.equals("User"))
            {
                //D
            }
           // alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
