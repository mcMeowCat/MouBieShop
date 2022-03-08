package com.cat.server.loader;

import com.cat.server.MouBieCat;
import com.cat.server.shop.Shop;
import com.moubieapi.api.manager.Manager;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 代表所有商店加載器
 * @author MouBieCat
 */
public final class ShopLoader {

    // 檔案路徑
    @NotNull
    private final String filePath;

    /**
     * 建構子
     * @param path 檔案路徑
     */
    public ShopLoader(final @NotNull String path) {
        this.filePath = path;
    }

    /**
     * 加載所有商店
     * @param shopManager 管理器
     */
    public int loadShops(final @NotNull Manager<String, Shop> shopManager) {
        final List<File> shopFiles = this.getShopFiles();

        for (final File file : shopFiles) {
            final String shopName = file.getName().split("\\.")[0];
            shopManager.add(shopName, new Shop(shopName));
        }

        return shopFiles.size();
    }

    /**
     * 獲取所有商店檔案
     * @return 商店檔案集合
     */
    @NotNull
    private List<File> getShopFiles() {
        // 檔案路徑物件
        final File pathObj = new File(MouBieCat.getInstance().getDataFolder(), this.filePath);
        if (!pathObj.exists())
            return new ArrayList<>();

        // 獲取所有檔案
        final File[] files = pathObj.listFiles();
        if (files == null)
            return new ArrayList<>();

        return Arrays.stream(files).toList();
    }

    /**
     * 獲取商店檔案路徑
     * @return 路徑
     */
    @NotNull
    public String getFilePath() {
        return this.filePath;
    }

}
