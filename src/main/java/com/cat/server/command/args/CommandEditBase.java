package com.cat.server.command.args;

import com.moubiecat.api.commands.SenderType;
import com.moubiecat.core.commands.SubcommandAbstract;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;

/**
 * 代表一個編輯指令基底類
 * @author MouBieCat
 */
public abstract class CommandEditBase
        extends SubcommandAbstract {

    /**
     * 建構子
     * @param name        子指令名
     */
    public CommandEditBase(final @NotNull String name, final @NotNull String description) {
        super(name, new Permission("MouBieShop.edit"), SenderType.PLAYER_SENDER, description);
    }

}
