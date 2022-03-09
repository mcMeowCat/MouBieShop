package com.cat.server.manager;

import com.cat.server.shop.Shop;
import com.cat.server.shop.ShopOperate;
import com.moubieapi.moubieapi.manager.ManagerAbstract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 代表商店管理器
 * @author MouBieCat
 */
public final class ShopManager
        extends ManagerAbstract<String, Shop> {

    // 店鋪商店操作
    @NotNull
    private final ShopOperate operate;

    // 店鋪資訊
    @NotNull
    private final ShopStoreInfo info;

    /**
     * 建構子
     * @param store 店鋪名稱
     */
    public ShopManager(final @NotNull String store) {
        this.operate = new ShopOperate(store);

        // 處理商店標題
        final @Nullable String storeTitle = this.operate.reader.getStoreTitle();
        this.info = storeTitle != null ? new ShopStoreInfo(store, storeTitle) : new ShopStoreInfo(store);
    }

    /**
     * 獲取店鋪商店操作
     * @return 商店操作
     */
    @NotNull
    public ShopOperate getOperate() {
        return this.operate;
    }

    /**
     * 獲取店鋪名稱
     * @return 名稱
     */
    @NotNull
    public String getShoreName() {
        return this.info.getStoreName();
    }

    /**
     * 獲取店鋪標題
     * @return 標題
     */
    @NotNull
    public String getStoreTitle() {
        return this.info.getTitle();
    }

    /**
     * 設定店鋪標題
     * @param title 標題
     */
    public void setStoreTitle(final @NotNull String title) {
        this.info.setTitle(title);

        this.operate.writer.setStoreTitle(title);
        this.operate.save();
    }

}
