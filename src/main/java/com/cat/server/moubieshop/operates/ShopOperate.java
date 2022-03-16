package com.cat.server.moubieshop.operates;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * 代表一個商店操作類
 * @author MouBieCat
 */
public class ShopOperate
        extends OperateBase {

    /**
     * 建構子
     * @param storeName 店鋪檔案名稱
     */
    public ShopOperate(final @NotNull String storeName) {
        super(storeName);
    }

    /**
     * 獲取購買後的物品項目
     * @param shopName 商店名稱
     * @return 物品項目
     */
    @NotNull
    public ItemStack getShopGiveItem(final @NotNull String shopName) {
        return this.reader.getShopGiveItem(shopName);
    }

    /**
     * 設置商店購買後給予的物品項目
     * @param shopName 商店名稱
     * @param item 物品項目
     */
    public void setShopGiveItem(final @NotNull String shopName, final @NotNull ItemStack item) {
        this.writer.setShopGiveItem(shopName, item);
    }

    /**
     * 獲取購買所需的經驗值
     * @param shopName 商店名稱
     * @return 經驗值
     */
    public int getShopMinecraftBuyExp(final @NotNull String shopName) {
        return this.reader.toMinecraftBuySectionReader().getShopBuyExp(shopName);
    }

    /**
     * 寫入購買所需的經驗值
     * @param shopName 商店名稱
     * @param exp 經驗值
     */
    public void setShopMinecraftBuyExp(final @NotNull String shopName, final int exp) {
        this.writer.toMinecraftBuySectionWriter().setShopBuyExp(shopName, exp);
    }

    /**
     * 獲取購買所需的物品項目
     * @param shopName 商店名稱
     * @return 物品項目
     */
    @NotNull
    public Map<String, ItemStack> getShopMinecraftBuyItems(final @NotNull String shopName) {
        return this.reader.toMinecraftBuySectionReader().getShopBuyItems(shopName);
    }

    /**
     * 添加一個購買所需的物品
     * @param shopName 商店名稱
     * @param key  物品路徑示標符
     * @param item 物品
     */
    public boolean addShopMinecraftBuyItem(final @NotNull String shopName, final @NotNull String key, final @NotNull ItemStack item) {
        return this.writer.toMinecraftBuySectionWriter().addShopBuyItems(shopName, key, item);
    }

    /**
     * 刪除一個購買所需的物品
     * @param shopName 商店名稱
     * @param key 物品路徑示標符
     */
    public void removeShopMinecraftBuyItem(final @NotNull String shopName, final @NotNull String key) {
        this.writer.toMinecraftBuySectionWriter().removeShopBuyItems(shopName, key);
    }

    /**
     * 獲取購買所需的 PlayerPoint 插件點數
     * @param shopName 商店名稱
     * @return 插件點數
     */
    public int getShopPluginBuyPlayerPoints(final @NotNull String shopName) {
        return this.reader.toPluginBuySectionReader().getShopPlayerPoints(shopName);
    }

    /**
     * 寫入購買所需的 PlayerPoint 插件點數
     * @param shopName 商店名稱
     * @param point 插件點數
     */
    public void setShopPluginBuyPlayerPoints(final @NotNull String shopName, final int point) {
        this.writer.toPluginBuySectionWriter().setShopPlayerPoints(shopName, point);
        this.writer.toPluginBuySectionWriter().setShopPlayerPoints(shopName, point);
    }

    /**
     * 獲取購買所需的 PlayerPoint 插件點數
     * @param shopName 商店名稱
     * @return 插件點數
     */
    public double getShopPluginVaultMoney(final @NotNull String shopName) {
        return this.reader.toPluginBuySectionReader().getShopVault(shopName);
    }

    /**
     * 寫入購買所需的 Vault 插件金錢
     * @param shopName 商店名稱
     * @param money 插件金錢
     */
    public void setShopPluginVaultMoney(final @NotNull String shopName, final double money) {
        this.writer.toPluginBuySectionWriter().setShopVault(shopName, money);
    }

}
