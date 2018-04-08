package com.example.atharva.health;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.CharBuffer;

public class BackgroundTasks extends AsyncTask<String,Void,String>{
    private WeakReference<Context> contextReference;
    Context ctx;
    AlertDialog alertDialog;
    String type;
  //  String task;
 //   String sessionId;
    BackgroundTasks(Context ctx)
    {
        this.ctx=ctx;
        contextReference = new WeakReference<>(ctx);
    }

    @Override
    protected String doInBackground(String... strings) {

        String reg_url = "http://hackathon-theabhinavjain.c9users.io/register.php";
        String log_url = "http://hackathon-theabhinavjain.c9users.io/login.php";
        //String json_url="http://192.168.43.152/webapp/retrive.php";
        String json_get_url = "http://hackathon-theabhinavjain.c9users.io/viewPrescriptions.php";
        String json_set_url = "http://hackathon-theabhinavjain.c9users.io/updatePrescriptions.php";
        String method = strings[0];
        String subMethod=strings[1];
        String mainphp = "";
    //    task = method;
        if (method.equals("register")) {
            String uname = strings[1];
            String pass = strings[2];
            String name = strings[3];
            String pin = strings[4];
            String desc = strings[5];
            type = strings[6];
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
                    return "Registration success...";
                } else {
                    return "Connection failure ...";
                }
            } catch (MalformedURLException e) {
                //   Toast.makeText(ctx,e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (IOException e) {
                //  Toast.makeText(ctx,e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();

            }


        } else if (method.equals("login")) {
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
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                    //StringBuilder response = new StringBuilder();
                    String response = "";
                    String line = "";
                    int i = 0;
                    while ((line = bufferedReader.readLine()) != null) {
                        //  Log.d(ctx.getClass().getSimpleName(),"Entered buffered");
                        response += line;
                        //  response.append(line);
                        //  Log.d(ctx.getClass().getSimpleName(),i+" "+response.toString());
                        //  i++;
                    }
                    bufferedReader.close();
                    IS.close();
                    httpURLConnection.disconnect();
                    //   Log.d(ctx.getClass().getSimpleName(),response.toString());
                    //    sessionId=response.substring(response.indexOf("\n")).toString();
                    //return response.substring(0).toString().trim();
                    return response;
                } else {
                    return "Connection failure ...";
                }
            } catch (MalformedURLException e) {
                //   Toast.makeText(ctx,e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (IOException e) {
                //  Toast.makeText(ctx,e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();

            }
        } else if (method.equals("getPre")) {
            try {
                URL url = new URL(json_get_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS));
                String json_string;
                StringBuilder stringBuilder = new StringBuilder();
                while ((json_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(json_string + "\n");
                    Log.d(ctx.getClass().getSimpleName(), json_string);
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (method.equals("setPre")) {
            String patientId = strings[1];
            String prescription = strings[2];
            Log.d(ctx.getClass().getSimpleName(), patientId + " : " + prescription);
            try {

                URL url = new URL(json_set_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                if (httpURLConnection != null) {
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("pat_id", "UTF-8") + "=" + URLEncoder.encode(patientId, "UTF-8")
                            + "&" + URLEncoder.encode("presc", "UTF-8") + "=" + URLEncoder.encode(prescription, "UTF-8");
                    //+ "&" + URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(sessionId, "UTF-8");
                    Log.d(ctx.getClass().getSimpleName(), data);
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                    String response = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                        Log.d(ctx.getClass().getSimpleName(), line);
                    }
                    bufferedReader.close();
                    IS.close();
                    httpURLConnection.disconnect();
                    return response;
                } else {
                    return "Connection failure ...";
                }
            } catch (MalformedURLException e) {
                //   Toast.makeText(ctx,e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (IOException e) {
                //  Toast.makeText(ctx,e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();

            }
        } else if (method.equals("push")) {
            if(subMethod.equals("register"))
            {
                String uname = strings[1];
                String pass = strings[2];
                String name = strings[3];
                String pin = strings[4];
                String desc = strings[5];
                String type = strings[6];
                try {
                    String data = URLEncoder.encode("user_username", "UTF-8") + "=" + URLEncoder.encode(uname, "UTF-8")
                            + "&" + URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8")
                            + "&" + URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")
                            + "&" + URLEncoder.encode("user_pin", "UTF-8") + "=" + URLEncoder.encode(pin, "UTF-8")
                            + "&" + URLEncoder.encode("user_description", "UTF-8") + "=" + URLEncoder.encode(desc, "UTF-8")
                            + "&" + URLEncoder.encode("user_type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if(subMethod.equals("Login"))
            {}
            if(subMethod.equals("getPre"))
            {}
            if(subMethod.equals(("setPre")))
            {}
            return null;
        }
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
        if(result==null){
            return;
        }
        if(result.equals("Registration success...")) {
            Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
        }
        else
        {
         //   alertDialog.setMessage(result);
            result=result.toString().trim();
            if(result.equals("Doctor"))
            {
                Log.d(ctx.getClass().getSimpleName(),result);
                Intent intent = new Intent(ctx, DoctorActivity.class);
                ctx.startActivity(intent);
            }
            else if(result.equals("User"))
            {
                Log.d(ctx.getClass().getSimpleName(),result);
                Intent intent = new Intent(ctx, UserActivity.class);
                ctx.startActivity(intent);
            }
            else if(/*task.equals("getPre")*/)
            {
                AppCompatActivity context = (AppCompatActivity) contextReference.get();
                if(context != null) {
                    TextView textView = context.findViewById(R.id.textView_pre);
                    textView.setText(result);
                }
            }
            else if(/*task.equals("setPre")*/)
            {
              //  Log.d(ctx.getClass().getSimpleName(),result.toString());
                AppCompatActivity context = (AppCompatActivity) contextReference.get();
                if(context != null) {
                    TextView textView = context.findViewById(R.id.textView_pre);
                    textView.setText(result);
                }
            }
           // alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
