package com.cat.server.api.shop.checker;

import com.cat.server.api.shop.Shop;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 代表一個檢查器介面
 * @author MouBieCat
 */
public interface Checker {

    /**
     * 檢查
     * @param shop 商店
     * @param player 玩家
     * @return 是否通過
     */
    boolean check(@NotNull Shop shop, @NotNull Player player);

}
