# UnreadWidget
Widget for any Android App to show unread count

## Adding to your Project:
Just drop all the files into your Project.

## Example Widget

![This is what it looks like](https://github.com/zoff99/UnreadWidget/raw/master/doc/UnreadWidget_Sample.png "This is what it looks like")

Normal App Icon on the left // UnreadWidget on the right

## CustomWidgetProvider.java

configure these 3 values to your needs:
```
public static final int MAX_COUNT = 20;
public static Class ToOpenActivity = org.example.myapp.WantToStartThisActivity.class;
public static String baseClass = "org.example.myapp";
```

if more than MAX_COUNT unread count, then MAX_COUNT"+" will be displayed

ToOpenActivity specifies the Activity that you want to open, when the Widget is clicked

baseClass is the Base Class of your Project

## Resources

you will need to already have these resources in your Project:

*@drawable/icon*

this should be your App's Homescreen Icon

*@string/app_name*

this should be the String that shows under your App's Homescreen Icon


## AndroidManifest.xml

add these lines to your AndroidManifest.xml (inside the application tag):
<pre>
        &lt;!-- unread badge widget --&gt;
        &lt;!-- unread badge widget --&gt;
        &lt;receiver android:name="com.zoffcc.applications.CustomWidgetProvider"
                  android:icon="@drawable/icon"
                  android:label="@string/app_name"&gt;
            &lt;intent-filter&gt;
                &lt;action android:name="android.appwidget.action.APPWIDGET_UPDATE"/&gt;
            &lt;/intent-filter&gt;
            &lt;meta-data
                android:name="android.appwidget.provider"
                android:resource="@xml/badge_widget_provider"/&gt;
        &lt;/receiver&gt;
        &lt;receiver
            android:name="com.zoffcc.applications.BadgeWidgetIntentReceiver"
            android:label="widgetBroadcastReceiver"&gt;
            &lt;intent-filter&gt;
                &lt;action android:name="org.example.myapp.CHANGE_BADGE" /&gt;
            &lt;/intent-filter&gt;

            &lt;meta-data
                android:name="android.appwidget.provider"
                android:resource="@xml/badge_widget_provider" /&gt;
        &lt;/receiver&gt;
        &lt;!-- unread badge widget --&gt;
        &lt;!-- unread badge widget --&gt;
</pre>

<B>and replace
*org.example.myapp*
with the Base Class of your Project</B>



## Update the Widget in your App

in your Project add:

```
import com.zoffcc.applications.CustomWidgetProvider;
.
.
.
.
try
{
        // ---- update the widget if present ----
        final Intent intent2 = new Intent();
        intent2.setAction(CustomWidgetProvider.baseClass + ".CHANGE_BADGE");
        intent2.putExtra("UNREAD_COUNT_NEW", enter_your_unread_count_here_as_integer);
        context.getApplicationContext().sendBroadcast(intent2);
        // ---- update the widget if present ----
}
catch (Exception e)
{
}
catch (Throwable t)
{
}
.
.
.
```


**enter_your_unread_count_here_as_integer** is the number you want displayed over your Widget.

if you want to hide the Count, just give 0 as **enter_your_unread_count_here_as_integer**


