![RevealFAB RevealFAB](http://i.giphy.com/xT0BKAJaZwttdc49cQ.gif)

# RevealFAB

RevealFAB is a simple custom view that provides you intents between activities with ripple animation.

## Install
Add the repository to your top build.gradle:
```groovy
repositories {
    jcenter()
    maven {
        url  "http://dl.bintray.com/sergiocasero/maven"
    }
}
```

And then, add the dependency in your app build.gradle file:
```groovy
compile 'com.sergiocasero.revealfab:revealfab:1.0'
```
## Example

Add to your layout:
```xml
<RelativeLayout...>

    <android.support.design.widget.CoordinatorLayout...>
        <!-- YOUR CONTENT -->
    </android.support.design.widget.CoordinatorLayout>

    <com.sergiocasero.revealfab.RevealFAB
        android:id="@+id/reveal_fab"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:fab_color="@color/colorAccent"
        app:fab_icon="@drawable/ic_add_white_24dp"
        app:reveal_color="@color/colorAccent" />
</RelativeLayout>
```
**Important**: This component goes above your content. You can use Coordinator, LinearLayout... or another Relative layout if you want :)

As you can see, you have 3 custom attributes for customizing colors and icon

Setting information about intent:
```java
revealFAB = (RevealFAB) findViewById(R.id.reveal_fab);
Intent intent = new Intent(MainActivity.this, DetailActivity.class);
revealFAB.setIntent(intent);

revealFAB.setOnClickListener(new RevealFAB.OnClickListener() {
    @Override
    public void onClick(RevealFAB button, View v) {
        button.startActivityWithAnimation();
    }
});
```

## Don't forget call onResume() method!
```java
@Override
protected void onResume() {
    super.onResume();
    revealFAB.onResume();
}
```

That's all

### Version
1.0.0

### Development

Want to contribute? Great!

License
----

Apache


**Free Software, Hell Yeah!**
