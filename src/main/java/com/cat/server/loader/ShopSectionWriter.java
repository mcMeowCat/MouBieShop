package com.cat.server.loader;

import com.moubieapi.MouBieCat;
import com.moubieapi.api.Utils;
import com.moubieapi.moubieapi.yaml.Loader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * 代表商店檔案細節部分寫入
 *
 * @author MouBieCat
 */
public record ShopSectionWriter(@NotNull Loader loader) {

    private static final String SHOP_TITLE_PATH = "Shop.title";

    private static final String SHOP_GIVE_ITEM_PATH = "Shop.give_item";

    /**
     * 建構子
     *
     * @param loader 檔案加載器
     */
    public ShopSectionWriter(final @NotNull Loader loader) {
        this.loader = loader;
    }

    /**
     * 設置商店標題
     * @param title 標題
     */
    public void setShopTitle(final @NotNull String title) {
        this.loader.set(SHOP_TITLE_PATH, title);
        this.loader.save();
    }

    /**
     * 設置商店購買後給予的物品項目
     * @param item 物品項目
     */
    public void setShopGiveItem(final @NotNull ItemStack item) {
        this.loader.set(SHOP_GIVE_ITEM_PATH, item);
        this.loader.save();
    }

    /**
     * 轉向寫入購買所需的條件部分(Minecraft)
     * @return 條件部分寫入(Minecraft)
     */
    @NotNull
    public ShopMinecraftBuySectionWriter toMinecraftBuySectionWriter() {
        return new ShopMinecraftBuySectionWriter(this.loader);
    }

    /**
     * 轉向寫入購買所需的條件部分(Plugin)
     * @return 條件部分寫入(Plugin)
     */
    @NotNull
    public ShopPluginBuySectionWriter toPluginBuySectionWriter() {
        return new ShopPluginBuySectionWriter(this.loader);
    }

    /**
     * 代表商店購買所需的物品部分讀取
     * @author MouBieCat
     */
    private static class ShopBuySectionWriter {
        // 檔案加載器
        protected final Loader loader;

        /**
         * 建構子
         *
         * @param loader 檔案加載器
         */
        public ShopBuySectionWriter(final @NotNull Loader loader) {
            this.loader = loader;
        }
    }

    /**
     * 代表商店購買所需的條件部分讀取(Minecraft)
     * @author MouBieCat
     */
    public final static class ShopMinecraftBuySectionWriter
            extends ShopBuySectionWriter {

        private static final String SHOP_BUY_MINECRAFT_EXP_PATH = "Shop.buy.MINECRAFT.exp";

        private static final String SHOP_BUY_MINECRAFT_ITEMS_PATH = "Shop.buy.MINECRAFT.items";

        /**
         * 寫入購買所需的經驗值
         * @param exp 經驗值
         */
        public void setShopBuyExp(final int exp) {
            this.loader.set(SHOP_BUY_MINECRAFT_EXP_PATH, exp);
            this.loader.save();
        }

        /**
         * 添加一個購買所需的物品
         * @param key  物品路徑示標符
         * @param item 物品
         */
        public boolean addShopBuyItems(final @NotNull String key, final @NotNull ItemStack item) {
            final String keyPath = SHOP_BUY_MINECRAFT_ITEMS_PATH + "." + key;

            if (!this.loader.getConfiguration().contains(SHOP_BUY_MINECRAFT_ITEMS_PATH + key)) {
                this.loader.set(keyPath, item);
                this.loader.save();
                return true;
            }

            return false;
        }

        /**
         * 刪除一個購買所需的物品
         * @param key 物品路徑示標符
         */
        @SuppressWarnings("all")
        public void removeShopBuyItems(final @NotNull String key) {
            final String keyPath = SHOP_BUY_MINECRAFT_ITEMS_PATH + "." + key;

            if (this.loader.getConfiguration().contains(keyPath))
                this.loader.set(keyPath, null);

            this.loader.save();
        }

        /**
         * 建構子
         * @param loader 檔案加載器
         */
        public ShopMinecraftBuySectionWriter(final @NotNull Loader loader) {
            super(loader);
        }
    }

    /**
     * 代表商店購買所需的條件部分讀取(Plugin)
     * @author MouBieCat
     */
    public final static class ShopPluginBuySectionWriter
            extends ShopBuySectionWriter {

        private static final String SHOP_BUY_PLUGIN_PLAYER_POINT_PATH = "Shop.buy.PLUGIN.PlayerPoints";

        /**
         * 建構子
         * @param loader 檔案加載器
         */
        public ShopPluginBuySectionWriter(final @NotNull Loader loader) {
            super(loader);
        }

        /**
         * 寫入購買所需的 PlayerPoint 插件點數
         * @param point 插件點數
         */
        public void setShopPlayerPoints(final int point) {
            this.loader.set(SHOP_BUY_PLUGIN_PLAYER_POINT_PATH, point);
            this.loader.save();
        }

        /**
         * 該插件依賴是否存在
         * @param name 插件名稱
         * @return 是否可依賴
         */
        private boolean isHookPlugin(final @NotNull String name) {
            final Plugin plugin = Bukkit.getPluginManager().getPlugin(name);
            return plugin != null && plugin.isEnabled();
        }
    }

}
