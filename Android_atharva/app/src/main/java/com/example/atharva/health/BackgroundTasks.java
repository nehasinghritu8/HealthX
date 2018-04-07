package com.example.atharva.health;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTasks extends AsyncTask<String,Void,String>{
    Context ctx;

    BackgroundTasks(Context ctx)
    {
        this.ctx=ctx;
    }

    @Override
    protected String doInBackground(String... strings) {

        String reg_url="http://192.168.43.152/webapp/register.php";
        String log_url="http://10.0.2.2/webapp/login.php";
        String method=strings[0];
        if(method.equals("register")) {
            String uname = strings[1];
            String pass = strings[2];
            try {

                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                if (httpURLConnection != null) {
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(uname, "UTF-8")
                            + "&" + URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
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

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
       // super.onPostExecute(result);
        Toast.makeText(ctx,result, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
