package com.cat.server.command.attributes;

import org.jetbrains.annotations.NotNull;

/**
 * 代表用於編輯商店或店舖屬性的列舉
 * @author MouBieCat
 */
public enum Attributes {

    // 店鋪屬性
    STORE_TITLE_ATTRIBUTE("title", "店鋪標題"),

    // 商店屬性
    SHOP_GIVE_ITEM_ATTRIBUTE("item", "商店物品"),
    SHOP_BUY_MINECRAFT_EXP_ATTRIBUTE("minecraft:exp", "需求經驗值"),
    SHOP_BUY_MINECRAFT_ITEMS_ATTRIBUTE("minecraft:items", "需求物品"),
    SHOP_BUY_PLUGIN_PLAYER_POINTS_ATTRIBUTE("plugin:player_points", "需求PlayerPoints"),
    SHOP_BUY_PLUGIN_VAULT_ATTRIBUTE("plugin:vault", "需求Vault"),

    ;

    // 屬性指令名稱
    @NotNull
    private final String cmd;

    // 屬性名稱
    @NotNull
    private final String name;

    /**
     * 建構子
     * @param cmd 指令名稱
     * @param name 屬性名稱
     */
    Attributes(final @NotNull String cmd, final @NotNull String name) {
        this.cmd = cmd;
        this.name = name;
    }

    /**
     * 獲取屬性指令名稱
     * @return 指令名稱
     */
    @NotNull
    public final String getCommand() {
        return this.cmd;
    }

    /**
     * 獲取屬性名稱
     * @return 名稱
     */
    @NotNull
    public final String getName() {
        return this.name;
    }
}
