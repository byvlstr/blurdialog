# Blur Dialog

<img src="https://github.com/byvlstr/blurdialog/blob/master/assets/BlurDialog.png" width=150 align=right>

Blur Dialog is an Android dialog-like view providing a blur effect as background, a title and an icon

* **Innovative:** Blur Dialog is an innovative way to show in-app alerts, notifications or reward messages.
* **Great UI:** A simple UI. One title. One beautiful icon. On an ambitious and elegant blurred background. 
* **Inspired by:** 3 inspirations: iOS's volume dialog, XCode's "Build successful" dialog and Microsoft's new Fluent Design System.
* **Many thanks to:** Dmitry Saviuk who made [BlurView](https://github.com/Dimezis/BlurView) so that I didn't have to do it again. ^^

# Usage

Add this XML snippet to your layout.

```xml


<com.vlstr.blurdialog.BlurDialog
        android:layout_width="200dp"

android:layout_height="200dp"
        android:id="@+id/blurView"
        android:elevation="24dp"
        app:blurOverlayColor="@color/colorOverlay"
        app:blurDialogIcon="@mipmap/ic_launcher"
        app:blurDialogTitle="Hello World!"
        app:blurDialogDuration="length_short"

android:layout_gravity="center"
        android:visibility="invisible">

    </com.vlstr.blurdialog.BlurDialog>
```

And create and show the BlurDialog this way:

```java
MainActivity.java
---

final BlurDialog blurDialog = (BlurDialog) findViewById(R.id.blurView);
blurDialog.create(getWindow().getDecorView(), 20);
blurDialog.show();
```
Note: Currently, it is best to have one and only one BlurDialog in your Activity. Using 'invisible' in the XML and then hide() the BlurDialog (automatically done if duration is set, otherwise use the public method). 

# Compatibility
This Android library is currently only supported by devices with API > 17.

Also, [BlurView can be used only in a hardware-accelerated window. Otherwise, blur will not be drawn. It will fallback to a regular FrameLayout drawing process.](https://github.com/Dimezis/BlurView). Performance is good, but highly depends on your layout and usage.

# Customisation

* Title
* Icon
* Title size
* Corner Radius
* Duration: You have the choice between SHORT (2s) and LONG (3.5s). If you wish to have an infinite BlurDialog and handle hiding or dismissing yourself, you can do so too.

* Blur color overlay: Provide the colour being used as the dialog's overlay. Default is a transparent white.
* Blur radius: Range [1-25] - The lower it is, the more you will see the BlurDialog's background.

Note: BlurDialog is NOT extending [AlertDialog](https://developer.android.com/reference/android/app/AlertDialog.html), hence clicking outside the box is currently NOT hiding the BlurDialog. Of course, you can implement this in dispatchTouchEvent() [easily](http://stackoverflow.com/questions/36889141/hide-keyboard-in-fragment-on-outside-click) and then call BlurDialog's hide() or dismiss() method. 



# Gradle

```groovy
dependencies {
    compile 'com.vlstr.blurdialog:blurdialog:1.0.0'
}
```


# Examples

![screen](https://github.com/byvlstr/blurdialog/blob/master/assets/travel.png)
![screen](https://github.com/byvlstr/blurdialog/blob/master/assets/game.png)

# Logs

### 1.0.0

- Initial version



# Credits

Creator: Valentin Lungenstrass [http://www.vlstr.com/](http://vlstr.com/)

<a href="https://twitter.com/byvlstr">
  <img alt="Follow me on Twitter"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/twitter.png" />
</a>
<a href="https://www.linkedin.com/in/valentin-lungenstrass-3a496b97/">
  <img alt="Follow me on LinkedIn"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/linkedin.png" />
</a>


License
--------

    Copyright 2017 Valentin Lungenstrass.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
