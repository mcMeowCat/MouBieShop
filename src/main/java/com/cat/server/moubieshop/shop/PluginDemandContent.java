package com.cat.server.moubieshop.shop;

/**
 * 代表購買商品的需求內容物(Plugin)
 * @author MouBieCat
 */
public final class PluginDemandContent {

    // PlayerPoints 插件
    private int playerPoints = 0;

    // Vault
    private double vault = 0.0D;

    /**
     * 獲取購買所需的玩家點數(PlayerPoints)
     * @return 點數
     */
    public int getPlayerPoints() {
        return this.playerPoints;
    }

    /**
     * 設定購買所需的玩家點數(PlayerPoints)
     * @param playerPoints  點數
     */
    public void setPlayerPoints(final int playerPoints) {
        this.playerPoints = playerPoints;
    }

    /**
     * 獲取購買所需的玩家金錢(Vault)
     * @return 金錢
     */
    public double getVault() {
        return this.vault;
    }

    /**
     * 設定購買所需的玩家金錢(Vault)
     * @param vault 金錢
     */
    public void setVault(final double vault) {
        this.vault = vault;
    }

}
