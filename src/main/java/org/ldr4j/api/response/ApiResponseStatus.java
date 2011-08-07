package org.ldr4j.api.response;

/**
 * livedoor Reader API が返却するAPIの成功可否のステータス
 *
 * @author sinsengumi
 * @version $Revision$
 */
public class ApiResponseStatus {

    /** 成功コード：失敗 */
    public static final int ISSUCCESS_FAILURE = 0;

    /** 成功コード：成功 */
    public static final int ISSUCCESS_SUCCESS = 1;

    /** エラーコード：エラーなし */
    public static final int ERRORCODE_NO_ERROR = 0;

    /** フィードID */
    private long subscribeId;

    /** 成功可否 */
    private int isSuccess;

    /** エラーコード */
    private int errorCode;

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
     * 成功可否を取得します。
     *
     * @return 成功可否
     */
    public int getIsSuccess() {
        return isSuccess;
    }

    /**
     * 成功可否を設定します。
     *
     * @param isSuccess 成功可否
     */
    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * エラーコードを取得します。
     *
     * @return エラーコード
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * エラーコードを設定します。
     *
     * @param errorCode エラーコード
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
