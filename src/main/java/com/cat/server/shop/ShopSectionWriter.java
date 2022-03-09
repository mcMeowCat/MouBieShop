package com.cat.server.shop;

import com.moubieapi.moubieapi.yaml.Loader;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * 代表商店檔案細節部分寫入
 * @author MouBieCat
 */
final class ShopSectionWriter
        extends ShopSection {

    /**
     * 建構子
     * @param loader 檔案加載器
     */
    public ShopSectionWriter(final @NotNull Loader loader) {
        super(loader);
    }

    /**
     * 設置商店標題
     * @param name 商店名稱
     * @param title 標題
     */
    public void setShopTitle(final @NotNull String name, final @NotNull String title) {
        this.loader.set(ShopSection.replaceFormat(
                SHOP_TITLE_PATH, name), title
        );

        this.loader.save();
    }

    /**
     * 設置商店購買後給予的物品項目
     * @param name 商店名稱
     * @param item 物品項目
     */
    public void setShopGiveItem(final @NotNull String name, final @NotNull ItemStack item) {
        this.loader.set(
                ShopSection.replaceFormat(SHOP_GIVE_ITEM_PATH, name), item
        );

        this.loader.save();
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
         * @param name 商店名稱
         * @param exp 經驗值
         */
        public void setShopBuyExp(final @NotNull String name, final int exp) {
            final Loader loader = this.shopSection.getLoader();

            loader.set(
                    ShopSection.replaceFormat(SHOP_BUY_MINECRAFT_EXP_PATH, name), exp
            );
            loader.save();
        }

        /**
         * 添加一個購買所需的物品
         * @param name 商店名稱
         * @param key  物品路徑示標符
         * @param item 物品
         */
        public boolean addShopBuyItems(final @NotNull String name, final @NotNull String key, final @NotNull ItemStack item) {
            final Loader loader = this.shopSection.getLoader();

            final String keyPath = ShopSection.replaceFormat(ShopMinecraftBuySection.SHOP_BUY_MINECRAFT_ITEMS_PATH, name) + key;

            if (!loader.getConfiguration().contains(keyPath)) {
                loader.set(keyPath, item);
                loader.save();
                return true;
            }

            return false;
        }

        /**
         * 刪除一個購買所需的物品
         * @param name 商店名稱
         * @param key 物品路徑示標符
         */
        @SuppressWarnings("all")
        public void removeShopBuyItems(final @NotNull String name, final @NotNull String key) {
            final Loader loader = this.shopSection.getLoader();

            final String keyPath = ShopSection.replaceFormat(ShopMinecraftBuySection.SHOP_BUY_MINECRAFT_ITEMS_PATH, name) + key;

            if (loader.getConfiguration().contains(keyPath)) {
                loader.set(keyPath, null);
                loader.save();
            }
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
         * @param name 商店名稱
         * @param point 插件點數
         */
        public void setShopPlayerPoints(final @NotNull String name, final int point) {
            final Loader loader = this.shopSection.getLoader();

            loader.set(ShopSection.replaceFormat(ShopPluginBuySection.SHOP_BUY_PLUGIN_PLAYER_POINT_PATH, name), point);
            loader.save();
        }

    }

}
