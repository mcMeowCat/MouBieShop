package com.cat.server.manager;

import com.cat.server.api.manager.StoreManager;
import com.cat.server.api.shop.Store;
import com.cat.server.shop.ShopStore;
import com.moubieapi.moubieapi.manager.ManagerAbstract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 代表店鋪管理器
 * @author MouBieCat
 */
public final class StoreManagerImp
        extends ManagerAbstract<String, Store>
        implements StoreManager {

    /**
     * 添加一筆紀錄
     * @param key 店鋪名稱
     * @param value 店鋪實例
     */
    @Override
    public void add(final @NotNull String key, final @Nullable @Deprecated Store value) {
        if (!this.hasKey(key))
            super.add(key, new ShopStore(key));
    }

    /**
     * 刪除一筆店鋪
     * @param key 店鋪名稱
     */
    @Override
    public void remove(final @NotNull String key) {
        final @Nullable Store shopManager = super.get(key);
        if (shopManager != null)
            shopManager.deleteStoreFile();

        super.remove(key);
    }

}
