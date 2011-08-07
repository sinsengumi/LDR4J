package org.ldr4j.api.response;

/**
 * livedoor Reader API が返却するフォルダー情報
 *
 * @author sinsengumi
 * @version $Revision$
 */
public class Folder {

    /** フォルダーID */
    private String id;

    /** フォルダー名 */
    private String name;

    /**
     * フォルダーIDを取得します。
     *
     * @return フォルダーID
     */
    public String getId() {
        return id;
    }

    /**
     * フォルダーIDを設定します。
     *
     * @param id フォルダーID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * フォルダー名を取得します。
     *
     * @return フォルダー名
     */
    public String getName() {
        return name;
    }

    /**
     * フォルダー名を設定します。
     *
     * @param name フォルダー名
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Folder [id=" + id + ", name=" + name + "]";
    }
}
