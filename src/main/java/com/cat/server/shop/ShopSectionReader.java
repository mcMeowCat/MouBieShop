package com.cat.server.shop;

import com.moubieapi.moubieapi.yaml.Loader;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * 代表商店檔案細節部分讀取
 * @author MouBieCat
 */
final class ShopSectionReader
        extends ShopSection {

    /**
     * 建構子
     * @param loader 檔案加載器
     */
    public ShopSectionReader(final @NotNull Loader loader) {
        super(loader);
    }

    /**
     * 獲取商店標題
     * @param name 商店名稱
     * @return 標題
     */
    @NotNull
    public String getShopTitle(final @NotNull String name) {
        return this.loader.getString(
                ShopSection.replaceFormat(ShopSection.SHOP_TITLE_PATH, name)
        );
    }

    /**
     * 獲取購買後的物品項目
     * @param name 商店名稱
     * @return 物品項目
     */
    @NotNull
    public ItemStack getShopGiveItem(final @NotNull String name) {
        return this.loader.getItemStack(
                ShopSection.replaceFormat(ShopSection.SHOP_GIVE_ITEM_PATH, name)
        );
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
         * @param name 商店名稱
         * @return 經驗值
         */
        public int getShopBuyExp(final @NotNull String name) {
            return this.shopSection.getLoader().getInt(
                    ShopSection.replaceFormat(SHOP_BUY_MINECRAFT_EXP_PATH, name)
            );
        }

        /**
         * 獲取購買所需的物品項目
         * @param name 商店名稱
         * @return 物品項目
         */
        @NotNull
        public Map<String, ItemStack> getShopBuyItems(final @NotNull String name) {
            final Loader loader = this.shopSection.getLoader();

            final ConfigurationSection configurationSection =
                    loader.getConfiguration().getConfigurationSection(
                            ShopSection.replaceFormat(SHOP_BUY_MINECRAFT_ITEMS_PATH, name)
                    );

            if (configurationSection == null)
                return new HashMap<>();

            final Map<String, ItemStack> itemStacks = new HashMap<>();
            for (final String key : configurationSection.getKeys(false))
                itemStacks.put(key, loader.getItemStack(
                        ShopSection.replaceFormat(SHOP_BUY_MINECRAFT_ITEMS_PATH, name) + "." + key)
                );

            return itemStacks;
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
         * @param name 商店名稱
         * @return 插件點數
         */
        public int getShopPlayerPoints(final @NotNull String name) {
            return this.shopSection.getLoader().getInt(
                    ShopSection.replaceFormat(SHOP_BUY_PLUGIN_PLAYER_POINT_PATH, name)
            );
        }

    }

}
