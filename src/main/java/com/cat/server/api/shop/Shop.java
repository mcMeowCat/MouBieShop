package com.cat.server.api.shop;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface Shop {

    /**
     * 獲取商店名稱
     * @return 名稱
     */
    @NotNull String getName();

    /**
     * 獲取購買成功時給予的物品
     * @return 物品項目
     */
    @NotNull ItemStack getGiveItem();

    /**
     * 設定購買成功時給予的物品
     * @param item 物品項目
     */
    void setGiveItem(@NotNull ItemStack item);

    /**
     * 獲取購買時所需的經驗值
     * @return 經驗值
     */
    int getBuyExp();

    /**
     * 設定購買時所需的經驗值
     * @param exp 經驗值
     */
    void setBuyExp(int exp);

    /**
     * 獲取所有購買時所需的物品
     * @return 物品項目(s)
     */
    @NotNull Map<String, ItemStack> getBuyItems();

    /**
     * 添加一個購買時所需的物品
     * @param key 識別碼
     * @param item 物品項目
     * @return 是否添加成功
     */
    boolean addBuyItem(@NotNull String key, @NotNull ItemStack item);

    /**
     * 刪除一個購買時所需的物品
     * @param key 物品識別碼
     */
    void removeBuyItem(@NotNull String key);

    /**
     * 獲取購買時所需的 PlayerPoints 點數
     * @return 點數
     */
    int getBuyPlayerPoints();

    /**
     * 設定購買時所需的 PlayerPoints 點數
     * @param point 點數
     */
    void setBuyPlayerPoints(int point);

    /**
     * 獲取購買時所需的 Vault 金錢
     * @return 金錢
     */
    double getBuyVault();

    /**
     * 設置購買時所需的 Vault 金錢
     * @param money 金錢
     */
    void setBuyVault(double money);

}
