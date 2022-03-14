package com.cat.server.command.attributes;

import com.cat.server.api.shop.Shop;
import com.cat.server.api.shop.Store;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 代表用於編輯商店或店舖屬性的列舉
 * @author MouBieCat
 */
public enum Attributes {

    // 店鋪屬性
    STORE_TITLE_ATTRIBUTE("title", "店鋪標題", Store.class),

    // 商店屬性
    SHOP_GIVE_ITEM_ATTRIBUTE("item", "商店物品", Shop.class),
    SHOP_BUY_MINECRAFT_EXP_ATTRIBUTE("minecraft:exp", "需求經驗值", Shop.class),
    SHOP_BUY_MINECRAFT_ITEMS_ATTRIBUTE("minecraft:items", "需求物品", Shop.class),
    SHOP_BUY_PLUGIN_PLAYER_POINTS_ATTRIBUTE("plugin:player_points", "需求PlayerPoints", Shop.class),
    SHOP_BUY_PLUGIN_VAULT_ATTRIBUTE("plugin:vault", "需求Vault", Shop.class),

    ;

    // 屬性指令名稱
    @NotNull
    private final String cmd;

    // 屬性名稱
    @NotNull
    private final String name;

    // 屬性類
    @NotNull
    private final Class<?> attributeClass;

    /**
     * 建構子
     * @param cmd 指令名稱
     * @param name 屬性名稱
     */
    Attributes(final @NotNull String cmd, final @NotNull String name, final @NotNull Class<?> clazz) {
        this.cmd = cmd;
        this.name = name;
        this.attributeClass = clazz;
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

    /**
     * 屬性類
     * @return 類
     */
    @NotNull
    public final Class<?> getAttributeClass() {
        return this.attributeClass;
    }

    /**
     * 檢查該物件是否為該屬性相關
     * @param clazz 類
     * @return 是否相關
     */
    public final boolean checkClass(final @NotNull Class<?> clazz) {
        return clazz.equals(this.attributeClass);
    }

    /**
     * 獲取指令的屬性
     * @param cmd 指令
     * @return 屬性
     */
    @Nullable
    public static Attributes getCommandAttribute(final @NotNull String cmd) {
        for (final Attributes attributes : Attributes.values())
            if (attributes.getCommand().equalsIgnoreCase(cmd))
                return attributes;

        return null;
    }

}
