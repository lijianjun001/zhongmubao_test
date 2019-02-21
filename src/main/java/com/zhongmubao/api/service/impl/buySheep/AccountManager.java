package com.zhongmubao.api.service.impl.buySheep;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private Map<String, AccountInfo> userInfoMap = new HashMap<>();
    private static volatile AccountManager accountManager;


    public AccountManager() {

    }

    public Map<String, AccountInfo> getUserInfoMap() {
        return userInfoMap;
    }

    public static AccountManager getInstance() {
        if (accountManager == null) {
            synchronized (AccountManager.class) {
                if (accountManager == null) {
                    accountManager = new AccountManager();
                }
            }
        }
        return accountManager;
    }

    public AccountInfo getAccountInfo(String account) {
        return userInfoMap.get(account);
    }

    public void saveAccountInfo(AccountInfo accountInfo) {
        userInfoMap.put(accountInfo.getAccount(), accountInfo);
    }

}
