package maktub.vn.maktub.utils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;

import maktub.vn.maktub.R;
import maktub.vn.maktub.utils.dialog.MaktubDialog;

public class NetworkUtils {
    public static final int TIMEOUT_DEFAULT = 30000;
    //	public enum RequestMethod {
//		GET,
//		POST,
//		PUT,
//		DELETE
//	}

    /**
     * Format an url with parameter
     *
     * @param url
     * @param params
     * @return a new url has been formatted
     */
    public static String formatUrl(String url, Map<String, Object> params) {
        if (params != null && params.size() > 0) {
            StringBuilder sb = new StringBuilder(url);
            if (!url.endsWith("?")) sb.append('?');
            for (String key : params.keySet()) {
                sb.append(key).append('=').append(params.get(key).toString()).append('&');
            }
            sb.deleteCharAt(sb.length() - 1);
            url = sb.toString();
        }
        AppLog.i(url);
        return url;
    }

    /**
     * Check network available
     *
     * @param context
     * @return true if has connect, false if otherwise
     */
    public static boolean hasNetWork(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public static void showDialogError(final Activity activity, final Exception e) {
        showDialogError(activity, e, null, null, null, null);
    }

    public static void showDialogError(final Activity activity, final Exception e, View.OnClickListener action) {
        showDialogError(activity, e, null, action, null, null);
    }

    public static void showDialogError(final Activity activity, final Exception e, final String btnOk, final View.OnClickListener actionOk, final String btnCancel, final View.OnClickListener actionCancel) {
        if (activity.isFinishing())
            return;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Resources res = activity.getResources();
                String msg;
                if (!NetworkUtils.hasNetWork(activity)) {
                    msg = res.getString(R.string.error_internet_lost);
                } else if (e instanceof UnknownHostException) {
                    msg = res.getString(R.string.error_unknown_host);
                } else if (e instanceof SocketTimeoutException) {
                    msg = res.getString(R.string.error_internet_timeout);
                } else {
                    msg = res.getString(R.string.error_network_general);
                }
                MaktubDialog.show(activity, msg, btnOk, actionOk, btnCancel, actionCancel);
            }
        });
    }
}
