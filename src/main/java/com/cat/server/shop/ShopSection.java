package com.cat.server.shop;

import com.moubieapi.moubieapi.yaml.Loader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * 代表商店檔案細節部分操作基底類
 * @author MouBieCat
 */
abstract class ShopSection {

    protected static final String SHOP_TITLE_PATH = "Shop.{name}.title";

    protected static final String SHOP_GIVE_ITEM_PATH = "Shop.{name}.give_item";

    // 檔案加載器
    @NotNull
    protected final Loader loader;

    /**
     * 建構子
     * @param loader 檔案加載器
     */
    public ShopSection(final @NotNull Loader loader) {
        this.loader = loader;
    }

    /**
     * 轉換商店名稱到格式
     * @param format 格式
     * @param shopName 商店名稱
     * @return 轉換後的格式
     */
    @NotNull
    public static String replaceFormat(final @NotNull String format, final @NotNull String shopName) {
        return format.replace("{name}", shopName);
    }

    /**
     * 獲取檔案加載器
     * @return 加載器
     */
    @NotNull
    protected final Loader getLoader() {
        return this.loader;
    }

    /**
     * 代表商店購買所需的條件部分基底類
     * @author MouBieCat
     */
    private static abstract class ShopBuySectionBase {

        // 商店部分操作類
        @NotNull
        protected ShopSection shopSection;

        /**
         * 建構子
         * @param shopSection 商店部分操作類
         */
        public ShopBuySectionBase(final @NotNull ShopSection shopSection) {
            this.shopSection = shopSection;
        }

        /**
         * 獲取商店部分操作類
         * @return 商店部分操作類
         */
        @NotNull
        public final ShopSection getShopSection() {
            return this.shopSection;
        }

    }

    /**
     * 代表商店購買所需的條件部分(Minecraft)
     * @author MouBieCat
     */
    protected static abstract class ShopMinecraftBuySection
            extends ShopBuySectionBase {

        protected static final String SHOP_BUY_MINECRAFT_EXP_PATH = "Shop.{name}.buy.MINECRAFT.exp";

        protected static final String SHOP_BUY_MINECRAFT_ITEMS_PATH = "Shop.{name}.buy.MINECRAFT.items";


        /**
         * 建構子
         * @param shopSection 商店部分操作類
         */
        public ShopMinecraftBuySection(final @NotNull ShopSection shopSection) {
            super(shopSection);
        }

    }

    /**
     * 代表商店購買所需的條件部分(Plugin)
     * @author MouBieCat
     */
    protected static abstract class ShopPluginBuySection
            extends ShopBuySectionBase {

        protected static final String SHOP_BUY_PLUGIN_PLAYER_POINT_PATH = "Shop.{name}.buy.PLUGIN.PlayerPoint";

        protected static final String SHOP_BUY_PLUGIN_VAULT_PATH = "Shop.{name}.buy.PLUGIN.Vault";

        /**
         * 建構子
         * @param shopSection 商店部分操作類
         */
        public ShopPluginBuySection(final @NotNull ShopSection shopSection) {
            super(shopSection);
        }

        /**
         * 該插件依賴是否存在
         * @param name 插件名稱
         * @return 是否可依賴
         */
        protected final boolean isHookPlugin(final @NotNull String name) {
            final Plugin plugin = Bukkit.getPluginManager().getPlugin(name);
            return plugin != null && plugin.isEnabled();
        }

    }

}
