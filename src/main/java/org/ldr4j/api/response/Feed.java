package org.ldr4j.api.response;

/**
 * livedoor Reader API が返却するフィード情報
 *
 * @author sinsengumi
 * @version $Revision$
 */
public class Feed {

    /** エラーコード（ユーザーなし） */
    public static final int NOTIFY_NO_USER = -1;

    /** ファビコンURL */
    private String icon;

    /** URL */
    private String link;

    /** フィードID */
    private long subscribeId;

    /** 未読記事 */
    private int unreadCount;

    /** フォルダ名 */
    private String folder;

    /** タグ */
    private String[] tags;

    /** レート */
    private int rate;

    /** 更新日時 */
    private long modifiedOn;

    /** 公開フラグ */
    private int _public;

    /** タイトル */
    private String title;

    /** 購読者数 */
    private int subscribersCount;

    /** フィードURL */
    private String feedlink;

    /**
     * ファビコンURLを取得します。
     *
     * @return ファビコンURL
     */
    public String getIcon() {
        return icon;
    }

    /**
     * ファビコンURLを設定します。
     *
     * @param icon ファビコンURL
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

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
    public void setSubscribeId(int subscribeId) {
        this.subscribeId = subscribeId;
    }

    /**
     * 未読記事を取得します。
     *
     * @return 未読記事
     */
    public int getUnreadCount() {
        return unreadCount;
    }

    /**
     * 未読記事を設定します。
     *
     * @param unreadCount 未読記事
     */
    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    /**
     * フォルダ名を取得します。
     *
     * @return フォルダ名
     */
    public String getFolder() {
        return folder;
    }

    /**
     * フォルダ名を設定します。
     *
     * @param folder フォルダ名
     */
    public void setFolder(String folder) {
        this.folder = folder;
    }

    /**
     * タグを取得します。
     *
     * @return タグ
     */
    public String[] getTags() {
        return tags;
    }

    /**
     * タグを設定します。
     *
     * @param tags タグ
     */
    public void setTags(String[] tags) {
        this.tags = tags;
    }

    /**
     * レートを取得します。
     *
     * @return レート
     */
    public int getRate() {
        return rate;
    }

    /**
     * レートを設定します。
     *
     * @param rate レート
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * 更新日時を取得します。
     *
     * @return 更新日時
     */
    public long getModifiedOn() {
        return modifiedOn;
    }

    /**
     * 更新日時を設定します。
     *
     * @param modifiedOn 更新日時
     */
    public void setModifiedOn(long modifiedOn) {
        this.modifiedOn = modifiedOn * 10 * 10 * 10;
    }

    /**
     * 公開フラグを取得します。
     *
     * @return 公開フラグ
     */
    public int get_public() {
        return _public;
    }

    /**
     * 公開フラグを設定します。
     *
     * @param _public 公開フラグ
     */
    public void set_public(int _public) {
        this._public = _public;
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
     * 購読者数を取得します。
     *
     * @return 購読者数
     */
    public int getSubscribersCount() {
        return subscribersCount;
    }

    /**
     * 購読者数を設定します。
     *
     * @param subscribersCount 購読者数
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
}
