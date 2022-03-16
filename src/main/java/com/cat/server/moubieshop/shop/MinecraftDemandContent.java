package com.cat.server.moubieshop.shop;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * 代表購買商品的需求內容物(Minecraft)
 * @author MouBieCat
 */
public final class MinecraftDemandContent {

    // 經驗
    private int exp = 0;

    // 物品
    @NotNull
    private Map<String, ItemStack> items = new HashMap<>();

    /**
     * 獲取購買所需的經驗
     * @return 經驗
     */
    public int getExp() {
        return this.exp;
    }

    /**
     * 設定購買所需的經驗
     * @param exp 經驗
     */
    public void setExp(final int exp) {
        this.exp = exp;
    }

    /**
     * 獲取購買所需的物品
     * @return 所需的物品
     */
    @NotNull
    public Map<String, ItemStack> getItems() {
        return this.items;
    }

    /**
     * 設定購馬所需的物品
     * @param items 所需的物品
     */
    public void setItems(final @NotNull Map<String, ItemStack> items) {
        this.items = items;
    }

}
