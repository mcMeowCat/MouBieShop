package com.cat.server;

import com.cat.server.commands.CommandMain;
import com.cat.server.loader.ShopLoader;
import com.cat.server.manager.ShopManager;
import com.moubieapi.api.plugin.PluginRegister;
import com.moubieapi.moubieapi.plugin.MouBiePluginBase;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * 代表該插件的主要類
 * @author MouBieCat
 */
public final class MouBieCat
        extends MouBiePluginBase {

    // 插件標題
    public static final String PLUGIN_TITLE = "§7[§fMouBie§6Shop§7] §r";

    // 商店管理器
    @NotNull
    private final ShopManager shopManager = new ShopManager();


    @PluginRegister(name = "加載所有商店", type = PluginRegister.ActionType.ACTION_ENABLE, priority = PluginRegister.ActionPriority.HIGHEST)
    public void loadShops() {
        final int count = new ShopLoader("Shops/").loadShops(this.shopManager);
        this.getDebugger().info("§a成功加載了 §6" + count + " §a個商店。");
    }


    @PluginRegister(name = "註冊插件指令", type = PluginRegister.ActionType.ACTION_ENABLE)
    public void loadCommands() {
        final PluginCommand mouBieShop = this.getCommand("MouBieShop");
        if (mouBieShop != null)
            mouBieShop.setExecutor(new CommandMain(mouBieShop));
    }


    /**
     * 獲取插件本身
     * @return 插件本身
     */
    @NotNull
    public static MouBieCat getInstance() {
        return JavaPlugin.getPlugin(MouBieCat.class);
    }

    /**
     * 獲取商店管理器
     * @return 管理器
     */
    @NotNull
    public ShopManager getShopManager() {
        return this.shopManager;
    }

}
