package maktub.vn.maktub.utils.dialog;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import maktub.vn.maktub.R;

public class LoadingView extends LinearLayout {
    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LoadingView(Context context) {
        super(context);
        setGravity(Gravity.CENTER);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        ProgressBar progress = new ProgressBar(context);
        progress.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.prg_loading));
        addView(progress, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}