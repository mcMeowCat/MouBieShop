package com.cat.server.manager;

import com.cat.server.shop.ShopOperate;
import com.cat.server.shop.Shop;
import com.moubieapi.moubieapi.manager.ManagerAbstract;
import org.jetbrains.annotations.NotNull;

/**
 * 代表商店管理器
 * @author MouBieCat
 */
public final class ShopManager
        extends ManagerAbstract<String, Shop> {

    // 店鋪商店操作
    @NotNull
    private final ShopOperate operate;

    /**
     * 建構子
     * @param store 店鋪名稱
     */
    public ShopManager(final @NotNull String store) {
        this.operate = new ShopOperate(store);
    }

    /**
     * 獲取店鋪商店操作
     * @return 商店操作
     */
    @NotNull
    public ShopOperate getOperate() {
        return this.operate;
    }

}
