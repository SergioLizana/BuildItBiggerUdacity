package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.example.sergiolizanamontero.myapplication.backend.myApi.MyApi;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.repackaged.com.google.common.annotations.VisibleForTesting;

import java.io.IOException;


/**
 * Created by sergiolizanamontero on 1/7/17.
 */

class AsyncTaskJokes  extends  AsyncTask<MainActivityFragment, String, String> {

    private InterstitialAd interstitial;
    private static MyApi myApiService = null;
    private Context context;
    private MainActivityFragment mainActivityFragment;
    private boolean testFlag = false;

    public AsyncTaskJokes(Context context) {
        this.context = context;
        showAd();
    }
    public AsyncTaskJokes(Context context, boolean testFlag){
        this.testFlag = testFlag;
        this.context = context;
    }

    @Override
    protected String doInBackground(MainActivityFragment... params) {
        mainActivityFragment = params[0];
        try {
            //5 second delay to give the loading spinner progress bar a chance to show up
            Thread.sleep(2500);
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

        mainActivityFragment.mResult = s;
        if(!testFlag) {
            displayInterstitial();
        }


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

    private void showAd(){
        AdRequest adRequest = new AdRequest.Builder().build();

        // Prepare the Interstitial Ad
        interstitial = new InterstitialAd(context);
        // Insert the Ad Unit ID
        interstitial.setAdUnitId(context.getString(R.string.admob_interstitial_id));

        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                mainActivityFragment.sendJokeToJokeActivity();
            }
        });

        interstitial.loadAd(adRequest);
    }

    public void displayInterstitial() {
        // If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }


    }


}
