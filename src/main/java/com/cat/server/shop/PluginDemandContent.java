package com.cat.server.shop;

/**
 * 代表購買商品的需求內容物(Plugin)
 * @author MouBieCat
 */
public final class PluginDemandContent {

    // PlayerPoints 插件
    private int playerPoints = 0;

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

}
