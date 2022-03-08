package com.cat.server.loader;

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

    // 商店部分讀取物件
    @NotNull
    public final ShopSectionReader READER = new ShopSectionReader(this);

    // 商店部分寫入物件
    @NotNull
    public final ShopSectionWriter WRITER = new ShopSectionWriter(this);

    /**
     * 建構子
     * @param name 商店檔案名稱
     */
    public ShopOperate(final @NotNull String name) {
        super(MouBieCat.getInstance(), "shops" + File.separator, name, true);
    }

}
