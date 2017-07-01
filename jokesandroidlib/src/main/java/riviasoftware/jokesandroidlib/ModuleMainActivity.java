package riviasoftware.jokesandroidlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ModuleMainActivity extends AppCompatActivity {
    String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_module_activity);

        joke = getIntent().getStringExtra("joke");
        TextView textViewJoke = (TextView) findViewById(R.id.textview_joke);
        textViewJoke.setText(joke);
    }

}
