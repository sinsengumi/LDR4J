package org.ldr4j.api.response;

/**
 * livedoor Reader API が返却するピン情報
 *
 * @author sinsengumi
 * @version $Revision$
 */
public class Pin {

    /** リンクURL */
    private String link;

    /** 生成日時 */
    private long createdOn;

    /** タイトル */
    private String title;

    /**
     * リンクURLを取得します。
     *
     * @return リンクURL
     */
    public String getLink() {
        return link;
    }

    /**
     * リンクURLを設定します。
     *
     * @param link リンクURL
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 生成日時を取得します。
     *
     * @return 生成日時
     */
    public long getCreatedOn() {
        return createdOn;
    }

    /**
     * 生成日時を設定します。
     *
     * @param createdOn 生成日時
     */
    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn * 10 * 10 * 10;
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

    @Override
    public String toString() {
        return "Pin [link=" + link + ", createdOn=" + createdOn + ", title=" + title + "]";
    }
}
