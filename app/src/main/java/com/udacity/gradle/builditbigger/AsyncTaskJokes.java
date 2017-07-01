package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.sergiolizanamontero.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;


import java.io.IOException;

import riviasoftware.jokesandroidlib.ModuleMainActivity;

/**
 * Created by sergiolizanamontero on 1/7/17.
 */

class AsyncTaskJokes  extends  AsyncTask<String, String, String> {

    private static MyApi myApiService = null;
    private Context context;
    private String mResult;

    public AsyncTaskJokes(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            //5 second delay to give the loading spinner progress bar a chance to show up
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http:/10.0.2.2:8080/_ah/api/") //address of the Genymotion emulator
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            String data = myApiService.getJoke().execute().getData();
            return data;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mResult = s;
        sendJokeToJokeActivity();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("TEST","onPreExecute");
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Log.d("TEST","onProgressUpdate");
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        Log.d("TEST","onCancelled");
    }

    private void sendJokeToJokeActivity() {
        Intent intent = new Intent(context, ModuleMainActivity.class);
        intent.putExtra("joke", mResult);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
