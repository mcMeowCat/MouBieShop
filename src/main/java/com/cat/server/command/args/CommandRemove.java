package com.cat.server.command.args;

import com.cat.server.MouBieCat;
import com.cat.server.manager.StoreManager;
import com.cat.server.result.RemoveResult;
import com.cat.server.result.Result;
import com.cat.server.shop.Shop;
import com.cat.server.shop.Store;
import com.moubieapi.api.commands.SenderType;
import com.moubieapi.moubieapi.commands.SubcommandAbstract;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用於刪除店鋪或是商店的指令
 * @author MouBieCat
 */
public final class CommandRemove
        extends SubcommandAbstract {

    /**
     * 建構子
     * @param name        子指令名
     */
    public CommandRemove(final @NotNull String name) {
        super(name, new Permission("MouBieShop.remove"), SenderType.PLAYER_SENDER, "用於刪除店鋪或是商店的指令。");
    }

    /**
     * 運行該節點指令
     * @param sender 發送者
     * @param args   參數
     * @return 是否成功運行
     */
    public boolean onCmd(final @NotNull CommandSender sender, final @NotNull String[] args) {
        // 檢查最少參數
        if (args.length >= 2) {
            @Nullable Result result = null;

            // 刪除店鋪
            if (args.length == 2)
                result = this.removeStore(args[1]);

            // 刪除商店
            if (args.length == 3)
                result = this.removeShopInStore(args[1], args[2]);

            if (result != null)
                sender.sendMessage(MouBieCat.PLUGIN_TITLE + result.getMessage());

            return true;
        }

        sender.sendMessage(MouBieCat.PLUGIN_TITLE + "§c很抱歉，您所輸入的參數不足，請參閱插件幫助訊息。");
        return false;
    }

    /**
     * 運行該節點指令幫助列表
     * @param sender 發送者
     * @param args   參數
     * @return 節點指令幫助列表
     */
    @NotNull
    public List<String> onTab(final @NotNull CommandSender sender, final @NotNull String[] args) {
        final List<String> list = new ArrayList<>();

        // 顯示所有店鋪名稱
        if (args.length == 2) {
            final Collection<Store> values = MouBieCat.getInstance().getStoreManager().getValues();
            list.addAll(values.stream().map(Store::getShoreName).toList());
        }

        // 顯示所有店鋪中的商店
        if (args.length == 3) {
            final @Nullable Store manager = MouBieCat.getInstance().getStoreManager().get(args[1]);
            if (manager != null)
                list.addAll(manager.getValues().stream().map(Shop::getName).toList());
        }

        return list;
    }

    /**
     * 刪除一個店鋪
     * @param storeName 店鋪名稱
     * @return 刪除結果
     */
    @NotNull
    private RemoveResult removeStore(final @NotNull String storeName) {
        final StoreManager storeManager = MouBieCat.getInstance().getStoreManager();

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
    private RemoveResult removeShopInStore(final @NotNull String storeName, final @NotNull String shopName) {
        final StoreManager storeManager = MouBieCat.getInstance().getStoreManager();

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

}
