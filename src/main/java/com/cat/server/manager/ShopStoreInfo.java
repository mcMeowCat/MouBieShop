package com.cat.server.manager;

import com.moubieapi.api.Utils;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

/**
 * 代表店鋪資訊
 * @author MouBieCat
 */
public final class ShopStoreInfo {

    // 店鋪名稱
    @NotNull
    private final String storeName;

    // 標題
    @NotNull
    private String title = "§8沫白的小店鋪";

    /**
     * 建構子
     * @param storeName 店鋪名稱
     */
    public ShopStoreInfo(final @NotNull String storeName) {
        this.storeName = storeName;
    }

    /**
     * 建構子
     * @param storeName 店鋪名稱
     * @param title 標題
     */
    public ShopStoreInfo(final @NotNull String storeName, final @NotNull String title) {
        this.storeName = storeName;
        this.setTitle(title);
    }

    /**
     * 獲取店鋪名稱
     * @return 名稱
     */
    @NotNull
    public String getStoreName() {
        return this.storeName;
    }

    /**
     * 獲取店鋪標題
     * @return 標題
     */
    @NotNull
    public String getTitle() {
        return this.title;
    }

    /**
     * 設定店鋪標題
     * @param title 標題
     */
    public void setTitle(final @NotNull String title) {
        this.title = ChatColor.translateAlternateColorCodes('&', Utils.forMessageToRGB(title));
    }

}
