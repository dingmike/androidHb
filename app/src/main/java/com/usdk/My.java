package com.usdk;

import android.app.Application;
import android.util.Log;

/**
 * Created by PAX on 2017/9/19.
 */
public class My {
    private static Application app;
    private static boolean debug = true;

    public static void register(Application app) {
        if (My.app == null) {
            My.app = app;
        }
    }

    public static Application app() {
        if (My.app == null) {
            Log.e("base.My", "You must add \"My.register(this)\" int your Application");
            throw new RuntimeException("You must add \"My.register(this)\" int your Application");
        } else {
            return My.app;
        }
    }
}