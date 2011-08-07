package org.ldr4j.api.response;

/**
 * livedoor Reader API が返却するフィードメタ情報
 *
 * @author sinsengumi
 * @version $Revision$
 */
public class FeedInfo {

    /** URL */
    private String link;

    /** タイトル */
    private String title;

    /** LDR内での購読者数 */
    private int subscribersCount;

    /** フィードURL */
    private String feedlink;

    /**
     * URLを取得します。
     *
     * @return URL
     */
    public String getLink() {
        return link;
    }

    /**
     * URLを設定します。
     *
     * @param link URL
     */
    public void setLink(String link) {
        this.link = link;
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
     * LDR内での購読者数を取得します。
     *
     * @return LDR内での購読者数
     */
    public int getSubscribersCount() {
        return subscribersCount;
    }

    /**
     * LDR内での購読者数を設定します。
     *
     * @param subscribersCount LDR内での購読者数
     */
    public void setSubscribersCount(int subscribersCount) {
        this.subscribersCount = subscribersCount;
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

    @Override
    public String toString() {
        return "FeedInfo [link=" + link + ", title=" + title + ", subscribersCount="
                + subscribersCount + ", feedlink=" + feedlink + "]";
    }
}
