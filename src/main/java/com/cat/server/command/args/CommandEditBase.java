package com.cat.server.command.args;

import com.cat.server.command.attributes.Attributes;
import com.moubieapi.api.commands.SenderType;
import com.moubieapi.moubieapi.commands.SubcommandAbstract;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表一個編輯指令基底類
 * @author MouBieCat
 */
public abstract class CommandEditBase
        extends SubcommandAbstract {

    // 可編輯屬性集合
    @NotNull
    protected final List<Attributes> attributes = new ArrayList<>();

    /**
     * 建構子
     * @param name        子指令名
     */
    public CommandEditBase(final @NotNull String name, final @NotNull String description) {
        super(name, new Permission("MouBieShop.edit"), SenderType.PLAYER_SENDER, description);
    }

}
