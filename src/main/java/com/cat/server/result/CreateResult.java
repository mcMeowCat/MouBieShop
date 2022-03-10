package com.cat.server.result;

import org.jetbrains.annotations.NotNull;

/**
 * 代表店鋪或商店的創建結果
 * @author MouBieCat
 */
public enum CreateResult
        implements Result {

    CREATE_STORE_SUCCESS(1, "§f完成，您成功創建了該店鋪。"),
    CREATE_STORE_ERROR_NAME(2, "§c很抱歉，您所定義的店舖名稱已經存在，請更換一個店鋪名稱。"),

    CREATE_SHOP_SUCCESS(3, "§f完成，您成功創建了該商店在店鋪中，請設定商店資訊否則伺服器重啟時商店將被刪除。"),
    CREATE_SHOP_ERROR_SHORE(4, "§c很抱歉，您所輸入的店鋪不存在，該此無法為您創建該商店。"),
    CREATE_SHOP_ERROR_NAME(5, "§c很抱歉，您所定義的商店名稱已經存在，請更換一個商店名稱。"),

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
    CreateResult(final int id, final @NotNull String message) {
        this.id = id;
        this.message = message;
    }

    /**
     * 獲取結果ID
     * @return ID
     */
    public final int getId() {
        return id;
    }

    /**
     * 獲取果訊息
     * @return 訊息
     */
    @NotNull
    public final String getMessage() {
        return message;
    }
}
