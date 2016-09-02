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
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import org.smssecure.smssecure.R;

/**
 * Created on 01.09.2016.
 */
public class BadgeWidgetIntentReceiver extends BroadcastReceiver
{
    public static int unreadCount = 0;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(CustomWidgetProvider.baseClass + ".CHANGE_BADGE"))
        {
            int unreadCountNew = intent.getIntExtra("UNREAD_COUNT_NEW", unreadCount);
            updateWidgetPictureAndButtonListener(context, unreadCountNew);
        }
    }

    private void updateWidgetPictureAndButtonListener(Context context, int unreadCountNew)
    {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.badge_widget);

        unreadCount = unreadCountNew;
        if (unreadCount <= 0)
        {
            // Hide TextView for unread count if there are no unread messages.
            remoteViews.setViewVisibility(R.id.widget_number, View.GONE);
        }
        else
        {
            remoteViews.setViewVisibility(R.id.widget_number, View.VISIBLE);

            String displayCount = (unreadCount <= CustomWidgetProvider.MAX_COUNT) ? String.valueOf(unreadCount) : String.valueOf(CustomWidgetProvider.MAX_COUNT) + "+";
            remoteViews.setTextViewText(R.id.widget_number, displayCount);
        }

        Intent intent = new Intent(context, CustomWidgetProvider.ToOpenActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.widget_frame, p);

        ComponentName myWidget = new ComponentName(context, CustomWidgetProvider.class);

        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, remoteViews);
    }

}