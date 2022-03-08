package com.cat.server.commands.args.attributes;

import com.cat.server.shop.Shop;
import com.moubieapi.moubieapi.manager.ManagerAbstract;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public final class PlayerEditorCache {

    @NotNull
    private static final EditorCacheManager CACHE_MANAGER = new EditorCacheManager();

    // 編輯者
    @NotNull
    public Player player;

    // 正在編輯的商店
    @Nullable
    public Shop shop;

    /**
     * 建構子
     * @param player 編輯者
     */
    private PlayerEditorCache(final @NotNull Player player) {
        this.player = player;
    }

    /**
     * 從塊取中添加玩家紀錄
     * @param player 玩家
     */
    public static void add(final @NotNull Player player) {
        PlayerEditorCache.CACHE_MANAGER.add(player.getUniqueId(), new PlayerEditorCache(player));
    }

    /**
     * 從塊取中刪除玩家紀錄
     * @param player 玩家
     */
    public static void remove(final @NotNull Player player) {
        PlayerEditorCache.CACHE_MANAGER.remove(player.getUniqueId());
    }

    /**
     * 從塊取中獲取玩家紀錄
     * @param player 玩家
     */
    @NotNull
    public static PlayerEditorCache get(final @NotNull Player player) {
        final @Nullable PlayerEditorCache editorCache = PlayerEditorCache.CACHE_MANAGER.get(player.getUniqueId());
        if (editorCache != null)
            return editorCache;

        else
            PlayerEditorCache.add(player);

        return PlayerEditorCache.get(player);
    }

    /**
     * 代表玩家編輯商店快取紀錄
     * @author MouBieCat
     */
    private final static class EditorCacheManager
            extends ManagerAbstract<UUID, PlayerEditorCache> {

        /**
         * 從塊取中添加玩家紀錄
         * @param key 玩家
         * @param value 值
         */
        @Override
        public void add(@NotNull UUID key, @NotNull PlayerEditorCache value) {
            if (!this.hasKey(key))
                super.add(key, value);
        }
    }

}
