package com.cat.server.shop.checker;

import com.cat.server.api.shop.Checker;
import com.cat.server.api.shop.Shop;
import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
        if (Bukkit.getPluginManager().getPlugin("PlayerPoints") == null)
            return true;

        return PlayerPoints.getInstance().getAPI().look(player.getUniqueId()) >= shop.getBuyPlayerPoints();
    }

}
