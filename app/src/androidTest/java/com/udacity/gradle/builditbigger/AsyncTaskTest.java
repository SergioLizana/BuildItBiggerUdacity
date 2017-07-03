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

        try {
            AsyncTaskJokes endpointsAsyncTask = new AsyncTaskJokes(getContext());
            endpointsAsyncTask.execute();
            String joke = endpointsAsyncTask.get(30, TimeUnit.SECONDS);

            assertThat(joke, notNullValue());
            assertTrue(joke.length() > 0);

        } catch (Exception e) {
            fail("Operation timed out");
        }
    }
}
