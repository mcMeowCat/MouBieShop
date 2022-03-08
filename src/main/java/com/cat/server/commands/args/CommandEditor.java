package com.cat.server.commands.args;

import com.cat.server.MouBieCat;
import com.cat.server.commands.args.attributes.PlayerEditorCache;
import com.cat.server.shop.Shop;
import com.moubieapi.api.commands.SenderType;
import com.moubieapi.moubieapi.commands.CommandNodeAbstract;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 選定編輯的商店指令
 * @author MouBieCat
 */
public final class CommandEditor
        extends CommandNodeAbstract {

    /**
     * 建構子
     * @param nodeId      當前節點列數
     * @param nodeName    節點名稱
     */
    public CommandEditor(final int nodeId, final @NotNull String nodeName) {
        super(nodeId, nodeName, "MouBieShop.editor", SenderType.PLAYER_SENDER, "用於選定編輯的商店指令參數。", 2);
    }

    /**
     * 運行該節點指令
     * @param sender 發送者
     * @param args   參數
     * @return 是否成功運行
     */
    public boolean onCommand(final @NotNull CommandSender sender, final @NotNull String[] args) {
        final Shop shop = MouBieCat.getInstance().getShopManager().get(args[1]);

        if (shop != null) {
            PlayerEditorCache.get((Player) sender).shop = shop;
            sender.sendMessage(MouBieCat.PLUGIN_TITLE + "§f您成功選定該商店作為編輯目標，目前正在編輯 §6" + shop.getName() + " §f商店。");
            return true;
        }

        sender.sendMessage(MouBieCat.PLUGIN_TITLE + "§c很抱歉，該商店名稱不存在，因此無法選擇該商店作為編輯目標。");
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
        return MouBieCat.getInstance().getShopManager().getValues()
                .stream()
                .map(Shop::getName)
                .toList();
    }

}
