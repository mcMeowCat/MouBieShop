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

    // 商店操作
    private final ShopOperate operate;

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
    public Shop(final @NotNull String shopName) {
        this.operate = new ShopOperate(shopName + ".yml");

        // 商店基本訊息加載
        this.name = shopName;
        this.title = this.operate.READER.getShopTitle();
        this.giveItem = this.operate.READER.getShopGiveItem();

        // 購買條件 (Minecraft)
        this.minecraftDemandContent.setExp(
                this.operate.READER.toMinecraftBuySectionReader().getShopBuyExp()
        );

        this.minecraftDemandContent.setItems(
                this.operate.READER.toMinecraftBuySectionReader().getShopBuyItems()
        );

        // 購買條件 (Plugin)
        this.pluginDemandContent.setPlayerPoints(
                this.operate.READER.toPluginBuySectionReader().getShopPlayerPoints()
        );
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
        this.operate.WRITER.setShopTitle(title);
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
        this.operate.WRITER.setShopGiveItem(item);
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
        this.operate.WRITER.toMinecraftBuySectionWriter().setShopBuyExp(exp);
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
        if (this.operate.WRITER.toMinecraftBuySectionWriter().addShopBuyItems(key, item)) {
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
        this.operate.WRITER.toMinecraftBuySectionWriter().removeShopBuyItems(key);
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
        this.operate.WRITER.toPluginBuySectionWriter().setShopPlayerPoints(point);
        this.pluginDemandContent.setPlayerPoints(point);
    }

}
