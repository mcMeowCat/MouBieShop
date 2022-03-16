package com.cat.server.moubieshop.store;

import com.moubiecat.api.Utils;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 代表店鋪資訊
 * @author MouBieCat
 */
public final class StoreInfo {

    // 店鋪標題
    @NotNull
    private String title = "§8沫白的小店鋪";

    /**
     * 建構子
     */
    public StoreInfo(final @Nullable String title) {
        if (title != null)
            this.setTitle(title);
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
