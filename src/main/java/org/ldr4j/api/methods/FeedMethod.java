package org.ldr4j.api.methods;

import org.ldr4j.LDRException;
import org.ldr4j.api.response.Article;
import org.ldr4j.api.response.Feed;
import org.ldr4j.api.response.FeedInfo;

/**
 * フィード情報の操作を提供します。
 *
 * @author sinsengumi
 * @version $Revision$
 */
public interface FeedMethod {

    /**
     * 指定されたフィードURLのフィード情報を追加します。
     *
     * @param feedUrl フィードURL
     * @return 追加されたフィードID
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    long addFeed(String feedUrl) throws LDRException;

    /**
     * 指定されたフィードIDのフィード情報を削除します。
     *
     * @param subscribeId フィードID
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    void removeFeed(long subscribeId) throws LDRException;

    /**
     * フィード情報を取得します。
     *
     * @param unread 未読を取得したい場合は true、すべて取得したい場合は false
     * @return フィード情報の配列
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    Feed[] getAllFeeds(boolean unread) throws LDRException;

    /**
     * フィード情報を取得します。
     *
     * @param unread 未読を取得したい場合は true、すべて取得したい場合は false
     * @param limit 取得するフィードの上限数（例外として0以下を指定した場合、すべてを取得する）
     * @return フィード情報の配列
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    Feed[] getAllFeeds(boolean unread, int limit) throws LDRException;

    /**
     * フィード情報を取得します（サポートされません）
     *
     * @param unread 未読を取得したい場合は true、すべて取得したい場合は false
     * @param limit 取得するフィードの上限数（例外として0以下を指定した場合、すべてを取得する）
     * @param fromSubscribeId 取得を開始するフィードID（指定されたフィードIDより大きいフィードIDを取得する）
     * @return フィード情報の配列
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    Feed[] getAllFeeds(boolean unread, int limit, long fromSubscribeId) throws LDRException;

    /**
     * 記事情報を取得します。
     *
     * @param subscribeId フィードID
     * @return 記事情報
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    Article getAllArticle(long subscribeId) throws LDRException;

    /**
     * 記事情報を取得します。
     *
     * @param subscribeId フィードID
     * @param limit 取得上限数（0以下の値を入れた場合、すべてを取得する）
     * @param offset 取得オフセット
     * @return 記事情報
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    Article getAllArticle(long subscribeId, int limit, int offset) throws LDRException;

    /**
     * 指定されたフィードIDの未読記事情報を取得します。
     *
     * @param subscribeId フィードID
     * @return 記事情報
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    Article getUnreadArticle(long subscribeId) throws LDRException;

    /**
     * 指定されたフィードIDの記事情報を既読にします。
     *
     * @param subscribeId フィードID（存在しないフィードIDを指定しても例外は起きない）
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    void touchAll(long subscribeId) throws LDRException;

    /**
     * 指定されたフィードIDのフィード情報にレートを設定します。
     *
     * @param subscribeId フィードID
     * @param rate レート（0～5）
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    void setRate(long subscribeId, int rate) throws LDRException;

    /**
     * 指定されたフィードIDのフィード情報を"未分類"のフォルダーに移動します。
     *
     * @param subscribeId フィードID
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    void moveFeed(long subscribeId) throws LDRException;

    /**
     * 指定されたフィードIDのフィード情報を指定されたフォルダー名のフォルダーに移動します。
     *
     * @param subscribeId フィードID
     * @param folderName フォルダー名（存在しないフォルダー名を指定しても例外は起きない）
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    void moveFeed(long subscribeId, String folderName) throws LDRException;

    /**
     * 指定されたlivedoor ID の未読記事数を取得します。
     *
     * @param livedoorId livedoor ID
     * @return 未読記事数
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    int getNotify(String livedoorId) throws LDRException;

    /**
     * 指定されたURLからフィード情報を検索します（オートディスカバリー）
     *
     * @param url URL
     * @return フィード情報
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    FeedInfo[] discover(String url) throws LDRException;
}
