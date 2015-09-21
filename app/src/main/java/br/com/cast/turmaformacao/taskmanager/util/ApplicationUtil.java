package br.com.cast.turmaformacao.taskmanager.util;

import android.content.Context;

public final class ApplicationUtil {

    private static Context context;

    private ApplicationUtil() {
        super();
    }

    public static void setContext(Context context) {
        ApplicationUtil.context = context;
    }

    public static Context getContext() {
        return ApplicationUtil.context;
    }

}
