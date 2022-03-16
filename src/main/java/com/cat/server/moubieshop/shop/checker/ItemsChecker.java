package com.cat.server.moubieshop.shop.checker;

import com.cat.server.api.shop.checker.Checker;
import com.cat.server.api.shop.Shop;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * 檢查玩家物品
 * @author MouBieCat
 */
public final class ItemsChecker
        implements Checker {

    /**
     * 檢查
     * @param shop   商店
     * @param player 玩家
     * @return 是否通過
     */
    public boolean check(final @NotNull Shop shop, final @NotNull Player player) {
        final Collection<ItemStack> items = shop.getBuyItems().values();

        for (final ItemStack item : items) {
            if (!this.check0(item, player))
                return false;
        }

        return true;
    }

    /**
     * 細部檢查玩家是否擁有該物品
     * @param itemStack 物品
     * @param player 玩家
     * @return 是否擁有
     */
    private boolean check0(final @NotNull ItemStack itemStack, final @NotNull Player player) {
        final PlayerInventory inventory = player.getInventory();

        for (int checkSlotId = 0; checkSlotId <= 35; checkSlotId++) {
            final @Nullable ItemStack item = inventory.getItem(checkSlotId);

            if (itemStack.equals(item))
                return true;
        }

        return false;
    }

}
