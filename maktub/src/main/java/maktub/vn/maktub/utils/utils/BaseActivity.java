package maktub.vn.maktub.utils.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        setContentView(getLayoutId());
        initVariable();
        initControls();
    }

    protected abstract int getLayoutId();

    protected abstract void initVariable();

    protected abstract void initControls();


}
