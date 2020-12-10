package com.example.gsontag.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShareUtil {

    public static void shareContentNoUs(Context context, File text) {
        Activity activity = (Activity) context;
        Intent it = new Intent(Intent.ACTION_SEND);
        it.setType("application/pdf");
        List<ResolveInfo> resInfo = activity.getPackageManager().queryIntentActivities(it, PackageManager.MATCH_DEFAULT_ONLY);

        if (!resInfo.isEmpty()) {
            List<Intent> targetedShareIntents = new ArrayList<Intent>();
            for (ResolveInfo info : resInfo) {
                Intent targeted = new Intent(Intent.ACTION_SEND);
                targeted.setType("application/pdf");
                ActivityInfo activityInfo = info.activityInfo;

                // judgments : activityInfo.packageName, activityInfo.name, etc.
               /* if (activityInfo.packageName.contains("bluetooth") || activityInfo.name.contains("bluetooth")) {
                    continue;
                } else if (activityInfo.packageName.contains("com.suning.netdisk")) {
                    continue;
                }*/
                targeted.putExtra(Intent.EXTRA_SUBJECT, "分享到:");
                targeted.putExtra(Intent.EXTRA_STREAM, text);
                targeted.setPackage(activityInfo.packageName);

                targetedShareIntents.add(targeted);
            }
            try {
                if (targetedShareIntents.size() == 0) {
                    Toast.makeText(activity, "不存在分享组件", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), "分享功能");
                if (chooserIntent == null) {
                    return;
                }

                // A Parcelable[] of Intent or LabeledIntent objects as set with
                // putExtra(String, Parcelable[]) of additional activities to place
                // a the front of the list of choices, when shown to the user with a
                // ACTION_CHOOSER.
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[] {}));

                activity.startActivity(chooserIntent);
            } catch (Exception ex) {
                Toast.makeText(activity, "不存在分享组件", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(activity, "不存在分享组件", Toast.LENGTH_SHORT).show();
        }
    }
}
