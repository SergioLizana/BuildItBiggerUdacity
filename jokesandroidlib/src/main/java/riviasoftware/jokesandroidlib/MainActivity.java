package riviasoftware.jokesandroidlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    JokeDisplay jokeDisplay;
    String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        joke = getIntent().getStringExtra("joke");
        jokeDisplay = JokeDisplay.newInstance(joke);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentJoke, jokeDisplay)
                .commit();


    }
}
