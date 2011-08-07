package org.ldr4j.api.methods;

import org.ldr4j.LDRException;
import org.ldr4j.api.response.Folder;

/**
 * フォルダー情報の操作を提供します。
 *
 * @author sinsengumi
 * @version $Revision$
 */
public interface FolderMethod {

    /**
     * フォルダー情報を取得します。
     *
     * @return すべてのフォルダー情報
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    Folder[] getAllFolders() throws LDRException;

    /**
     * フォルダー情報を作成します。
     *
     * @param folderName フォルダー名
     * @throws LDRException HTTP接続時に例外が発生した場合、フォルダー情報の作成に失敗した場合
     */
    void createFolder(String folderName) throws LDRException;

    /**
     * フォルダー名を更新します。
     *
     * @param folderId 更新するフォルダーID
     * @param folderName 更新するフォルダー名
     * @throws LDRException HTTP接続時に例外が発生した場合、フォルダー名の更新に失敗した場合
     */
    void updateFolder(String folderId, String folderName) throws LDRException;

    /**
     * フォルダー情報を削除します。
     *
     * @param folderId 削除するフォルダーID
     * @throws LDRException HTTP接続時に例外が発生した場合、フォルダー情報の削除に失敗した場合
     */
    void deleteFolder(String folderId) throws LDRException;
}
