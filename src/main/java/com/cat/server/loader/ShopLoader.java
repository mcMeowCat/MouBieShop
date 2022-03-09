package com.cat.server.loader;

import com.cat.server.MouBieCat;
import com.cat.server.shop.Shop;
import com.moubieapi.api.manager.Manager;
import com.moubieapi.moubieapi.yaml.Loader;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

/**
 * 代表商店加載器
 * @author MouBieCat
 */
public final class ShopLoader
        extends Loader {

    /**
     * 建構子
     * @param path 檔案路徑
     * @param name 檔案名稱
     */
    public ShopLoader(final @NotNull String path, final @NotNull String name) {
        super(MouBieCat.getInstance(), path, name, false);
    }

    /**
     * 加載所有商店
     * @param storeName 店鋪名稱
     * @param manager 管理器
     * @return 商店總數
     */
    public int loadShops(final @NotNull String storeName, final @NotNull Manager<String, Shop> manager) {
        final ConfigurationSection shops = this.configuration.getConfigurationSection("Shops");
        if (shops == null)
            return 0;

        int count = 0;
        for (final String shopName : shops.getKeys(false)) {
            final Shop shop = new Shop(storeName, shopName);
            manager.add(shopName, shop);
            count++;
        }

        return count;
    }

}
