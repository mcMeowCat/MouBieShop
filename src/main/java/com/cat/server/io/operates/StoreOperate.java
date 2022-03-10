package com.cat.server.io.operates;

import com.cat.server.shop.Shop;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * 代表一個店鋪操作類
 * @author MouBieCat
 */
public final class StoreOperate
        extends ShopOperate {

    /**
     * 建構子
     * @param storeName 店鋪檔案名稱
     */
    public StoreOperate(final @NotNull String storeName) {
        super(storeName);
    }

    /**
     * 獲取店鋪標題
     * @return 標題
     */
    @Nullable
    public String getStoreTitle() {
        return this.reader.getStoreTitle();
    }

    /**
     * 設置新的店鋪標題
     * @param title 標題
     */
    public void setStoreTitle(final @NotNull String title) {
        this.writer.setStoreTitle(title);
    }

    /**
     * 創建一個商店
     * @param shopName 商店名稱
     */
    public boolean createShop(final @NotNull String shopName) {
        return this.writer.createShop(shopName);
    }

    /**
     * 刪除一個商店
     * @param shopName 商店名稱
     */
    public void removeShop(final @NotNull String shopName) {
        this.writer.removeShop(shopName);
    }

    /**
     * 從配置中解析所有商店
     * @return 所有商店
     */
    @NotNull
    public Map<String, Shop> parsingShops() {
        return this.reader.parsingShops(this);
    }

    /**
     * 刪除整個店鋪包含檔案
     * @return 是否成功刪除檔案
     */
    @SuppressWarnings("all")
    public boolean removeStore() {
        return this.writer.getFile().delete();
    }

}
