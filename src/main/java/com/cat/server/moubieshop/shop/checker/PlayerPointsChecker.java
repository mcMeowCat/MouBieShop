package com.cat.server.moubieshop.shop.checker;

import com.cat.server.api.shop.checker.Checker;
import com.cat.server.api.shop.Shop;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 檢查 PlayerPoints 是否足夠
 * @author MouBieCat
 */
public final class PlayerPointsChecker
        implements Checker {

    /**
     * 檢查
     * @param shop   商店
     * @param player 玩家
     * @return 是否通過
     */
    public boolean check(final @NotNull Shop shop, final @NotNull Player player) {
        final @Nullable Plugin playerPoints = Bukkit.getPluginManager().getPlugin("PlayerPoints");
        if (playerPoints == null)
            return true;

        final PlayerPointsAPI api = ((PlayerPoints) playerPoints).getAPI();
        return api.look(player.getUniqueId()) >= shop.getBuyPlayerPoints();
    }

}
