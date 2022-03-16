package com.cat.server.api;

import com.cat.server.MouBieCat;
import com.cat.server.api.result.BuyResult;
import com.cat.server.api.result.CreateResult;
import com.cat.server.api.result.RemoveResult;
import com.cat.server.api.shop.Shop;
import com.cat.server.api.shop.Store;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 有關該插件的一些實用函數
 * @author MouBieCat
 */
public final class MouBieShop {

    // API核心
    private static MouBieCat API;

    // 是否已經掛勾APU
    private static boolean HOOK = false;

    /**
     * 掛勾插件API
     *
     * 該函數請不要隨意調用，以及變動或是該函數內容
     * @param plugin 插劍
     */
    public static void setAPI(@NotNull MouBieCat plugin) {
        if (!MouBieShop.HOOK) {
            MouBieShop.API = plugin;
            MouBieShop.HOOK = true;
        }
    }

    /**
     * 獲取逤有的店鋪管理器
     * @return 管理器
     */
    @NotNull
    public static StoreManager getStoreManager() {
        return MouBieShop.API.getStoreManager();
    }

    /**
     * 獲取指定的店鋪
     * @param storeName 店鋪名稱
     * @return 店鋪
     */
    @Nullable
    public static Store getStore(@NotNull String storeName) {
        return MouBieShop.getStoreManager().get(storeName);
    }

    /**
     * 獲取指定商店
     * @param storeName 店鋪名稱
     * @param shopName 商店名稱
     * @return 商店
     */
    @Nullable
    public static Shop getShop(@NotNull String storeName, @NotNull String shopName) {
        final @Nullable Store store = MouBieShop.getStore(storeName);
        if (store != null)
            return store.get(shopName);

        return null;
    }


    /**
     * 創建店鋪
     * @param storeName 店鋪名稱
     * @return 創建結果
     */
    @NotNull
    public static CreateResult createStore(@NotNull String storeName) {
        final StoreManager storeManager = MouBieShop.getStoreManager();

        // 是否已經擁有該商店
        if (!storeManager.hasKey(storeName)) {
            storeManager.add(storeName, null);

            // 成功創建
            return CreateResult.CREATE_STORE_SUCCESS;
        }

        // 名稱重複
        return CreateResult.CREATE_STORE_ERROR_NAME;
    }

    /**
     * 創建一個商店在某個店鋪中
     * @param store 店鋪名
     * @param shop 商店名
     * @return 創建結果
     */
    @NotNull
    public static CreateResult createShopInStore(@NotNull String store, @NotNull String shop) {
        final StoreManager storeManager = MouBieShop.getStoreManager();
        final @Nullable Store shopManager = storeManager.get(store);

        if (shopManager != null) {
            if (!shopManager.hasKey(shop)) {
                shopManager.add(shop, null);

                return CreateResult.CREATE_SHOP_SUCCESS;
            }

            // 商店名稱重複
            return CreateResult.CREATE_SHOP_ERROR_NAME;
        }

        // 店鋪不存在
        return CreateResult.CREATE_SHOP_ERROR_SHORE;
    }

    /**
     * 刪除一個店鋪
     * @param storeName 店鋪名稱
     * @return 刪除結果
     */
    @NotNull
    public static RemoveResult removeStore(@NotNull String storeName) {
        final StoreManager storeManager = MouBieShop.getStoreManager();

        if (storeManager.hasKey(storeName)) {
            storeManager.remove(storeName);

            return RemoveResult.REMOVE_STORE_SUCCESS;
        }

        return RemoveResult.REMOVE_STORE_ERROR_NAME;
    }

    /**
     * 刪除一個店鋪
     * @param storeName 店鋪名稱
     * @param shopName 商店名稱
     * @return 刪除結果
     */
    @NotNull
    public static RemoveResult removeShopInStore(@NotNull String storeName, @NotNull String shopName) {
        final StoreManager storeManager = MouBieShop.getStoreManager();

        final @Nullable Store store = storeManager.get(storeName);
        if (store != null) {
            if (store.hasKey(shopName)) {
                store.remove(shopName);
                return RemoveResult.REMOVE_SHOP_SUCCESS;
            }

            return RemoveResult.REMOVE_SHOP_ERROR_NAME;
        }

        return RemoveResult.REMOVE_STORE_ERROR_NAME;
    }

    /**
     * 購買物品
     * @param store 店鋪名稱
     * @param shop 商店名稱
     * @param player 購買者
     * @param noChecker 是否繞過購買檢測
     * @return 是否成功購買
     */
    @NotNull
    public static BuyResult buyShop(@NotNull String store, @NotNull String shop, @NotNull Player player, boolean noChecker) {
        final @Nullable Shop shopObj = MouBieShop.getShop(store, shop);
        if (shopObj != null)
            return shopObj.buy(player, noChecker);

        return BuyResult.BUY_SUCCESS_ERROR_NOT_SHOP;
    }

}
