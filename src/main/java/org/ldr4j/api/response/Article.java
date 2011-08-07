package org.ldr4j.api.response;

/**
 * livedoor Reader API が返却する記事情報
 *
 * @author sinsengumi
 * @version $Revision$
 */
public class Article {

    /** 配信広告 */
    private Ads[] ads;

    /** フィードID */
    private long subscribeId;

    /** チャンネル */
    private Channel channel;

    /** アイテム */
    private Item[] items;

    /**
     * 配信広告を取得します。
     *
     * @return 配信広告
     */
    public Ads[] getAds() {
        return ads;
    }

    /**
     * 配信広告を設定します。
     *
     * @param ads 配信広告
     */
    public void setAds(Ads[] ads) {
        this.ads = ads;
    }

    /**
     * フィードIDを取得します。
     *
     * @return フィードID
     */
    public long getSubscribeId() {
        return subscribeId;
    }

    /**
     * フィードIDを設定します。
     *
     * @param subscribeId フィードID
     */
    public void setSubscribeId(long subscribeId) {
        this.subscribeId = subscribeId;
    }

    /**
     * チャンネルを取得します。
     *
     * @return チャンネル
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * チャンネルを設定します。
     *
     * @param channel チャンネル
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    /**
     * 記事を取得します。
     *
     * @return 記事
     */
    public Item[] getItems() {
        return items;
    }

    /**
     * 記事を設定します。
     *
     * @param items 記事
     */
    public void setItems(Item[] items) {
        this.items = items;
    }
}
