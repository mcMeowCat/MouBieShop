package com.cat.server.api.result;

import org.jetbrains.annotations.NotNull;

/**
 * 購買商店結果
 * @author MouBieCat
 */
public enum BuyResult
        implements Result {

    BUY_SUCCESS(0, "§f完成。您成功購買了該商店物品。"),
    BUY_ERROR(1, "§c失敗。您沒有達到該商店的購買需求，因此購買商品沒有成功。"),
    BUY_SUCCESS_NO_CHECKER(2, "§f完成。您成功購買了該商店物品，且為未經購買需求檢查。"),
    BUY_SUCCESS_ERROR_NOT_SHOP(3, "§c很抱歉，您所指定的商店名稱沒有找到，所以沒有購買任何商品。"),
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
    BuyResult(final int id, final @NotNull String message) {
        this.id = id;
        this.message = message;
    }

    /**
     * 獲取結果ID
     * @return ID
     */
    @Override
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
