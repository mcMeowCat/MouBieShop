package com.cat.server.commands.args;

import com.cat.server.MouBieCat;
import com.cat.server.commands.args.attributes.AttributesTitleEditor;
import com.cat.server.commands.args.attributes.PlayerEditorCache;
import com.cat.server.shop.Shop;
import com.moubieapi.api.commands.CommandNode;
import com.moubieapi.api.commands.SenderType;
import com.moubieapi.moubieapi.commands.CommandNodeAbstract;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 編輯商店屬性指令
 * @author MouBieCat
 */
public final class CommandAttributes
        extends CommandNodeAbstract {

    /**
     * 建構子
     * @param nodeId      當前節點列數
     * @param nodeName    節點名稱
     */
    public CommandAttributes(final int nodeId, final @NotNull String nodeName) {
        super(nodeId, nodeName, "MouBieShop.attributes", SenderType.PLAYER_SENDER, "用於編輯商店屬性的指令參數。", 2);

        /* Register Command Nodes */
        this.nextNodes.add("title", new AttributesTitleEditor(2, "title"));
    }

    /**
     * 運行該節點指令
     * @param sender 發送者
     * @param args   參數
     * @return 是否成功運行
     */
    public boolean onCommand(final @NotNull CommandSender sender, final @NotNull String[] args) {
        final Player player = (Player) sender;

        final @Nullable Shop shop = PlayerEditorCache.get(player).shop;

        if (shop == null) {
            sender.sendMessage(MouBieCat.PLUGIN_TITLE + "§f您目前沒有正在編輯的商店，請使用 §6/MouBieShop editor <商店名> §f來編輯一個商店。");
            return false;
        }

        sender.sendMessage(MouBieCat.PLUGIN_TITLE + "§f您需要添加屬性來編輯該商店，目前正在編輯 §6" + shop.getName() + " §f商店。");
        return true;
    }

    /**
     * 運行該節點指令幫助列表
     * @param sender 發送者
     * @param args   參數
     * @return 節點指令幫助列表
     */
    @NotNull
    public List<String> onTab(final @NotNull CommandSender sender, final @NotNull String[] args) {
        final ArrayList<String> strings = new ArrayList<>();

        for (final CommandNode node : this.nextNodes.getValues())
            strings.add(node.getNodeName());

        strings.add("?");
        return strings;
    }

}
