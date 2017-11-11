package maktub.vn.maktub.utils.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;

import maktub.vn.maktub.R;

/**
 * Created by dinhdv on 7/31/2017.
 */

public class AccountUtils {

    public static Account getAccountUser(Activity activity) {
        AccountManager mAccountManager = AccountManager.get(activity);
        String type = activity.getString(R.string.account_type);
        Account[] accounts = mAccountManager.getAccountsByType(type);
        Account accountResult = null;
        if (accounts.length > 0)
            accountResult = accounts[0];
        return accountResult;
    }

    public static void store(Context context, String email, String password) {
        AccountManager mAccountManager = AccountManager.get(context);
        String type = context.getString(R.string.account_type);
        Account[] accounts = mAccountManager.getAccountsByType(type);
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
            mAccountManager.setPassword(account, password);
        } else {
            account = new Account(email, type);
            mAccountManager.addAccountExplicitly(account, password, null);
        }
    }

    public static void clearAllAccountOfThisApplication(Activity activity) {
        AccountManager mAccountManager = AccountManager.get(activity);
        String accountType = activity.getString(R.string.account_type);
        Account[] accounts = mAccountManager.getAccountsByType(accountType);
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
            mAccountManager.removeAccount(account, null, null);
        }
    }
}
