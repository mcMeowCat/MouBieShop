package com.cat.server.result;

import org.jetbrains.annotations.NotNull;

/**
 * 代表結果
 * @author MouBieCat
 */
public interface Result {

    /**
     * 獲取結果ID
     * @return ID
     */
    int getId();

    /**
     * 獲取果訊息
     * @return 訊息
     */
    @NotNull String getMessage();

}
