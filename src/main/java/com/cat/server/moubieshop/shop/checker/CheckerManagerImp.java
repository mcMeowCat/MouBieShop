package com.cat.server.moubieshop.shop.checker;

import com.cat.server.api.shop.checker.Checker;
import com.cat.server.api.shop.checker.CheckerManager;
import com.moubiecat.core.manager.ManagerAbstract;
import org.jetbrains.annotations.NotNull;

/**
 * 購買檢查器管理器
 * @author MouBieCat
 */
public final class CheckerManagerImp
        extends ManagerAbstract<String, Checker>
        implements CheckerManager {

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
