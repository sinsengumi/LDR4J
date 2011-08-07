package org.ldr4j.api.response;

/**
 * livedoor Reader API が返却するアイテム情報
 *
 * @author sinsengumi
 * @version $Revision$
 */
public class Item {

    /** URL */
    private String link;

    /** エンクロージャ */
    private String enclosure;

    /** エンクロージャタイプ */
    private String enclosureType;

    /** 著者 */
    private String author;

    /** 本文 */
    private String body;

    /** 更新日時 */
    private long modifiedOn;

    /** 作成日時 */
    private long createdOn;

    /** カテゴリー名 */
    private String category;

    /** タイトル */
    private String title;

    /** ID */
    private String id;

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
     * エンクロージャを取得します。
     *
     * @return エンクロージャ
     */
    public String getEnclosure() {
        return enclosure;
    }

    /**
     * エンクロージャを設定します。
     *
     * @param enclosure エンクロージャ
     */
    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    /**
     * エンクロージャタイプを取得します。
     *
     * @return エンクロージャタイプ
     */
    public String getEnclosureType() {
        return enclosureType;
    }

    /**
     * エンクロージャタイプを設定します。
     *
     * @param enclosureType エンクロージャタイプ
     */
    public void setEnclosureType(String enclosureType) {
        this.enclosureType = enclosureType;
    }

    /**
     * 著者を取得します。
     *
     * @return 著者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 著者を設定します。
     *
     * @param author 著者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 本文を取得します。
     *
     * @return 本文
     */
    public String getBody() {
        return body;
    }

    /**
     * 本文を設定します。
     *
     * @param body 本文
     */
    public void setBody(String body) {
        this.body = body;
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
     * 作成日時を取得します。
     *
     * @return 作成日時
     */
    public long getCreatedOn() {
        return createdOn;
    }

    /**
     * 作成日時を設定します。
     *
     * @param createdOn 作成日時
     */
    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn * 10 * 10 * 10;
    }

    /**
     * カテゴリー名を取得します。
     *
     * @return カテゴリー名
     */
    public String getCategory() {
        return category;
    }

    /**
     * カテゴリー名を設定します。
     *
     * @param category カテゴリー名
     */
    public void setCategory(String category) {
        this.category = category;
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
     * IDを取得します。
     *
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * IDを設定します。
     *
     * @param id ID
     */
    public void setId(String id) {
        this.id = id;
    }
}
