package com.cat.server.shop.checker;

import com.cat.server.api.shop.Checker;
import com.cat.server.api.shop.Shop;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 檢查 Vault 是否足夠
 * @author MouBieCat
 */
public final class VaultChecker
        implements Checker {

    /**
     * 檢查
     * @param shop   商店
     * @param player 玩家
     * @return 是否通過
     */
    public boolean check(final @NotNull Shop shop, final @NotNull Player player) {
        final @Nullable Plugin vaultPlugin = Bukkit.getPluginManager().getPlugin("Vault");

        if (vaultPlugin == null)
            return true;

        final @NotNull Economy economy = (Economy) vaultPlugin;
        return economy.getBalance(player) >= shop.getBuyVault();
    }

}
