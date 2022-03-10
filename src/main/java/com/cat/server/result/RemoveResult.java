package com.cat.server.result;

import org.jetbrains.annotations.NotNull;

/**
 * 代表店鋪或商店刪除的結果
 * @author MouBieCat
 */
public enum RemoveResult
        implements Result {

    REMOVE_STORE_SUCCESS(0, "§f完成，您成功刪除了該店鋪。"),
    REMOVE_STORE_ERROR_NAME(2, "§c很抱歉，您所指定的店舖名稱沒有找到，所以沒有刪除任何店鋪。"),
    REMOVE_SHOP_SUCCESS(3, "§f完成，您成功刪除了該商店於店鋪中。"),
    REMOVE_SHOP_ERROR_NAME(4, "§c很抱歉，您所指定的商店名稱沒有找到，所以沒有刪除任何商店。")
    ;

    // 結果ID
    private final int id;

    // 結果訊息
    @NotNull
    private final String message;

    /**
     * 建構子
     * @param id 獲取結果ID
     * @param message 獲取果訊息
     */
    RemoveResult(final int id, final @NotNull String message) {
        this.id = id;
        this.message = message;
    }

    /**
     * 獲取結果ID
     * @return ID
     */
    public final int getId() {
        return this.id;
    }

    /**
     * 獲取果訊息
     * @return 訊息
     */
    @NotNull
    public final String getMessage() {
        return this.message;
    }

}
