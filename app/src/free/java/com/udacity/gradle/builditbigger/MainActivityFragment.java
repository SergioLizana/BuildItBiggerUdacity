package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import riviasoftware.jokesandroidlib.ModuleMainActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private InterstitialAd interstitial;
    private ProgressBar progressBar = null;
    public String mResult = null;

    public MainActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdRequest adRequest = new AdRequest.Builder().build();

        // Prepare the Interstitial Ad
        interstitial = new InterstitialAd(getActivity());
        // Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));

        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                fetchJoke();
            }
        });

        interstitial.loadAd(adRequest);


        Button button = (Button) root.findViewById(R.id.tell_joke);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               displayInterstitial();
            }
        });

        return root;
    }

    public void fetchJoke(){
        getActivity().findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.tell_joke).setVisibility(View.INVISIBLE);
        getActivity().findViewById(R.id.instructions_text_view).setVisibility(View.INVISIBLE);

        AsyncTaskJokes endpointsAsyncTask = new AsyncTaskJokes(getActivity());
        endpointsAsyncTask.execute(this);
    }


    public void sendJokeToJokeActivity() {
        Intent intent = new Intent(getActivity(), ModuleMainActivity.class);
        intent.putExtra("joke", mResult);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
        getActivity().findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    public void displayInterstitial() {
    // If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }


    }

}
