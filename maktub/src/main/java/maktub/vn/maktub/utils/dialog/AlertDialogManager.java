package maktub.vn.maktub.utils.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;

import maktub.vn.maktub.R;

public class AlertDialogManager {

    private static AlertDialog dialog;
    private static OnClickListener listenerDefault = new OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    };

    public static void show(Context context, String message) {
        show(context, null, message);
    }

    public static void show(Context context, String title, String message) {
        show(context, title, message, context.getString(R.string.btn_accept), listenerDefault, null, null);
    }

    public static void show(Context context, String title, String message, String txtAccept) {
        show(context, title, message, txtAccept, listenerDefault, null, null);
    }

    public static void show(Context context, String title, String message, OnClickListener accept) {
        show(context, title, message, context.getString(R.string.btn_accept), accept, null, null);
    }

    public static void show(Context context, String title, String message,
                            String txtAccept, OnClickListener accept) {
        show(context, title, message, txtAccept, accept, null, null);
    }

    public static void show(Context context, String title, String message,
                            OnClickListener accept, OnClickListener cancel) {
        show(context, title, message,
                context.getString(R.string.btn_accept), accept,
                context.getString(R.string.btn_cancel), cancel);
    }

    public static void show(Context context, String title, String message,
                            String txtAccept, OnClickListener accept,
                            String txtCancel, OnClickListener cancel) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) builder.setTitle(title);
        if (!TextUtils.isEmpty(message)) builder.setMessage(message);

        if (accept != null) {
            if (TextUtils.isEmpty(txtAccept))
                builder.setPositiveButton(R.string.btn_accept, accept);
            else
                builder.setPositiveButton(txtAccept, accept);
        }
        if (cancel != null) {
            if (TextUtils.isEmpty(txtCancel))
                builder.setNegativeButton(R.string.btn_cancel, cancel);
            else
                builder.setNegativeButton(txtCancel, cancel);
        }
        dialog = builder.show();
    }
}
