package com.cat.server.command.args;

import com.cat.server.command.attributes.Attributes;
import com.cat.server.MouBieCat;
import com.cat.server.api.MouBieShop;
import com.cat.server.api.shop.Store;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用於編輯店鋪屬性的指令
 * @author MouBieCat
 */
public final class CommandEditStore
        extends CommandEditBase {

    /**
     * 建構子
     * @param name        子指令名
     */
    public CommandEditStore(final @NotNull String name) {
        super(name, "用於編輯店鋪屬性的指令。");
    }

    /**
     * 運行該節點指令
     * @param sender 發送者
     * @param args   參數
     * @return 是否成功運行
     */
    public boolean onCmd(final @NotNull CommandSender sender, final @NotNull String[] args) {

        if (args.length >= 3) {
            final @Nullable Store store = MouBieShop.getStore(args[1]);
            final @Nullable Attributes attributes = Attributes.getCommandAttribute(args[2]);

            // 基本檢查
            if (store == null || attributes == null)
                return false;

            // 是否成功編輯
            boolean isSuccessEdit = false;

            switch (attributes) {
                case STORE_TITLE_ATTRIBUTE -> {
                    if (args.length == 4) {
                        store.setStoreTitle(args[3]);
                        isSuccessEdit = true;
                    }
                }

                default -> sender.sendMessage("§c很抱歉，目前沒有這個編輯屬性。");
            }

            if (isSuccessEdit)
                sender.sendMessage(MouBieCat.PLUGIN_TITLE + "§f您成功編輯了 §6" + attributes.getName() + " §f屬性。");

            return isSuccessEdit;
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
    @Override
    @NotNull
    public List<String> onTab(final @NotNull CommandSender sender, final @NotNull String[] args) {
        final List<String> list = new ArrayList<>();

        // 顯示所有店鋪名稱
        if (args.length == 2) {
            final Collection<Store> values = MouBieShop.getStoreManager().getValues();
            list.addAll(values.stream().map(Store::getShoreName).toList());
        }

        // 顯示所有可編輯屬性
        if (args.length == 3) {
            for (Attributes attributes : Attributes.values()) {
                if (attributes.checkClass(Store.class))
                    list.add(attributes.getCommand());
            }
        }

        return list;
    }
}
