package com.cat.server.shop;

import com.cat.server.MouBieCat;
import com.moubieapi.moubieapi.yaml.Loader;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * 代表一個商店加載器
 * @author MouBieCat
 */
public final class ShopOperate
        extends Loader {

    private static final String FILE_PATH = "shops" + File.separator;

    // 店鋪名稱
    @NotNull
    private final String store;

    // 商店部分讀取物件
    @NotNull
    public final ShopSectionReader reader = new ShopSectionReader(this);

    // 商店部分寫入物件
    @NotNull
    public final ShopSectionWriter writer = new ShopSectionWriter(this);

    /**
     * 建構子
     * @param store 店鋪檔案名稱
     */
    public ShopOperate(final @NotNull String store) {
        super(MouBieCat.getInstance(), FILE_PATH, store, true);
        this.store = store;
    }

    /**
     * 獲取商店店鋪名稱
     * @return 店鋪名稱
     */
    @NotNull
    public String getStore() {
        return this.store;
    }

}
