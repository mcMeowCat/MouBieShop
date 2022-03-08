package com.cat.server.commands.args;

import com.cat.server.MouBieCat;
import com.cat.server.shop.Shop;
import com.moubieapi.api.commands.SenderType;
import com.moubieapi.moubieapi.commands.CommandNodeAbstract;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 創建商店指令
 * @author MouBieCat
 */
public final class CommandCreate
        extends CommandNodeAbstract {

    /**
     * 建構子
     * @param nodeId      當前節點列數
     * @param nodeName    節點名稱
     */
    public CommandCreate(int nodeId, @NotNull String nodeName) {
        super(nodeId, nodeName, "MouBieShop.create", SenderType.PLAYER_SENDER, "用於創建商店指令參數。", 2);
    }

    /**
     * 運行該節點指令
     * @param sender 發送者
     * @param args   參數
     * @return 是否成功運行
     */
    public boolean onCommand(final @NotNull CommandSender sender, final @NotNull String[] args) {
        final Shop shop = MouBieCat.getInstance().getShopManager().get(args[1]);
        if (shop != null)
            sender.sendMessage(MouBieCat.PLUGIN_TITLE + "§c很抱歉，該商店名稱已經存在！");

        else {
            MouBieCat.getInstance().getShopManager().add(args[1], new Shop(args[1]));
            sender.sendMessage(MouBieCat.PLUGIN_TITLE + "§f完成，商店成功被創建！");
            return true;
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
