package com.cat.server.command;

import com.cat.server.MouBieCat;
import com.cat.server.command.args.*;
import com.moubieapi.moubieapi.commands.MainCommandAbstract;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * 代表該插件的主要命令
 * @author MouBieCat
 */
public final class CommandMain
        extends MainCommandAbstract {

    /**
     * 建構子
     */
    public CommandMain() {
        this.commandManager.add("create", new CommandCreate("create"));
        this.commandManager.add("remove", new CommandRemove("remove"));
        this.commandManager.add("editstore", new CommandEditStore("editstore"));
        this.commandManager.add("editshop", new CommandEditShop("editshop"));
        this.commandManager.add("buy", new CommandBuy("buy"));
    }

    /**
     * 插劍指令
     * @param sender 發送者
     * @param args 參數
     * @return 是否成功運行
     */
    @Override
    public boolean onCmd(final @NotNull CommandSender sender, final @NotNull String[] args) {
        sender.sendMessage(MouBieCat.PLUGIN_TITLE + "§7歡迎使用沫白商店插件，如果有任何插件問題歡迎回報！");
        return true;
    }

}
