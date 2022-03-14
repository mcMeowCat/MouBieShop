package com.cat.server.api.shop;

import com.moubieapi.api.manager.Manager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Store
        extends Manager<String, Shop> {

    /**
     * 獲取店鋪名稱
     * @return 名稱
     */
    @NotNull String getShoreName();


    /**
     * 獲取店鋪標題
     * @return 標題
     */
    @NotNull String getStoreTitle();

    /**
     * 設定店鋪標題
     * @param title 標題
     */
    void setStoreTitle(@NotNull String title);

    /**
     * 從配置中讀取所有商店
     */
    void loadShops();

    /**
     * 刪除整個店鋪包含檔案
     */
    void deleteStoreFile();

    /**
     * 添加一個商店到該管理器中
     * @param shopName 商店名稱
     * @param shop 商店(可為空)
     */
    @Override
    void add(@NotNull String shopName, @Nullable @Deprecated Shop shop);

}
