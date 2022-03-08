package com.cat.server.commands.args.attributes;

import com.cat.server.MouBieCat;
import com.cat.server.shop.Shop;
import com.moubieapi.api.commands.SenderType;
import com.moubieapi.moubieapi.commands.CommandNodeAbstract;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 用於編輯商店標題屬性指令
 * @author MouBieCat
 */
public final class AttributesTitleEditor
        extends CommandNodeAbstract {

    /**
     * 建構子
     * @param nodeId      當前節點列數
     * @param nodeName    節點名稱
     */
    public AttributesTitleEditor(final int nodeId, final @NotNull String nodeName) {
        super(nodeId, nodeName, SenderType.PLAYER_SENDER, "用於編輯商店標題屬性。", 3);
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
        if (shop != null) {
            shop.setShopTitle(args[2]);
            sender.sendMessage(MouBieCat.PLUGIN_TITLE + "§f您成功編輯了 §6" + shop.getName() + " §f商店的§6標題屬性§f。");
        }

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
        return new ArrayList<>();
    }

}
