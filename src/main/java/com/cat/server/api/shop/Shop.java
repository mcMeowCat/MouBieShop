package com.cat.server.api.shop;

import com.cat.server.api.result.BuyResult;
import com.cat.server.api.shop.checker.CheckerManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
     */
    void setBuyItem(@NotNull String key, @Nullable ItemStack item);

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

    /**
     * 獲取購買檢查器
     * @return 檢查器
     */
    @NotNull CheckerManager getBuyCheckers();

    /**
     * 購買該物品
     * @param player 玩家
     * @param noChecker 是否不用經過購買檢查
     */
    @NotNull BuyResult buy(@NotNull Player player, boolean noChecker);
    
}
