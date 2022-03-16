package com.cat.server.moubieshop.store;

import com.cat.server.moubieshop.operates.StoreOperate;
import com.cat.server.api.shop.Shop;
import com.cat.server.api.shop.Store;
import com.cat.server.moubieshop.shop.ShopObject;
import com.moubiecat.core.manager.ManagerAbstract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 代表商店管理器
 * @author MouBieCat
 */
public final class ShopStore
        extends ManagerAbstract<String, Shop>
        implements Store {

    // 店鋪商店操作
    @NotNull
    private final StoreOperate operate;

    // 店鋪資訊
    @NotNull
    private final StoreInfo storeInfo;

    /**
     * 建構子
     * @param storeName 店鋪名稱
     */
    public ShopStore(final @NotNull String storeName) {
        this.operate = new StoreOperate(storeName);

        // 處理商店資訊
        this.storeInfo = new StoreInfo(this.operate.getStoreTitle());

        // 加載所有商店
        this.loadShops();
    }

    /**
     * 獲取店鋪名稱
     * @return 名稱
     */
    @NotNull
    public String getShoreName() {
        return this.operate.getStoreName();
    }

    /**
     * 獲取店鋪標題
     * @return 標題
     */
    @NotNull
    public String getStoreTitle() {
        return this.storeInfo.getTitle();
    }

    /**
     * 設定店鋪標題
     * @param title 標題
     */
    public void setStoreTitle(final @NotNull String title) {
        this.operate.setStoreTitle(title);
        this.storeInfo.setTitle(title);
    }

    /**
     * 從配置中讀取所有商店
     */
    private void loadShops() {
        this.clear();
        this.manager.putAll(this.operate.parsingShops());
    }

    /**
     * 刪除整個店鋪包含檔案
     */
    public void deleteStoreFile() {
        this.operate.removeStore();
    }

    /**
     * 添加一筆商店
     * @param key 商店名稱
     * @param value 商店實例 (設置為 null)
     */
    @Override
    public void add(final @NotNull String key, final @Nullable Shop value) {
        if (!this.hasKey(key) && this.operate.createShop(key))
            super.add(key, new ShopObject(key, this.operate));
    }

    /**
     * 刪除一筆商店
     * @param key 商店名稱
     */
    @Override
    public void remove(final @NotNull String key) {
        this.operate.removeShop(key);
        super.remove(key);
    }

}
