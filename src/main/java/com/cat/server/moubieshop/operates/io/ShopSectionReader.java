package com.cat.server.moubieshop.operates.io;

import com.cat.server.moubieshop.operates.ShopOperate;
import com.cat.server.moubieshop.shop.ShopObject;
import com.cat.server.api.shop.Shop;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * 代表商店檔案細節部分讀取
 * @author MouBieCat
 */
public final class ShopSectionReader
        extends ShopSection {

    /**
     * 建構子
     * @param storeName 店鋪名稱
     */
    public ShopSectionReader(final @NotNull String storeName) {
        super(storeName);
    }

    /**
     * 解析所有商店
     * @return 商店
     */
    @NotNull
    public Map<String, Shop> parsingShops(final @NotNull ShopOperate operate) {
        final @Nullable ConfigurationSection section = this.configuration.getConfigurationSection(STORE_SHOPS_PATH);
        if (section == null)
            return new HashMap<>();

        final Map<String, Shop> shops = new HashMap<>();
        for (final String shopName : section.getKeys(false))
            shops.put(shopName, new ShopObject(shopName, operate));

        return shops;
    }

    /**
     * 獲取店鋪標題
     * @return 標題
     */
    @Nullable
    public String getStoreTitle() {
        return this.getConfiguration().getString(STORE_TITLE_PATH);
    }

    /**
     * 獲取購買後的物品項目
     * @param shopName 商店名稱
     * @return 物品項目
     */
    @NotNull
    public ItemStack getShopGiveItem(final @NotNull String shopName) {
        return this.getItemStack(replaceFormat(SHOP_GIVE_ITEM_PATH, shopName));
    }

    /**
     * 轉向讀取購買所需的條件部分讀取(Minecraft)
     * @return 條件部分讀取(Minecraft)
     */
    @NotNull
    public ShopMinecraftBuySectionReader toMinecraftBuySectionReader() {
        return new ShopMinecraftBuySectionReader(this);
    }

    /**
     * 轉向讀取購買所需的條件部分讀取(Plugin)
     * @return 條件部分讀取(Plugin)
     */
    @NotNull
    public ShopPluginBuySectionReader toPluginBuySectionReader() {
        return new ShopPluginBuySectionReader(this);
    }

    /**
     * 代表商店購買所需的條件部分讀取(Minecraft)
     * @author MouBieCat
     */
    public final static class ShopMinecraftBuySectionReader
            extends ShopSection.ShopMinecraftBuySection {

        /**
         * 建構子
         * @param shopSection 商店部分操作類
         */
        public ShopMinecraftBuySectionReader(final @NotNull ShopSectionReader shopSection) {
            super(shopSection);
        }

        /**
         * 獲取購買所需的經驗值
         * @param shopName 商店名稱
         * @return 經驗值
         */
        public int getShopBuyExp(final @NotNull String shopName) {
            return this.shopSection.getInt(replaceFormat(SHOP_BUY_MINECRAFT_EXP_PATH, shopName));
        }

        /**
         * 獲取購買所需的物品項目
         * @param shopName 商店名稱
         * @return 物品項目
         */
        @NotNull
        public Map<String, ItemStack> getShopBuyItems(final @NotNull String shopName) {
            final String path = replaceFormat(SHOP_BUY_MINECRAFT_ITEMS_PATH, shopName);

            final @Nullable ConfigurationSection section = this.shopSection.getConfiguration().getConfigurationSection(path);
            if (section == null)
                return new HashMap<>();

            final Map<String, ItemStack> items = new HashMap<>();
            for (final String itemKey : section.getKeys(false))
                items.put(itemKey, this.shopSection.getItemStack(path + "." + itemKey));

            return items;
        }

    }

    /**
     * 代表商店購買所需的條件部分讀取(Plugin)
     * @author MouBieCat
     */
    public final static class ShopPluginBuySectionReader
            extends ShopSection.ShopPluginBuySection {


        /**
         * 建構子
         * @param shopSection 商店部分操作類
         */
        public ShopPluginBuySectionReader(final @NotNull ShopSectionReader shopSection) {
            super(shopSection);
        }

        /**
         * 獲取購買所需的 PlayerPoint 插件點數
         * @param shopName 商店名稱
         * @return 插件點數
         */
        public int getShopPlayerPoints(final @NotNull String shopName) {
            return this.shopSection.getInt(replaceFormat(SHOP_BUY_PLUGIN_PLAYER_POINT_PATH, shopName));
        }

        /**
         * 獲取購買所需的 PlayerPoint 插件點數
         * @param shopName 商店名稱
         * @return 插件點數
         */
        public double getShopVault(final @NotNull String shopName) {
            return this.shopSection.getDouble(replaceFormat(SHOP_BUY_PLUGIN_VAULT_PATH, shopName));
        }

    }

}
