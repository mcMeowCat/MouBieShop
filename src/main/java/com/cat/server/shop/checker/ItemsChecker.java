package com.cat.server.shop.checker;

import com.cat.server.api.shop.Checker;
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
        final PlayerInventory playerInventory = player.getInventory();

        for (final ItemStack item : items) {
            boolean isFoundItem = false;

            for (int slotId = 0; slotId <= 35; slotId++) {
                final @Nullable ItemStack playerItem = playerInventory.getItem(slotId);
                if (playerItem == null)
                    continue;

                if (item.equals(playerItem)) {
                    isFoundItem = true;
                    break;
                }
            }

            if (!isFoundItem)
                return false;

        }

        return true;
    }

}
