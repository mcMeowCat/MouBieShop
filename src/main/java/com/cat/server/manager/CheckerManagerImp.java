package com.cat.server.manager;

import com.cat.server.api.manager.BuyCheckerManager;
import com.cat.server.api.shop.Checker;
import com.cat.server.shop.checker.*;
import com.moubieapi.moubieapi.manager.ManagerAbstract;
import org.jetbrains.annotations.NotNull;

/**
 * 購買檢查器管理器
 * @author MouBieCat
 */
public final class CheckerManagerImp
        extends ManagerAbstract<String, Checker>
        implements BuyCheckerManager {

    /**
     * 建構子
     */
    public CheckerManagerImp() {
        this.add("MINECRAFT:EXP_CHECKER", new ExpChecker());
        this.add("MINECRAFT:ITEMS_CHECKER", new ItemsChecker());
        this.add("PLUGIN:PLAYER_POINTS_CHECKER", new PlayerPointsChecker());
        this.add("PLUGIN:VAULT_CHECKER", new VaultChecker());
    }

    /**
     * 添加
     * @param key 檢查器名稱
     * @param value 檢查器
     */
    @Override
    public void add(final @NotNull String key, final @NotNull Checker value) {
        if (!this.hasKey(key))
            super.add(key, value);
    }

}
