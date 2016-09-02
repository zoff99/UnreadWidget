# UnreadWidget
Widget for any Android App to show unread count

# Adding to your Project:
Just drop all the files into your Project.

# CustomWidgetProvider.java

configure these 3 values to your needs:
<pre>
public static final int MAX_COUNT = 20;
public static Class ToOpenActivity = org.example.myapp.WantToStartThisActivity.class;
public static String baseClass = "org.example.myapp";
</pre>

if more than MAX_COUNT unread count, then MAX_COUNT"+" will be displayed
ToOpenActivity specifies the Activity that you want to open, when the Widget is clicked
baseClass is the Base Class of your Project

# AndroidManifest.xml

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
                &lt;action android:name="org.smssecure.smssecure.CHANGE_BADGE" /&gt;
            &lt;/intent-filter&gt;

            &lt;meta-data
                android:name="android.appwidget.provider"
                android:resource="@xml/badge_widget_provider" /&gt;
        &lt;/receiver&gt;
        &lt;!-- unread badge widget --&gt;
        &lt;!-- unread badge widget --&gt;
</pre>

