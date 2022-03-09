package com.cat.server.loader;

import com.cat.server.manager.ShopManager;
import com.cat.server.shop.Shop;
import com.cat.server.shop.ShopOperate;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

/**
 * 代表商店加載器
 * @author MouBieCat
 */
public final class ShopLoader {

    /**
     * 加載所有商店
     * @param manager 管理器
     * @return 商店總數
     */
    public int loadShops(final @NotNull ShopManager manager) {
        final ShopOperate loader = manager.getOperate();

        final ConfigurationSection shops = loader.getConfiguration().getConfigurationSection("Shops");
        if (shops == null)
            return 0;

        int count = 0;
        for (final String shopName : shops.getKeys(false)) {
            final Shop shop = new Shop(loader, shopName);

            manager.add(shopName, shop);
            count++;
        }

        return count;
    }

}
