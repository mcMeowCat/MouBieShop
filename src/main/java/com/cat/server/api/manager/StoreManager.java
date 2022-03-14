package com.cat.server.api.manager;

import com.cat.server.api.shop.Store;
import com.moubieapi.api.manager.Manager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 代表店鋪管理器
 * @author MouBieCat
 */
public interface StoreManager
        extends Manager<String, Store> {

    /**
     * 添加
     * @param storeName 店鋪名稱
     * @param store 可為空
     */
    @Override
    void add(@NotNull String storeName, @Nullable @Deprecated Store store);

}
