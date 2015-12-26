/**
 * Created by Saurabh on 12/25/2015
 */

package barqsoft.footballscores.ui;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import barqsoft.footballscores.R;
import barqsoft.footballscores.service.myFetchService;

public class ScoresWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        ComponentName thisWidget = new ComponentName(context, ScoresWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds) {

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            // Set the text
            remoteViews.setTextViewText(R.id.team_1, "Team 1");
            remoteViews.setTextViewText(R.id.time, "My time");
            remoteViews.setTextViewText(R.id.team_2, "Team 2");

            // Register an onClickListener
            Intent service_start = new Intent(context, myFetchService.class);

            service_start.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            service_start.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, service_start, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
}
