package com.cat.server.io.operates;

import com.cat.server.io.ShopSectionReader;
import com.cat.server.io.ShopSectionWriter;
import org.jetbrains.annotations.NotNull;

/**
 * 代表一個基本的操作類
 * @author MouBieCat
 */
abstract class OperateBase {

    // 讀取物件
    @NotNull
    protected final ShopSectionReader reader;

    // 寫入物件
    @NotNull
    protected final ShopSectionWriter writer;

    /**
     * 建構子
     * @param storeName 店鋪檔案名稱
     */
    public OperateBase(final @NotNull String storeName) {
        this.reader = new ShopSectionReader(storeName);
        this.writer = new ShopSectionWriter(storeName);
    }

    /**
     * 獲取店鋪名稱
     * @return 名稱
     */
    @NotNull
    public String getStoreName() {
        return this.reader.getStoreName();
    }

}
