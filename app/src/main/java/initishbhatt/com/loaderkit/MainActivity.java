package initishbhatt.com.loaderkit;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import initishbhatt.com.loaders.type.CircularLoader;

public class MainActivity extends AppCompatActivity {
    private LinearLayout container;
    private CircularLoader circularLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        container = (LinearLayout) findViewById(R.id.container);
        circularLoader = new CircularLoader(this);
        circularLoader.setPadding(20, 20, 20, 20);
      //  circularLoader.setDefaultColor(ContextCompat.getColor(this, R.color.blue_default));
        //circularLoader.setSelectedColor(ContextCompat.getColor(this, R.color.blue_selected));
        circularLoader.setBigCircleRadius(116);
        circularLoader.setRadius(40);
        circularLoader.setAnimationDuration(100);
        container.addView(circularLoader);
    }
}
