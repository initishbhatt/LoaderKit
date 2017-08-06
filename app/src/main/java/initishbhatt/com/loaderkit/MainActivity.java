package initishbhatt.com.loaderkit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.initishbhatt.loaders.type.CircularLoader;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout container;
    private CircularLoader circularLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        container = (RelativeLayout) findViewById(R.id.container);
        /*circularLoader = new CircularLoader(MainActivity.this);
        circularLoader.setPadding(40, 40, 40, 40);
        circularLoader.setDefaultColor(R.color.blue_default);
        circularLoader.setSelectedColor(R.color.blue_selected);
        circularLoader.setBigCircleRadius(116);
        circularLoader.setRadius(40);
        circularLoader.setAnimationDuration(500);*/
       // container.addView(circularLoader);
    }
}
