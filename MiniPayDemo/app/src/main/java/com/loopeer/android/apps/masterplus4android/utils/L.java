package com.loopeer.android.apps.masterplus4android.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.loopeer.android.apps.masterplus4android.BuildConfig;


@SuppressWarnings({"LogUsage", "DeprecatedApiWarning", "DeprecatedApiError"})
public class L {

    /**
     * release关闭log输出，其他编译环境开启
     */
    public static final boolean ENABLE_LOG = BuildConfig.DEBUG;

    /**
     * 开发环境下输出Log。使用示例：
     * <pre>
     * L.data("TAG", "count = %d, text = %s", count, text);
     * </pre>
     *
     * @param tag  TAG
     * @param msg  输出的消息，支持String.format字符串，例如"count = %d, text = %s"
     * @param args String.format格式化参数
     */
    public static void v(@NonNull String tag, String msg, Object... args) {
        if (ENABLE_LOG) {
            Log.v(tag, safeFormat(msg, args));
        }
    }

    /**
     * 开发环境下输出Log。使用示例：
     * <pre>
     * L.data("TAG", "count = %d, text = %s", count, text);
     * </pre>
     *
     * @param tag  使用SimpleClassName作为TAG
     * @param msg  输出的消息，支持String.format字符串，例如"count = %d, text = %s"
     * @param args String.format格式化参数
     */
    public static void d(@NonNull Object tag, String msg, Object... args) {


        if (ENABLE_LOG) {
            Log.d(tag.getClass().getSimpleName(), safeFormat(msg, args));
        }
    }

    /**
     * 开发环境下输出Log。使用示例：
     * <pre>
     * L.data("TAG", "count = %d, text = %s", count, text);
     * </pre>
     *
     * @param tag  使用SimpleClassName作为TAG
     * @param msg  输出的消息，支持String.format字符串，例如"count = %d, text = %s"
     * @param args String.format格式化参数
     */
    public static void d(@NonNull Class tag, String msg, Object... args) {
        if (ENABLE_LOG) {
            Log.d(tag.getSimpleName(), safeFormat(msg, args));
        }
    }

    /**
     * 开发环境下输出Log。使用示例：
     * <pre>
     * L.data("TAG", "count = %d, text = %s", count, text);
     * </pre>
     *
     * @param tag  TAG
     * @param msg  输出的消息，支持String.format字符串，例如"count = %d, text = %s"
     * @param args String.format格式化参数
     */
    public static void d(@NonNull String tag, String msg, Object... args) {

        if (ENABLE_LOG) {
            Log.d(tag, safeFormat(msg, args));
        }
    }

    /**
     * 开发环境下输出Log。使用示例：
     * <pre>
     * L.data("TAG", "count = %d, text = %s", count, text);
     * </pre>
     *
     * @param tag  TAG
     * @param msg  输出的消息，支持String.format字符串，例如"count = %d, text = %s"
     * @param args String.format格式化参数
     */
    public static void i(@NonNull String tag, String msg, Object... args) {
        if (ENABLE_LOG) {
            Log.i(tag, safeFormat(msg, args));
        }
    }

    /**
     * 开发环境下输出Log。使用示例：
     * <pre>
     * L.data("TAG", "count = %d, text = %s", count, text);
     * </pre>
     *
     * @param tag  TAG
     * @param msg  输出的消息，支持String.format字符串，例如"count = %d, text = %s"
     * @param args String.format格式化参数
     */
    public static void w(@NonNull String tag, String msg, Object... args) {
        if (ENABLE_LOG) {
            Log.println(Log.WARN, tag, safeFormat(msg, args));
        }
    }

    /**
     * 开发环境下输出Log。使用示例：
     * <pre>
     * L.data("TAG", "count = %d, text = %s", count, text);
     * </pre>
     *
     * @param tag  TAG
     * @param msg  输出的消息，支持String.format字符串，例如"count = %d, text = %s"
     * @param args String.format格式化参数
     */
    public static void e(@NonNull String tag, String msg, Object... args) {


        if (ENABLE_LOG) {
            Log.e(tag, safeFormat(msg, args));
        }
    }

    /**
     * 开发环境下输出Log。
     *
     * @param t 发生的异常
     */
    public static void e(Throwable t) {
        if (ENABLE_LOG) {
            Log.e("Throwable", Log.getStackTraceString(t));
        }
    }

    /**
     * 开发环境下输出Log。
     *
     * @param tag TAG
     * @param t   发生的异常
     */
    public static void e(@NonNull String tag, Throwable t) {
        if (ENABLE_LOG) {
            Log.e(tag, Log.getStackTraceString(t));
        }
    }

    /**
     * 开发环境下输出Log。
     *
     * @param tag 使用SimpleClassName作为TAG
     * @param msg 输出的消息
     * @param t   发生的异常
     */
    public static void e(@NonNull String tag, String msg, Throwable t) {
        if (ENABLE_LOG) {
            Log.e(tag, msg, t);
        }
    }

    private static String safeFormat(String msg, Object... args) {
        return args == null || args.length == 0 ? msg : String.format(msg, args);
    }

}
