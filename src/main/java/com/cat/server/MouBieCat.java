package com.cat.server;

import com.cat.server.command.CommandMain;
import com.cat.server.moubieshop.loader.StoreLoader;
import com.cat.server.moubieshop.StoreManagerImp;
import com.cat.server.api.MouBieShop;
import com.cat.server.api.StoreManager;
import com.moubiecat.api.plugin.PluginRegister;
import com.moubiecat.core.plugin.MouBiePluginBase;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 代表該插件的主要類
 * @author MouBieCat
 */
public final class MouBieCat
        extends MouBiePluginBase {

    public static final String PLUGIN_TITLE = "§7[§fMouBie§6Shop§7] ";

    // 商店管理器
    @NotNull
    private final StoreManager manager = new StoreManagerImp();


    @PluginRegister(name = "掛勾API", type = PluginRegister.ActionType.ACTION_LOAD, priority = PluginRegister.ActionPriority.HIGHEST)
    public void onLoadAPI() {
        MouBieShop.setAPI(this);
    }


    @PluginRegister(name = "加載所有商店", type = PluginRegister.ActionType.ACTION_ENABLE, priority = PluginRegister.ActionPriority.HIGHEST)
    public void onEnableLoadStores() {
        final int count = new StoreLoader("shops").loadStores(this.manager);
        this.getDebugger().info("§a成功加載了 §6" + count + " §a個店鋪。");
    }


    @PluginRegister(name = "註冊插件指令", type = PluginRegister.ActionType.ACTION_ENABLE)
    public void onEnableCommands() {
        final @Nullable PluginCommand mouBieShop = this.getCommand("MouBieShop");
        if (mouBieShop != null)
            mouBieShop.setExecutor(new CommandMain());
    }


    @PluginRegister(name = "重讀所有商店", type = PluginRegister.ActionType.ACTION_RELOAD)
    public void onReloadStores() {
        this.manager.clear();
        final int count = new StoreLoader("shops").loadStores(this.manager);
        this.getDebugger().info("§e成功重載了 §6" + count + " §e個商店。");
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
     * 獲取商店店鋪管理器
     * @return 管理器
     */
    @NotNull
    public StoreManager getStoreManager() {
        return this.manager;
    }

}
