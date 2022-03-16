package com.cat.server.moubieshop.operates.io;

import com.cat.server.MouBieCat;
import com.moubiecat.core.yaml.Loader;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * 代表商店檔案細節部分操作基底類
 * @author MouBieCat
 */
abstract class ShopSection
        extends Loader {

    private static final String FILE_PATH = "shops" + File.separator;

    protected static final String STORE_SHOPS_PATH = "Shops";

    protected static final String STORE_TITLE_PATH = "info.title";

    protected static final String SHOP_GIVE_ITEM_PATH = "Shops.{name}.give_item";

    // 店鋪名稱
    @NotNull
    protected final String storeName;

    /**
     * 建構子
     * @param storeName 店鋪名稱
     */
    public ShopSection(final @NotNull String storeName) {
        super(MouBieCat.getInstance(), FILE_PATH, storeName + ".yml", true);
        this.storeName = storeName;
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

        protected static final String SHOP_BUY_MINECRAFT_EXP_PATH = "Shops.{name}.buy.MINECRAFT.exp";

        protected static final String SHOP_BUY_MINECRAFT_ITEMS_PATH = "Shops.{name}.buy.MINECRAFT.items";


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

        protected static final String SHOP_BUY_PLUGIN_PLAYER_POINT_PATH = "Shops.{name}.buy.PLUGIN.PlayerPoints";

        protected static final String SHOP_BUY_PLUGIN_VAULT_PATH = "Shops.{name}.buy.PLUGIN.Vault";

        /**
         * 建構子
         * @param shopSection 商店部分操作類
         */
        public ShopPluginBuySection(final @NotNull ShopSection shopSection) {
            super(shopSection);
        }

    }

}
