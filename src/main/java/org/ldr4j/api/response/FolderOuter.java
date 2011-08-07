package org.ldr4j.api.response;

import java.util.Arrays;
import java.util.Map;

/**
 * livedoor Reader API が返却するフィード（アウター）情報
 *
 * @author sinsengumi
 * @version $Revision$
 */
public class FolderOuter {

    /** フォルダーIDとフォルダー名のマップ */
    private Map<String, String> name2id;

    /** フォルダー名の配列 */
    private String[] names;

    /**
     * フォルダーIDとフォルダー名のマップを取得します。
     *
     * @return フォルダーIDとフォルダー名のマップ
     */
    public Map<String, String> getName2id() {
        return name2id;
    }

    /**
     * フォルダーIDとフォルダー名のマップを設定します。
     *
     * @param name2id フォルダーIDとフォルダー名のマップ
     */
    public void setName2id(Map<String, String> name2id) {
        this.name2id = name2id;
    }

    /**
     * フォルダー名の配列を取得します。
     *
     * @return フォルダー名の配列
     */
    public String[] getNames() {
        return names;
    }

    /**
     * フォルダー名の配列を設定します。
     *
     * @param names フォルダー名の配列
     */
    public void setNames(String[] names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "FolderOuter [name2id=" + name2id + ", names=" + Arrays.toString(names) + "]";
    }
}
