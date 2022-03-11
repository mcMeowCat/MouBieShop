package com.cat.server.command.args;

import com.cat.server.api.MouBieShop;
import com.cat.server.api.shop.Store;
import com.cat.server.command.attributes.Attributes;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

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
        this.attributes.add(Attributes.STORE_TITLE_ATTRIBUTE);
    }

    /**
     * 運行該節點指令
     * @param sender 發送者
     * @param args   參數
     * @return 是否成功運行
     */
    public boolean onCmd(final @NotNull CommandSender sender, final @NotNull String[] args) {
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
        if (args.length == 3)
            list.addAll(this.attributes.stream().map(Attributes::getCommand).toList());

        return list;
    }
}
