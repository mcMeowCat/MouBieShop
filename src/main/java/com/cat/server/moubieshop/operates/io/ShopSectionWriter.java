package com.cat.server.moubieshop.operates.io;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * 代表商店檔案細節部分寫入
 * @author MouBieCat
 */
public final class ShopSectionWriter
        extends ShopSection {



    /**
     * 建構子
     * @param storeName 店鋪名稱
     */
    public ShopSectionWriter(final @NotNull String storeName) {
        super(storeName);
    }

    /**
     * 創建一個商店
     * @param shopName 商店名
     * @return 是否創建成功
     */
    public boolean createShop(final @NotNull String shopName) {
        return !this.configuration.contains(STORE_SHOPS_PATH + "." + shopName);
    }

    /**
     * 刪除一個商店
     * @param shopName 商店名
     */
    public void removeShop(final @NotNull String shopName) {
        if (this.configuration.contains(STORE_SHOPS_PATH + "." + shopName))
            this.configuration.set(STORE_SHOPS_PATH + "." + shopName, null);
    }

    /**
     * 設定店鋪標題
     * @param title 標題
     */
    public void setStoreTitle(final @NotNull String title) {
        this.set(STORE_TITLE_PATH, title);
        this.save();
    }

    /**
     * 設置商店購買後給予的物品項目
     * @param shopName 商店名稱
     * @param item 物品項目
     */
    public void setShopGiveItem(final @NotNull String shopName, final @NotNull ItemStack item) {
        this.set(replaceFormat(SHOP_GIVE_ITEM_PATH, shopName), item);
        this.save();
    }

    /**
     * 轉向寫入購買所需的條件部分(Minecraft)
     * @return 條件部分寫入(Minecraft)
     */
    @NotNull
    public ShopMinecraftBuySectionWriter toMinecraftBuySectionWriter() {
        return new ShopMinecraftBuySectionWriter(this);
    }

    /**
     * 轉向寫入購買所需的條件部分(Plugin)
     * @return 條件部分寫入(Plugin)
     */
    @NotNull
    public ShopPluginBuySectionWriter toPluginBuySectionWriter() {
        return new ShopPluginBuySectionWriter(this);
    }

    /**
     * 代表商店購買所需的條件部分讀取(Minecraft)
     * @author MouBieCat
     */
    public final static class ShopMinecraftBuySectionWriter
            extends ShopSection.ShopMinecraftBuySection {

        /**
         * 建構子
         * @param shopSection 商店部分操作類
         */
        public ShopMinecraftBuySectionWriter(final @NotNull ShopSection shopSection) {
            super(shopSection);
        }

        /**
         * 寫入購買所需的經驗值
         * @param shopName 商店名稱
         * @param exp 經驗值
         */
        public void setShopBuyExp(final @NotNull String shopName, final int exp) {
            this.shopSection.set(replaceFormat(SHOP_BUY_MINECRAFT_EXP_PATH, shopName), exp);
            this.shopSection.save();
        }

        /**
         * 添加一個購買所需的物品
         * @param shopName 商店名稱
         * @param key  物品路徑示標符
         * @param item 物品
         */
        public boolean addShopBuyItems(final @NotNull String shopName, final @NotNull String key, final @NotNull ItemStack item) {
            final String keyPath =
                    replaceFormat(SHOP_BUY_MINECRAFT_ITEMS_PATH, shopName) + "." + key;

            if (!this.shopSection.getConfiguration().contains(keyPath)) {
                this.shopSection.set(keyPath, item);
                this.shopSection.save();
                return true;
            }

            return false;
        }

        /**
         * 刪除一個購買所需的物品
         * @param shopName 商店名稱
         * @param key 物品路徑示標符
         */
        @SuppressWarnings("all")
        public void removeShopBuyItems(final @NotNull String shopName, final @NotNull String key) {
            final String keyPath =
                    replaceFormat(SHOP_BUY_MINECRAFT_ITEMS_PATH, shopName) + "." + key;

            this.shopSection.set(keyPath, null);
            this.shopSection.save();
        }

    }

    /**
     * 代表商店購買所需的條件部分讀取(Plugin)
     * @author MouBieCat
     */
    public final static class ShopPluginBuySectionWriter
            extends ShopSection.ShopPluginBuySection {

        /**
         * 建構子
         * @param shopSection 商店部分操作類
         */
        public ShopPluginBuySectionWriter(final @NotNull ShopSection shopSection) {
            super(shopSection);
        }

        /**
         * 寫入購買所需的 PlayerPoint 插件點數
         * @param shopName 商店名稱
         * @param point 插件點數
         */
        public void setShopPlayerPoints(final @NotNull String shopName, final int point) {
            this.shopSection.set(replaceFormat(SHOP_BUY_PLUGIN_PLAYER_POINT_PATH, shopName), point);
            this.shopSection.save();
        }

        /**
         * 寫入購買所需的 Vault 插件金錢
         * @param shopName 商店名稱
         * @param vault 插件金錢
         */
        public void setShopVault(final @NotNull String shopName, final double vault) {
            this.shopSection.set(replaceFormat(SHOP_BUY_PLUGIN_VAULT_PATH, shopName), vault);
            this.shopSection.save();
        }

    }

}
