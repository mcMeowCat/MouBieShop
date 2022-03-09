package com.cat.server.command.args;

import com.cat.server.MouBieCat;
import com.cat.server.manager.ShopManager;
import com.cat.server.manager.ShopStoreManager;
import com.cat.server.shop.Shop;
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
 * 用於創建店鋪或是商店的指令
 * @author MouBieCat
 */
public final class CommandCreate
        extends SubcommandAbstract {

    /**
     * 建構子
     * @param name        子指令名
     */
    public CommandCreate(final @NotNull String name) {
        super(name, new Permission("MouBieShop.create"), SenderType.PLAYER_SENDER, "用於創建店鋪或是商店的指令。");
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
            @Nullable CreateResult result = null;

            // 創建店鋪
            if (args.length == 2)
                result = this.createStore(args[1]);

            // 創建商店
            if (args.length == 3)
                result = this.createShopInStore(args[1], args[2]);

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
            final Collection<ShopManager> values = MouBieCat.getInstance().getShopManager().getValues();
            list.addAll(values.stream().map(ShopManager::getShoreName).toList());
        }

        // 顯示所有店鋪中的商店
        if (args.length == 3) {
            final @Nullable ShopManager manager = MouBieCat.getInstance().getShopManager().get(args[2]);
            if (manager != null)
                list.addAll(manager.getValues().stream().map(Shop::getName).toList());
        }

        return list;
    }

    /**
     * 創建店鋪
     * @param storeName 店鋪名稱
     * @return 創建結果
     */
    @NotNull
    private CreateResult createStore(final @NotNull String storeName) {
        final ShopStoreManager shopManager = MouBieCat.getInstance().getShopManager();

        // 是否已經擁有該商店
        if (!shopManager.hasKey(storeName)) {
            shopManager.add(storeName, new ShopManager(storeName));

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
    private CreateResult createShopInStore(final @NotNull String store, final @NotNull String shop) {
        final ShopStoreManager shopManager = MouBieCat.getInstance().getShopManager();
        final @Nullable ShopManager manager = shopManager.get(store);

        if (manager != null) {
            if (!manager.hasKey(shop)) {
                manager.add(shop, new Shop(manager.getOperate(), shop));

                // 成功創建
                return CreateResult.CREATE_SHOP_SUCCESS;
            }

            // 商店名稱重複
            return CreateResult.CREATE_SHOP_ERROR_NAME;
        }

        // 店鋪不存在
        return CreateResult.CREATE_SHOP_ERROR_SHORE;
    }

}
