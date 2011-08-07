package org.ldr4j;

/**
 * LDR4Jを使用する際に発生する例外です。
 *
 * @author sinsengumi
 * @version $Revision$
 */
public class LDRException extends Exception {

    /** シリアルバージョンUID */
    private static final long serialVersionUID = -1091462729841111012L;

    /**
     * LDRException オブジェクトを構築します。
     */
    public LDRException() {
    }

    /**
     * 指定されたメッセージで LDRException オブジェクトを構築します。
     *
     * @param message メッセージ
     */
    public LDRException(String message) {
        super(message);
    }

    /**
     * 指定された原因で LDRException オブジェクトを構築します。
     *
     * @param cause 原因
     */
    public LDRException(Throwable cause) {
        super(cause);
    }

    /**
     * 指定されたメッセージ、原因で LDRException オブジェクトを構築します。
     *
     * @param message メッセージ
     * @param cause 原因
     */
    public LDRException(String message, Throwable cause) {
        super(message, cause);
    }
}
