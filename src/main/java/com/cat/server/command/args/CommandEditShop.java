package com.cat.server.command.args;

import com.cat.server.MouBieCat;
import com.cat.server.api.MouBieShop;
import com.cat.server.api.shop.Shop;
import com.cat.server.api.shop.Store;
import com.cat.server.command.attributes.Attributes;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 用於編輯商店屬性的指令
 * @author MouBieCat
 */
public final class CommandEditShop
        extends CommandEditBase {

    private final List<String> ITEM_EDITOR = Arrays.asList("add", "remove", "edit");

    /**
     * 建構子
     * @param name        子指令名
     */
    public CommandEditShop(final @NotNull String name) {
        super(name, "用於編輯商店屬性的指令。");
    }

    /**
     * 運行該節點指令
     * @param sender 發送者
     * @param args   參數
     * @return 是否成功運行
     */
    public boolean onCmd(final @NotNull CommandSender sender, final @NotNull String[] args) {

        if (args.length >= 4) {
            final @Nullable Shop shop = MouBieShop.getShop(args[1], args[2]);
            final @Nullable Attributes attributes = Attributes.getCommandAttribute(args[3]);

            // 基本檢查
            if (shop == null || attributes == null)
                return false;

            // 是否成功編輯
            boolean isSuccessEdit = false;

            switch (attributes) {
                case SHOP_GIVE_ITEM_ATTRIBUTE -> {
                    shop.setGiveItem(((Player) sender).getInventory().getItemInMainHand());
                    isSuccessEdit = true;
                }

                case SHOP_BUY_MINECRAFT_EXP_ATTRIBUTE -> {
                    if (args.length == 5) {
                        try {
                            final int exp = Integer.parseInt(args[4]);
                            shop.setBuyExp(exp);
                            isSuccessEdit = true;
                        } catch (final NumberFormatException ignored) {}
                    }
                }

                case SHOP_BUY_MINECRAFT_ITEMS_ATTRIBUTE -> {
                    if (args.length == 6) {
                        if (args[4].equalsIgnoreCase(ITEM_EDITOR.get(0)))
                            shop.setBuyItem(args[5], ((Player) sender).getInventory().getItemInMainHand());

                        else if (args[4].equalsIgnoreCase(ITEM_EDITOR.get(1)))
                            shop.setBuyItem(args[5], null);

                        else if (args[4].equalsIgnoreCase(ITEM_EDITOR.get(2))) {
                            shop.setBuyItem(args[5], null);
                            shop.setBuyItem(args[5], ((Player) sender).getInventory().getItemInMainHand());
                        }

                        isSuccessEdit = true;
                    }
                }

                case SHOP_BUY_PLUGIN_PLAYER_POINTS_ATTRIBUTE -> {
                    if (args.length == 5) {
                        try {
                            final int playerPoints = Integer.parseInt(args[4]);
                            shop.setBuyPlayerPoints(playerPoints);
                            isSuccessEdit = true;
                        } catch (final NumberFormatException ignored) {}
                    }
                }

                case SHOP_BUY_PLUGIN_VAULT_ATTRIBUTE -> {
                    if (args.length == 5) {
                        try {
                            final double vault = Double.parseDouble(args[4]);
                            shop.setBuyVault(vault);
                            isSuccessEdit = true;
                        } catch (final NumberFormatException ignored) {}
                    }
                }

                default -> sender.sendMessage(MouBieCat.PLUGIN_TITLE + "§c很抱歉，目前沒有這個編輯屬性。");
            }

            if (isSuccessEdit)
                sender.sendMessage(MouBieCat.PLUGIN_TITLE + "§f您成功編輯了 §6" + attributes.getName() + " §f屬性。");

            return isSuccessEdit;
        }

        sender.sendMessage(MouBieCat.PLUGIN_TITLE + "§c很抱歉，您所輸入的參數不足，請參閱插件幫助訊息。");
        return false;
    }

    /**
     * 運行該節點指令幫助列表
     * @param sender 發送者
     * @param args   參數
     * @return 節點指令幫助列表
     */
    @Override
    @NotNull
    public List<String> onTab(final @NotNull CommandSender sender, final @NotNull String[] args) {
        final List<String> list = new ArrayList<>();

        // 顯示所有店鋪名稱
        if (args.length == 2) {
            final Collection<Store> values = MouBieShop.getStoreManager().getValues();
            list.addAll(values.stream().map(Store::getShoreName).toList());
        }

        // 顯示所有店鋪中的商店
        if (args.length == 3) {
            final @Nullable Store manager = MouBieShop.getStoreManager().get(args[1]);
            if (manager != null)
                list.addAll(manager.getValues().stream().map(Shop::getName).toList());
        }

        // 顯示所有可編輯屬性
        if (args.length == 4) {
            for (Attributes attributes : Attributes.values()) {
                if (attributes.checkClass(Shop.class))
                    list.add(attributes.getCommand());
            }
        }

        // 顯示物品操作
        if (args.length == 5) {
            if (args[3].equalsIgnoreCase(Attributes.SHOP_BUY_MINECRAFT_ITEMS_ATTRIBUTE.getCommand()))
                return this.ITEM_EDITOR;
        }

        if (args.length == 6) {
            final @Nullable Shop shop = MouBieShop.getShop(args[1], args[2]);
            if (shop != null)
                list.addAll(shop.getBuyItems().keySet());
        }

        return list;
    }

}
