package org.ldr4j;

/**
 * livedoor Reader API の接続URLの定数クラス。
 *
 * @author sinsengumi
 * @version $Revision$
 */
public final class ApiUrl {

    /**
     * コンストラクタ
     */
    private ApiUrl() {
    }

    /** livedoor ログインページURL */
    public static final String LIVEDOOR_LOGIN = "http://member.livedoor.com/login/index";

    /** livedoor Reader のURL */
    public static final String LDR_URI = "http://reader.livedoor.com/";

    /** livedoor Reader API のURL */
    public static final String LDR_API_URI = LDR_URI + "api/";

    /** livedoor Reader API のURL（ピン取得） */
    public static final String PIN_ALL = LDR_API_URI + "pin/all";

    /** livedoor Reader API のURL（ピン追加） */
    public static final String PIN_ADD = LDR_API_URI + "pin/add";

    /** livedoor Reader API のURL（ピン削除） */
    public static final String PIN_REMOVE = LDR_API_URI + "pin/remove";

    /** livedoor Reader API のURL（ピンクリア） */
    public static final String PIN_CLEAR = LDR_API_URI + "pin/clear";

    /** livedoor Reader API のURL（フィード登録） */
    public static final String FEED_ADD = LDR_API_URI + "feed/subscribe";

    /** livedoor Reader API のURL（未読フィード取得） */
    public static final String FEED_REMOVE = LDR_API_URI + "feed/unsubscribe";

    /** livedoor Reader API のURL（フィード取得） */
    public static final String FEED_ALL = LDR_API_URI + "subs";

    /** livedoor Reader API のURL（フィードの既読） */
    public static final String FEED_TOUCH_ALL = LDR_API_URI + "touch_all";

    /** livedoor Reader API のURL（フィードのレート設定） */
    public static final String FEED_SET_RATE = LDR_API_URI + "feed/set_rate";

    /** livedoor Reader API のURL（フィードのフォルダー移動） */
    public static final String FEED_MOVE = LDR_API_URI + "feed/move";

    /** livedoor Reader API のURL（オートディスカバリー） */
    public static final String FEED_DISCOVER = LDR_API_URI + "feed/discover";

    /** livedoor Reader API のURL（未読記事数取得） */
    public static final String FEED_NOTIFY = "http://rpc.reader.livedoor.com/notify";

    /** livedoor Reader API のURL（記事取得） */
    public static final String ARTICLE_ALL = LDR_API_URI + "all";

    /** livedoor Reader API のURL（未読記事取得） */
    public static final String ARTICLE_UNREAD = LDR_API_URI + "unread";

    /** livedoor Reader API のURL（フォルダー取得） */
    public static final String FOLDER_ALL = LDR_API_URI + "folders";

    /** livedoor Reader API のURL（フォルダー作成） */
    public static final String FOLDER_CREATE = LDR_API_URI + "folder/create";

    /** livedoor Reader API のURL（フォルダー削除） */
    public static final String FOLDER_DELETE = LDR_API_URI + "folder/delete";

    /** livedoor Reader API のURL（フォルダー名更新） */
    public static final String FOLDER_UPDATE = LDR_API_URI + "folder/update";

    /** livedoor Reader API のURL（コンフィグ設定） */
    public static final String CONFIG_SAVE = LDR_API_URI + "config/save?";

    /** livedoor Reader API のURL（コンフィグ取得） */
    public static final String CONFIG_LOAD = LDR_API_URI + "config/load";
}
