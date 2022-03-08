package com.cat.server.manager;

import com.cat.server.shop.Shop;
import com.moubieapi.MouBieCat;
import com.moubieapi.moubieapi.manager.ManagerAbstract;
import org.jetbrains.annotations.NotNull;

/**
 * 代表商店管理器
 * @author MouBieCat
 */
public final class ShopManager
        extends ManagerAbstract<String, Shop> {

    /**
     * 添加到管理器中
     * @param key 識別碼
     * @param value 數值
     */
    @Override
    public void add(final @NotNull String key, final @NotNull Shop value) {
        if (this.manager.containsKey(key))
            MouBieCat.getInstance().getDebugger().warning(MouBieCat.PLUGIN_TITLE + "§c很抱歉，已經包含了商店名稱 §6" + key + " §c因此重複名稱的商店沒有被加載。");

        else
            super.add(key, value);
    }

}
