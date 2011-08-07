package org.ldr4j.api.response;

/**
 * livedoor Reader API が配信する広告情報
 *
 * @author sinsengumi
 * @version $Revision$
 */
public class Ads {

    /** URL */
    private String url;

    /** タイトル */
    private String title;

    /** 概要 */
    private String description;

    /**
     * URLを取得します。
     *
     * @return URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * URLを設定します。
     *
     * @param url URL
     */
    public void setUrl(String url) {
        this.url = url;
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
}
