package com.cat.server.moubieshop.shop;

import com.cat.server.api.result.BuyResult;
import com.cat.server.api.shop.Shop;
import com.cat.server.api.shop.checker.Checker;
import com.cat.server.api.shop.checker.CheckerManager;
import com.cat.server.moubieshop.operates.ShopOperate;
import com.cat.server.moubieshop.shop.checker.CheckerManagerImp;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * 代表一個商店
 * @author MouBieCat
 */
public final class ShopObject
        implements Shop {

    // 商店管理器
    @NotNull
    private final ShopOperate operate;

    // 商店名稱
    @NotNull
    private final String name;

    // 商品項目
    @NotNull
    private ItemStack giveItem;

    // 購買商品所需的條件(Minecraft)
    @NotNull
    private final MinecraftDemandContent minecraftDemandContent = new MinecraftDemandContent();

    // 購買商品所需的條件(Plugin)
    @NotNull
    private final PluginDemandContent pluginDemandContent = new PluginDemandContent();

    // 購買檢查
    @NotNull
    private final CheckerManager checkerManager = new CheckerManagerImp();

    /**
     * 商店名稱 (檔案名稱)
     * @param shopName 商店名稱
     */
    public ShopObject(final @NotNull String shopName, final @NotNull ShopOperate operate) {
        // 商店操作類
        this.operate = operate;

        // 商店基本訊息加載
        this.name = shopName;
        this.giveItem = operate.getShopGiveItem(shopName);

        // 購買條件 (Minecraft)
        this.minecraftDemandContent.setExp(operate.getShopMinecraftBuyExp(shopName));
        this.minecraftDemandContent.setItems(operate.getShopMinecraftBuyItems(shopName));

        // 購買條件 (Plugin)
        this.pluginDemandContent.setPlayerPoints(operate.getShopPluginBuyPlayerPoints(shopName));
        this.pluginDemandContent.setVault(operate.getShopPluginVaultMoney(shopName));
    }

    /**
     * 獲取商店名稱
     * @return 名稱
     */
    @NotNull
    public String getName() {
        return this.name;
    }

    /**
     * 獲取購買成功時給予的物品
     * @return 物品項目
     */
    @NotNull
    public ItemStack getGiveItem() {
        return this.giveItem;
    }

    /**
     * 設定購買成功時給予的物品
     * @param item 物品項目
     */
    public void setGiveItem(final @NotNull ItemStack item) {
        this.operate.setShopGiveItem(this.name, item);
        this.giveItem = item;
    }

    /**
     * 獲取購買時所需的經驗值
     * @return 經驗值
     */
    public int getBuyExp() {
        return this.minecraftDemandContent.getExp();
    }

    /**
     * 設定購買時所需的經驗值
     * @param exp 經驗值
     */
    public void setBuyExp(final int exp) {
        this.operate.setShopMinecraftBuyExp(this.name, exp);
        this.minecraftDemandContent.setExp(exp);
    }

    /**
     * 獲取所有購買時所需的物品
     * @return 物品項目(s)
     */
    @NotNull
    public Map<String, ItemStack> getBuyItems() {
        return this.minecraftDemandContent.getItems();
    }

    /**
     * 添加一個購買時所需的物品
     * @param key 識別碼
     * @param item 物品項目
     */
    public void setBuyItem(final @NotNull String key, final @Nullable ItemStack item) {
        if (item != null) {
            if (this.operate.addShopMinecraftBuyItem(this.name, key, item))
                this.minecraftDemandContent.getItems().put(key, item);

            return;
        }

        this.operate.removeShopMinecraftBuyItem(this.name, key);
        this.minecraftDemandContent.getItems().remove(key);
    }

    /**
     * 獲取購買時所需的 PlayerPoints 點數
     * @return 點數
     */
    public int getBuyPlayerPoints() {
        return this.pluginDemandContent.getPlayerPoints();
    }

    /**
     * 設定購買時所需的 PlayerPoints 點數
     * @param point 點數
     */
    public void setBuyPlayerPoints(final int point) {
        this.operate.setShopPluginBuyPlayerPoints(this.name, point);
        this.pluginDemandContent.setPlayerPoints(point);
    }

    /**
     * 獲取購買時所需的 Vault 金錢
     * @return 金錢
     */
    public double getBuyVault() {
        return this.pluginDemandContent.getVault();
    }

    /**
     * 設置購買時所需的 Vault 金錢
     * @param money 金錢
     */
    public void setBuyVault(final double money) {
        this.operate.setShopPluginVaultMoney(this.name, money);
        this.pluginDemandContent.setVault(money);
    }

    /**
     * 獲取購買檢查器
     * @return 檢查器
     */
    @NotNull
    public CheckerManager getBuyCheckers() {
        return this.checkerManager;
    }

    /**
     * 購買該物品
     * @param player 玩家
     * @param noChecker 是否不用經過購買檢查
     */
    @NotNull
    public BuyResult buy(final @NotNull Player player, final boolean noChecker) {
        if (noChecker) {
            player.getInventory().addItem(this.giveItem);
            return BuyResult.BUY_SUCCESS_NO_CHECKER;
        }

        if (this.buyCheck(player)) {
            player.getInventory().addItem(this.giveItem);
            return BuyResult.BUY_SUCCESS;
        }

        return BuyResult.BUY_ERROR;
    }

    /**
     * 購買檢查
     * @param player 玩家
     * @return 是否可以購買
     */
    private boolean buyCheck(final @NotNull Player player) {
        for (final Checker checker : this.checkerManager.getValues()) {
            if (!checker.check(this, player))
                return false;
        }

        return true;
    }

}
