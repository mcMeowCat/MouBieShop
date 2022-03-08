package com.cat.server.loader;

import com.moubieapi.MouBieCat;
import com.moubieapi.moubieapi.yaml.Loader;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * 代表商店檔案細節部分讀取
 * @author MouBieCat
 */
public final class ShopSectionReader {

    private static final String SHOP_TITLE_PATH = "Shop.title";

    private static final String SHOP_GIVE_ITEM_PATH = "Shop.give_item";

    // 檔案加載器
    @NotNull
    private final Loader loader;

    /**
     * 建構子
     * @param loader 檔案加載器
     */
    public ShopSectionReader(final @NotNull Loader loader) {
        this.loader = loader;
    }

    /**
     * 獲取商店標題
     * @return 標題
     */
    @NotNull
    public String getShopTitle() {
        return this.loader.getString(SHOP_TITLE_PATH);
    }

    /**
     * 獲取購買後的物品項目
     * @return 物品項目
     */
    @NotNull
    public ItemStack getShopGiveItem() {
        return this.loader.getItemStack(SHOP_GIVE_ITEM_PATH);
    }

    /**
     * 轉向讀取購買所需的條件部分讀取(Minecraft)
     * @return 條件部分讀取(Minecraft)
     */
    @NotNull
    public ShopMinecraftBuySectionReader toMinecraftBuySectionReader() {
        return new ShopMinecraftBuySectionReader(this.loader);
    }

    /**
     * 轉向讀取購買所需的條件部分讀取(Plugin)
     * @return 條件部分讀取(Plugin)
     */
    @NotNull
    public ShopPluginBuySectionReader toPluginBuySectionReader() {
        return new ShopPluginBuySectionReader(this.loader);
    }

    /**
     * 代表商店購買所需的物品部分讀取
     * @author MouBieCat
     */
    private static class ShopBuySectionReader {
        // 檔案加載器
        protected final Loader loader;

        /**
         * 建構子
         * @param loader 檔案加載器
         */
        public ShopBuySectionReader(final @NotNull Loader loader) {
            this.loader = loader;
        }
    }

    /**
     * 代表商店購買所需的條件部分讀取(Minecraft)
     * @author MouBieCat
     */
    public static class ShopMinecraftBuySectionReader
            extends ShopBuySectionReader {

        private static final String SHOP_BUY_MINECRAFT_EXP_PATH = "Shop.buy.MINECRAFT.exp";

        private static final String SHOP_BUY_MINECRAFT_ITEMS_PATH = "Shop.buy.MINECRAFT.items";

        /**
         * 建構子
         * @param loader 檔案加載器
         */
        public ShopMinecraftBuySectionReader(final @NotNull Loader loader) {
            super(loader);
        }

        /**
         * 獲取購買所需的經驗值
         * @return 經驗值
         */
        public int getShopBuyExp() {
            return this.loader.getInt(SHOP_BUY_MINECRAFT_EXP_PATH);
        }

        /**
         * 獲取購買所需的物品項目
         * @return 物品項目
         */
        @NotNull
        public Map<String, ItemStack> getShopBuyItems() {
            final ConfigurationSection configurationSection = this.loader.getConfiguration().getConfigurationSection(SHOP_BUY_MINECRAFT_ITEMS_PATH);
            if (configurationSection == null)
                return new HashMap<>();

            final Map<String, ItemStack> itemStacks = new HashMap<>();

            for (final String key : configurationSection.getKeys(false))
                itemStacks.put(key, this.loader.getItemStack(SHOP_BUY_MINECRAFT_ITEMS_PATH + "." + key));

            return itemStacks;
        }

    }

    /**
     * 代表商店購買所需的條件部分讀取(Plugin)
     * @author MouBieCat
     */
    public static class ShopPluginBuySectionReader
            extends ShopBuySectionReader {

        private static final String SHOP_BUY_PLUGIN_PLAYER_POINT_PATH = "Shop.buy.PLUGIN.PlayerPoint";

        /**
         * 建構子
         * @param loader 檔案加載器
         */
        public ShopPluginBuySectionReader(final @NotNull Loader loader) {
            super(loader);
        }

        /**
         * 獲取購買所需的 PlayerPoint 插件點數
         * @return 插件點數
         */
        public int getShopPlayerPoint() {
            if (this.loader.getConfiguration().contains(SHOP_BUY_PLUGIN_PLAYER_POINT_PATH) && this.isHookPlugin("PlayerPoint"))
                return this.loader.getInt(SHOP_BUY_PLUGIN_PLAYER_POINT_PATH);

            MouBieCat.getInstance().getDebugger().warning("§c您的商店配置包含了 §6" + SHOP_BUY_PLUGIN_PLAYER_POINT_PATH + " §c部分，但是您的伺服器沒有裝載 §6PlayerPoints §c插件。");
            return 0;
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
