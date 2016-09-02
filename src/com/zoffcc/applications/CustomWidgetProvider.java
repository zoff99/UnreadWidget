/**
 * UnreadWidget, Widget for any Android App to show unread count
 * Copyright (C) 2016 Zoff <zoff@zoff.cc>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301, USA.
 */

package com.zoffcc.applications;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import org.smssecure.smssecure.R;

/**
 * Created on 01.09.2016.
 */
public class CustomWidgetProvider extends AppWidgetProvider
{

    // ---- CONFIG ----
    // ---- CONFIG ----
    // ---- CONFIG ----
	//
	// Configure your values here:
	//
    public static final int MAX_COUNT = 20;
    public static Class ToOpenActivity = org.example.myapp.WantToStartThisActivity.class;
    public static String baseClass = "org.example.myapp";
	//
	// You also need these resources defines in your Project:
	//
	// @string/app_name
	// @drawable/icon
	//
    // ---- CONFIG ----
    // ---- CONFIG ----
    // ---- CONFIG ----

    @Override
    public void onUpdate(final Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        for (int widgetId : appWidgetIds)
        {
            Intent intent = new Intent(context, ToOpenActivity);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.badge_widget);

            int unreadCount = BadgeWidgetIntentReceiver.unreadCount;
            if (unreadCount <= 0)
            {
                // Hide TextView for unread count
                remoteViews.setViewVisibility(R.id.widget_number, View.GONE);
            }
            else
            {
                remoteViews.setViewVisibility(R.id.widget_number, View.VISIBLE);

                String displayCount = (unreadCount <= MAX_COUNT) ? String.valueOf(unreadCount) : String.valueOf(MAX_COUNT) + "+";
                remoteViews.setTextViewText(R.id.widget_number, displayCount);
            }

            remoteViews.setOnClickPendingIntent(R.id.widget_icon, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
}
