package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by sergiolizanamontero on 2/7/17.
 */

public class AsyncTaskTest  extends AndroidTestCase {

    public void testJokeDownload() {

        AsyncTaskJokes endpointsAsyncTask = new AsyncTaskJokes(getContext(),true);
        MainActivityFragment fragment = new MainActivityFragment();
        endpointsAsyncTask.execute(fragment);
        String joke = "";
        try {
            joke = endpointsAsyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();

        }
        assertThat(fragment.mResult, notNullValue());
        assertTrue(fragment.mResult.length() > 0);
    }
}
