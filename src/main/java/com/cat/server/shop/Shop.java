package com.cat.server.shop;

import com.cat.server.loader.ShopOperate;
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

    /**
     * 商店名稱 (檔案名稱)
     * @param shopName 商店名稱
     */
    public Shop(final @NotNull String shopName) {
        this.operate = new ShopOperate(shopName + ".yml");
    }

    /**
     * 獲取商店標題
     * @return 標題
     */
    @NotNull
    public String getShopTitle() {
        return this.operate.READER.getShopTitle();
    }

    /**
     * 設置商店標題
     * @param title 標題
     */
    public void setShopTitle(final @NotNull String title) {
        this.operate.WRITER.setShopTitle(title);
    }

    /**
     * 獲取購買後的物品項目
     * @return 物品項目
     */
    @NotNull
    public ItemStack getShopGiveItem() {
        return this.operate.READER.getShopGiveItem();
    }

    /**
     * 設置商店購買後給予的物品項目
     * @param item 物品項目
     */
    public void setShopGiveItem(final @NotNull ItemStack item) {
        this.operate.WRITER.setShopGiveItem(item);
    }

    /**
     * 獲取購買所需的經驗值
     * @return 經驗值
     */
    public int getShopBuyExp() {
        return this.operate.READER.toMinecraftBuySectionReader().getShopBuyExp();
    }

    /**
     * 寫入購買所需的經驗值
     * @param exp 經驗值
     */
    public void setShopBuyExp(final int exp) {
        this.operate.WRITER.toMinecraftBuySectionWriter().setShopBuyExp(exp);
    }

    /**
     * 獲取購買所需的物品項目
     * @return 物品項目
     */
    @NotNull
    public Map<String, ItemStack> getShopBuyItems() {
        return this.operate.READER.toMinecraftBuySectionReader().getShopBuyItems();
    }

    /**
     * 添加一個購買所需的物品
     * @param key 物品路徑示標符
     * @param item 物品
     */
    public void addShopBuyItems(final @NotNull String key, final @NotNull ItemStack item) {
        this.operate.WRITER.toMinecraftBuySectionWriter().addShopBuyItems(key, item);
    }

    /**
     * 刪除一個購買所需的物品
     * @param key 物品路徑示標符
     */
    public void removeShopBuyItems(final @NotNull String key) {
        this.operate.WRITER.toMinecraftBuySectionWriter().removeShopBuyItems(key);
    }

    /**
     * 獲取購買所需的 PlayerPoint 插件點數
     * @return 插件點數
     */
    public int getShopPlayerPoint() {
        return this.operate.READER.toPluginBuySectionReader().getShopPlayerPoint();
    }

    /**
     * 寫入購買所需的 PlayerPoint 插件點數
     * @param point 插件點數
     */
    public void setShopPlayerPoint(final int point) {
        this.operate.WRITER.toPluginBuySectionWriter().setShopPlayerPoint(point);
    }

}
