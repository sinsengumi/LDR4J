package org.ldr4j.api.response;

/**
 * livedoor Reader API が返却するフィードのチャンネル情報
 *
 * @author sinsengumi
 * @version $Revision$
 */
public class Channel {

    /** アイコン */
    private String icon;

    /** サイトURL */
    private String link;

    /** 概要 */
    private String description;

    /** ロゴ画像 */
    private String image;

    /** タイトル */
    private String title;

    /** フィードURL */
    private String feedlink;

    /** 購読者数 */
    private String subscribersCount;

    /** 有効期限 */
    private long expires;

    /**
     * アイコンを取得します。
     *
     * @return アイコン
     */
    public String getIcon() {
        return icon;
    }

    /**
     * アイコンを設定します。
     *
     * @param icon アイコン
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * サイトURLを取得します。
     *
     * @return サイトURL
     */
    public String getLink() {
        return link;
    }

    /**
     * サイトURLを設定します。
     *
     * @param link サイトURL
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 概要を取得します。
     *
     * @return 概要
     */
    public String getDescription() {
        return description;
    }

    /**
     * 概要を設定します。
     *
     * @param description 概要
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ロゴ画像を取得します。
     *
     * @return ロゴ画像
     */
    public String getImage() {
        return image;
    }

    /**
     * ロゴ画像を設定します。
     *
     * @param image ロゴ画像
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * タイトルを取得します。
     *
     * @return タイトル
     */
    public String getTitle() {
        return title;
    }

    /**
     * タイトルを設定します。
     *
     * @param title タイトル
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * フィードURLを取得します。
     *
     * @return フィードURL
     */
    public String getFeedlink() {
        return feedlink;
    }

    /**
     * フィードURLを設定します。
     *
     * @param feedlink フィードURL
     */
    public void setFeedlink(String feedlink) {
        this.feedlink = feedlink;
    }

    /**
     * 購読者数を取得します。
     *
     * @return 購読者数
     */
    public String getSubscribersCount() {
        return subscribersCount;
    }

    /**
     * 購読者数を設定します。
     *
     * @param subscribersCount 購読者数
     */
    public void setSubscribersCount(String subscribersCount) {
        this.subscribersCount = subscribersCount;
    }

    /**
     * 有効期限を取得します。
     *
     * @return 有効期限
     */
    public long getExpires() {
        return expires;
    }

    /**
     * 有効期限を設定します。
     *
     * @param expires 有効期限
     */
    public void setExpires(long expires) {
        this.expires = expires * 10 * 10 * 10;
    }
}
