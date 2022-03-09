package com.cat.server.shop;

import com.cat.server.loader.ShopOperate;
import com.moubieapi.api.Utils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * 代表一個商店
 * @author MouBieCat
 */
public final class Shop {

    // 總店鋪名稱
    @NotNull
    private final String store;

    // 商店名稱
    @NotNull
    private final String name;

    // 商店標題
    @NotNull
    private String title;

    // 商品項目
    @NotNull
    private ItemStack giveItem;

    // 購買商品所需的條件(Minecraft)
    @NotNull
    private final MinecraftDemandContent minecraftDemandContent = new MinecraftDemandContent();

    // 購買商品所需的條件(Plugin)
    @NotNull
    private final PluginDemandContent pluginDemandContent = new PluginDemandContent();

    /**
     * 商店名稱 (檔案名稱)
     * @param shopName 商店名稱
     */
    public Shop(final @NotNull String storeName, final @NotNull String shopName) {
        // 商店操作加載
        final ShopOperate shopOperate = this.getShopOperate();

        // 商店基本訊息加載
        this.store = storeName;
        this.name = shopName;
        this.title = shopOperate.reader.getShopTitle(shopName);
        this.giveItem = shopOperate.reader.getShopGiveItem(shopName);

        // 購買條件 (Minecraft)
        this.minecraftDemandContent.setExp(
                shopOperate.reader.toMinecraftBuySectionReader().getShopBuyExp(shopName)
        );

        this.minecraftDemandContent.setItems(
                shopOperate.reader.toMinecraftBuySectionReader().getShopBuyItems(shopName)
        );

        // 購買條件 (Plugin)
        this.pluginDemandContent.setPlayerPoints(
                shopOperate.reader.toPluginBuySectionReader().getShopPlayerPoints(shopName)
        );
    }

    /**
     * 獲取總店鋪名稱
     * @return 店鋪名稱
     */
    @NotNull
    public String getStore() {
        return this.store;
    }

    /**
     * 獲取商店名稱
     * @return 名稱
     */
    @NotNull
    public String getName() {
        return this.name;
    }

    /**
     * 獲取商店標題
     * @return 標題
     */
    @NotNull
    public String getShopTitle() {
        return this.title;
    }

    /**
     * 設置商店標題
     * @param title 標題
     */
    public void setShopTitle(final @NotNull String title) {
        final ShopOperate shopOperate = this.getShopOperate();
        shopOperate.writer.setShopTitle(this.name, title);
        this.title = ChatColor.translateAlternateColorCodes('&', Utils.forMessageToRGB(title));
    }

    /**
     * 獲取購買後的物品項目
     * @return 物品項目
     */
    @NotNull
    public ItemStack getShopGiveItem() {
        return this.giveItem;
    }

    /**
     * 設置商店購買後給予的物品項目
     * @param item 物品項目
     */
    public void setShopGiveItem(final @NotNull ItemStack item) {
        final ShopOperate shopOperate = this.getShopOperate();
        shopOperate.writer.setShopGiveItem(this.name, item);
        this.giveItem = item;
    }

    /**
     * 獲取購買所需的經驗值
     * @return 經驗值
     */
    public int getShopBuyExp() {
        return this.minecraftDemandContent.getExp();
    }

    /**
     * 寫入購買所需的經驗值
     * @param exp 經驗值
     */
    public void setShopBuyExp(final int exp) {
        final ShopOperate shopOperate = this.getShopOperate();
        shopOperate.writer.toMinecraftBuySectionWriter().setShopBuyExp(this.name, exp);
        this.minecraftDemandContent.setExp(exp);
    }

    /**
     * 獲取購買所需的物品項目
     * @return 物品項目
     */
    @NotNull
    public Map<String, ItemStack> getShopBuyItems() {
        return this.minecraftDemandContent.getItems();
    }

    /**
     * 添加一個購買所需的物品
     * @param key 物品路徑示標符
     * @param item 物品
     */
    public boolean addShopBuyItems(final @NotNull String key, final @NotNull ItemStack item) {
        final ShopOperate shopOperate = this.getShopOperate();

        if (shopOperate.writer.toMinecraftBuySectionWriter().addShopBuyItems(this.name, key, item)) {
            this.minecraftDemandContent.getItems().put(key, item);
            return true;
        }

        return false;
    }

    /**
     * 刪除一個購買所需的物品
     * @param key 物品路徑示標符
     */
    public void removeShopBuyItems(final @NotNull String key) {
        final ShopOperate shopOperate = this.getShopOperate();
        shopOperate.writer.toMinecraftBuySectionWriter().removeShopBuyItems(this.name, key);
        this.minecraftDemandContent.getItems().remove(key);
    }

    /**
     * 獲取購買所需的 PlayerPoint 插件點數
     * @return 插件點數
     */
    public int getShopPlayerPoint() {
        return this.pluginDemandContent.getPlayerPoints();
    }

    /**
     * 寫入購買所需的 PlayerPoint 插件點數
     * @param point 插件點數
     */
    public void setShopPlayerPoint(final int point) {
        final ShopOperate shopOperate = this.getShopOperate();
        shopOperate.writer.toPluginBuySectionWriter().setShopPlayerPoints(this.name, point);
        this.pluginDemandContent.setPlayerPoints(point);
    }

    /**
     * 獲取商店店鋪操作
     * @return 商店店鋪操作
     */
    @NotNull
    private ShopOperate getShopOperate() {
        return new ShopOperate(this.store);
    }

}
