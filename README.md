# LoaderKit
Android Loading animations

# Circular Loader

#. Through XML
```
<initishbhatt.com.loaders.type.CircularLoader
        android:id="@+id/loader"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        app:anim_dur="500"
        app:big_circle_radius="42dp"
        app:circle_radius="14dp"
        app:default_color="@color/purple_default"
        app:first_shadow_color="@color/pink_selected"
        app:second_shadow_color="@color/pink_default"
        app:selected_color="@color/purple_selected"
        app:show_running_shadow="true" />
 
 ```
#. Through Code

```
 CircularLoader circularLoader = new CircularLoader(MainActivity.this);
        circularLoader.setPadding(20, 20, 20, 20);
        circularLoader.setDefaultColor(ContextCompat.getColor(this, R.color.blue_default));
        circularLoader.setSelectedColor(ContextCompat.getColor(this, R.color.blue_selected));
        circularLoader.setBigCircleRadius(116);
        circularLoader.setRadius(40);
        circularLoader.setAnimationDuration(500);
