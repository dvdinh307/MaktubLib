package maktub.vn.maktub.utils.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initVariable();
        initControls(savedInstanceState);
    }


    protected abstract int getLayoutId();

    protected abstract void initVariable();

    protected abstract void initControls(Bundle bundle);


}
