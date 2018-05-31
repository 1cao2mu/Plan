package com.project.cyy.plan.tool;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by cyy
 * on 18-5-30下午3:54
 */
public class Utils {
     static void sysout(Object object) {
        System.out.println(object);
    }

    private static int screenWidth = 0;

    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }
}






