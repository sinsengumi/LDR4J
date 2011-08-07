package org.ldr4j.api.methods;

import org.ldr4j.LDRException;
import org.ldr4j.api.response.Pin;

/**
 * ピン情報の操作を提供します。
 *
 * @author sinsengumi
 * @version $Revision$
 */
public interface PinMethod {

    /**
     * ピン情報を取得します。
     *
     * @return すべてのピン情報
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    Pin[] getAllPins() throws LDRException;

    /**
     * ピン情報を追加します。<br>
     * 但し、ピン情報のURLが同一のものがある場合は追加されません。
     *
     * @param link リンクURL
     * @param title タイトル
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    void addPin(String link, String title) throws LDRException;

    /**
     * ピン情報を削除します。
     *
     * @param link リンクURL
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    void removePin(String link) throws LDRException;

    /**
     * ピン情報をすべて削除します。
     *
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    void clearPin() throws LDRException;
}
