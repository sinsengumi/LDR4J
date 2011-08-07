package org.ldr4j.api;

import org.apache.http.Header;

/**
 * LDR4Jで使用するレスポンス情報。
 *
 * @author sinsengumi
 * @version $Revision$
 */
class LDRHttpResponse {

    /** HTTPステータスコード */
    private int statusCode;

    /** HTTPレスポンスヘッダー */
    private Header[] header;

    /** HTTPレスポンスボディ */
    private String body;

    /** HTTPレスポンスヘッダーのクッキー情報 */
    private Header[] cookieHeader;

    /**
     * HTTPステータスコードを取得します。
     *
     * @return HTTPステータスコード
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * HTTPステータスコードを設定します。
     *
     * @param statusCode HTTPステータスコード
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * HTTPレスポンスヘッダーを取得します。
     *
     * @return HTTPレスポンスヘッダー
     */
    public Header[] getHeader() {
        return header;
    }

    /**
     * HTTPレスポンスヘッダーを設定します。
     *
     * @param header HTTPレスポンスヘッダー
     */
    public void setHeader(Header[] header) {
        this.header = header;
    }

    /**
     * HTTPレスポンスボディを取得します。
     *
     * @return HTTPレスポンスボディ
     */
    public String getBody() {
        return body;
    }

    /**
     * HTTPレスポンスボディを設定します。
     *
     * @param body HTTPレスポンスボディ
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * HTTPレスポンスヘッダーのクッキー情報を取得します。
     *
     * @return HTTPレスポンスヘッダーのクッキー情報
     */
    public Header[] getCookieHeader() {
        return cookieHeader;
    }

    /**
     * HTTPレスポンスヘッダーのクッキー情報を設定します。
     *
     * @param cookieHeader HTTPレスポンスヘッダーのクッキー情報
     */
    public void setCookieHeader(Header[] cookieHeader) {
        this.cookieHeader = cookieHeader;
    }
}
