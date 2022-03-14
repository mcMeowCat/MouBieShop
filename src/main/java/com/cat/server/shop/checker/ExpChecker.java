package com.cat.server.shop.checker;

import com.cat.server.api.shop.Checker;
import com.cat.server.api.shop.Shop;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 檢查經驗值是否足夠
 * @author MouBieCat
 */
public final class ExpChecker
        implements Checker {

    /**
     * 檢查
     * @param shop 商店
     * @param player 玩家
     * @return 是否通過
     */
    public boolean check(final @NotNull Shop shop, final @NotNull Player player) {
        return player.getLevel() >= shop.getBuyExp();
    }

}
