package com.cat.server.loader;

import com.cat.server.MouBieCat;
import com.cat.server.manager.ShopManager;
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
public final class ShopStoreLoader {

    // 檔案路徑
    @NotNull
    private final String filePath;

    /**
     * 建構子
     * @param path 檔案路徑
     */
    public ShopStoreLoader(final @NotNull String path) {
        this.filePath = path;
    }

    /**
     * 加載所有商店
     * @param manager 管理器
     */
    public int loadStores(final @NotNull Manager<String, ShopManager> manager) {
        final List<File> storeFiles = this.getStoreFiles();

        int count = 0;
        for (final File file : storeFiles) {
            final String storeName = file.getName();

            final ShopManager shopManager = new ShopManager(storeName);
            final int shopCount = new ShopLoader().loadShops(shopManager);

            // DeBug Message
            MouBieCat.getInstance().getDebugger().info("ShopStoreLoader#loadStores -> §a成功從 §6" + storeName + " §a加載了 §6" + shopCount + " §a個商店。");

            manager.add(storeName, shopManager);
            count++;
        }

        return count;
    }

    /**
     * 獲取所有商店檔案
     * @return 商店檔案集合
     */
    @NotNull
    private List<File> getStoreFiles() {
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

}
