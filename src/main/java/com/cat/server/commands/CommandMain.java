package com.cat.server.commands;

import com.cat.server.MouBieCat;
import com.cat.server.commands.args.CommandCreate;
import com.moubieapi.api.commands.CommandNode;
import com.moubieapi.api.commands.SenderType;
import com.moubieapi.moubieapi.commands.CommandMainNodeAbstract;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表插件的主命令
 * @author MouBieCat
 */
public final class CommandMain
        extends CommandMainNodeAbstract {

    public CommandMain(@NotNull PluginCommand pluginCommand) {
        super(pluginCommand, SenderType.PLAYER_SENDER);

        /* Register Command Nodes */
        this.nextNodes.add("create", new CommandCreate(1, "create"));
    }

    /**
     * 運行該節點指令
     * @param sender 發送者
     * @param args   參數
     * @return 是否成功運行
     */
    @Override
    public boolean onCommand(final @NotNull CommandSender sender, final @NotNull String[] args) {
        sender.sendMessage(MouBieCat.PLUGIN_TITLE + "§7歡迎使用沫白商店插件，如果有任何插件問題歡迎回報！");
        return false;
    }

    /**
     * 運行該節點指令幫助列表
     * @param sender 發送者
     * @param args   參數
     * @return 節點指令幫助列表
     */
    @Override
    public @NotNull List<String> onTab(final @NotNull CommandSender sender, final @NotNull String[] args) {
        final ArrayList<String> strings = new ArrayList<>();

        for (final CommandNode node : this.nextNodes.getValues())
            strings.add(node.getNodeName());

        return strings;
    }

}
