package org.ldr4j.api.methods;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ldr4j.LDRException;
import org.ldr4j.api.LDRClient;
import org.ldr4j.api.TestAccountReader;
import org.ldr4j.api.response.Folder;

public class FolderMethodTest {

    private static LDRClient ldrClient;

    private static Properties properties;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        properties = TestAccountReader.read();
        ldrClient = new LDRClient(
                properties.getProperty("livedoorId"), properties.getProperty("password"));
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        ldrClient.shutdown();
    }

    @Before
    public void setUp() throws Exception {
        Folder[] folders = ldrClient.getAllFolders();

        // 全フォルダ削除
        for (Folder folder : folders) {
            ldrClient.deleteFolder(folder.getId());
        }
    }

    @After
    public void tearDown() throws Exception {
        Folder[] folders = ldrClient.getAllFolders();

        // 全フォルダ削除
        for (Folder folder : folders) {
            ldrClient.deleteFolder(folder.getId());
        }
    }

    @Test
    public void testGetAllFolders() throws LDRException {
        String testFolderName = "テスト用フォルダ";

        ldrClient.createFolder(testFolderName);

        Folder[] folders = ldrClient.getAllFolders();

        for (Folder folder : folders) {
            // 追加したフォルダが存在すること
            if (testFolderName.equals(folder.getName())) {
                ldrClient.deleteFolder(folder.getId());
                assertTrue(true);
                return;
            }
        }
        assertTrue(false);
    }

    @Test
    public void testGetAllFoldersNotFolders() throws LDRException {
        Folder[] folders = ldrClient.getAllFolders();

        // フォルダ数が0であること
        assertEquals(0, folders.length);
    }

    @Test
    public void testCreateFolder() throws LDRException {
        Folder[] beforeFolders = ldrClient.getAllFolders();

        ldrClient.createFolder("テスト用フォルダ");

        Folder[] afterFolders = ldrClient.getAllFolders();

        for (Folder folder : afterFolders) {
            if (folder.getName().equals("テスト用フォルダ")) {
                // 取得したフォルダ名に作成したフォルダ名が入っていること
                assertTrue(true);
            }
        }

        // フォルダ数が1増えていること
        assertEquals(beforeFolders.length + 1, afterFolders.length);
    }

    @Test(expected = LDRException.class)
    public void testCreateFolderSameFolderName() throws LDRException {
        // 同じ名前でフォルダを作成すると例外
        ldrClient.createFolder("テスト用フォルダ");
        ldrClient.createFolder("テスト用フォルダ");
    }

    @Test
    public void testUpdateFolder() throws LDRException {
        ldrClient.createFolder("テスト用フォルダ");

        Folder[] beforeFolders = ldrClient.getAllFolders();

        for (Folder folder : beforeFolders) {
            if (folder.getName().equals("テスト用フォルダ")) {
                // フォルダ名をリネーム
                ldrClient.updateFolder(folder.getId(), "テスト用フォルダリネーム後");
            }
        }

        Folder[] afterFolders = ldrClient.getAllFolders();
        for (Folder folder : afterFolders) {
            if (folder.getName().equals("テスト用フォルダリネーム後")) {
                assertTrue(true);
            }

            if (folder.getName().equals("テスト用フォルダ")) {
                // リネーム前のフォルダがあればテスト失敗
                assertTrue(false);
            }
        }
    }

    @Test(expected = LDRException.class)
    public void testUpdateFolderNotExistId() throws LDRException {
        ldrClient.updateFolder("NonExistId", "テスト用フォルダリネーム後");
    }

    @Test
    public void testDeleteFolder() throws LDRException {
        ldrClient.createFolder("テスト用フォルダ");

        Folder[] beforeFolders = ldrClient.getAllFolders();
        for (Folder folder : beforeFolders) {
            if ("テスト用フォルダ".equals(folder.getName())) {
                // フォルダを削除
                ldrClient.deleteFolder(folder.getId());
            }
        }

        Folder[] afterFolders = ldrClient.getAllFolders();

        // フォルダ数が1減っていること
        assertEquals(beforeFolders.length - 1, afterFolders.length);
    }

    @Test(expected = LDRException.class)
    public void testDeleteFolderNotExistId() throws LDRException {
        // 存在しないIDで削除を行うと例外
        ldrClient.deleteFolder("NonExistId");
    }
}
