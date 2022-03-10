package com.cat.server.loader;

import com.cat.server.MouBieCat;
import com.cat.server.shop.Store;
import com.moubieapi.api.manager.Manager;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
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
    public int loadStores(final @NotNull Manager<String, Store> manager) {
        final List<File> storeFiles = this.getStoreFiles();

        int count = 0;
        for (final File file : storeFiles) {
            final String storeName = file.getName().replace(".yml", "");
            manager.add(storeName, new Store(storeName));
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

        // 判斷是否為 yaml 檔案
        final List<File> yamlFiles = new ArrayList<>();
        for (final File file : files)
            if (this.isYamlFile(file))
                yamlFiles.add(file);

        return yamlFiles;
    }

    /**
     * 判斷是否為 Yaml 檔案
     * @param file 檔案
     * @return 是否是Yaml檔案
     */
    private boolean isYamlFile(final @NotNull File file) {
        final String fileName = file.getName();

        try {
            return file.exists() &&
                    file.isFile() &&
                    fileName.substring(
                            fileName.lastIndexOf(".")).equalsIgnoreCase(".yml");

        } catch (final Exception ignored) { }

        return false;
    }

}
