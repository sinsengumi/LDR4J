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
import org.ldr4j.api.response.Article;
import org.ldr4j.api.response.Feed;
import org.ldr4j.api.response.FeedInfo;
import org.ldr4j.api.response.Folder;
import org.ldr4j.api.response.Item;

public class FeedMethodTest {

    private static LDRClient ldrClient;

    private static Properties properties;

    private static String TEST_FEED = "http://news.livedoor.com/rss/summary/41.xml";
    private static String TEST_FEED2 = "http://news.livedoor.com/rss/summary/5.xml";
    private static String TEST_FOLDER_NAME = "テストフォルダー";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        properties = TestAccountReader.read();
        ldrClient = new LDRClient(
                properties.getProperty("livedoorId"), properties.getProperty("password"));

        Feed[] feeds = ldrClient.getAllFeeds(false);
        for (Feed feed : feeds) {
            ldrClient.removeFeed(feed.getSubscribeId());
        }
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        ldrClient.shutdown();
    }

    @Before
    public void setUp() throws Exception {
        ldrClient.createFolder(TEST_FOLDER_NAME);
    }

    @After
    public void tearDown() throws Exception {
        Feed[] feeds = ldrClient.getAllFeeds(false);
        for (Feed feed : feeds) {
            ldrClient.removeFeed(feed.getSubscribeId());
        }

        Folder[] folders = ldrClient.getAllFolders();
        for (Folder folder : folders) {
            ldrClient.deleteFolder(folder.getId());
        }
    }

    @Test
    public void testAddFeed() throws LDRException {
        Feed[] beforeFeeds = ldrClient.getAllFeeds(false);

        ldrClient.addFeed(TEST_FEED);

        Feed[] afterFeeds = ldrClient.getAllFeeds(false);

        assertEquals(beforeFeeds.length + 1, afterFeeds.length);
    }

    @Test(expected = LDRException.class)
    public void testAddFeedInvalidFeedURL() throws LDRException {
        ldrClient.addFeed("InvalidFeedURL!!");
    }

    @Test(expected = LDRException.class)
    public void testAddFeedSameFeedURL() throws LDRException {
        ldrClient.addFeed(TEST_FEED);
        ldrClient.addFeed(TEST_FEED);
    }

    @Test
    public void testRemoveFeed() throws LDRException {
        long subscribeId = ldrClient.addFeed(TEST_FEED);

        Feed[] beforeFeeds = ldrClient.getAllFeeds(false);

        ldrClient.removeFeed(subscribeId);

        Feed[] afterFeeds = ldrClient.getAllFeeds(false);

        assertEquals(beforeFeeds.length - 1, afterFeeds.length);
    }

    @Test(expected = LDRException.class)
    public void testRemoveFeedInvalidSubscribeId() throws LDRException {
        ldrClient.removeFeed(1L);
    }

    @Test
    public void testGetAllFeedsBooleanAll() throws LDRException {
        long subscribeId = ldrClient.addFeed(TEST_FEED);
        long subscribeId2 = ldrClient.addFeed(TEST_FEED2);

        Feed[] feeds = ldrClient.getAllFeeds(false);

        for (Feed feed : feeds) {
            if (feed.getSubscribeId() == subscribeId) {
                assertTrue(true);
            }
            if (feed.getSubscribeId() == subscribeId2) {
                assertTrue(true);
            }
        }
    }

    @Test
    public void testGetAllFeedsBooleanUnread() throws LDRException {
        long subscribeId = ldrClient.addFeed(TEST_FEED);
        long subscribeId2 = ldrClient.addFeed(TEST_FEED2);

        // TEST_FEED2を既読にする。
        ldrClient.touchAll(subscribeId2);

        Feed[] feeds = ldrClient.getAllFeeds(true);

        for (Feed feed : feeds) {
            if (feed.getSubscribeId() == subscribeId) {
                assertTrue(true);
            }
            if (feed.getSubscribeId() == subscribeId2) {
                assertTrue(false);
            }
        }
    }

    @Test
    public void testGetAllFeedsBooleanInt() throws LDRException {
        ldrClient.addFeed(TEST_FEED);
        ldrClient.addFeed(TEST_FEED2);

        Feed[] feeds = ldrClient.getAllFeeds(true, 1);

        assertEquals(1, feeds.length);
    }

    @Test
    public void testGetAllFeedsBooleanInt0() throws LDRException {
        ldrClient.addFeed(TEST_FEED);
        ldrClient.addFeed(TEST_FEED2);

        Feed[] feeds = ldrClient.getAllFeeds(true, 0);

        assertEquals(2, feeds.length);
    }

    @Test
    public void testGetAllArticle() throws LDRException {
        long subscribeId = ldrClient.addFeed(TEST_FEED);
        Article article = ldrClient.getAllArticle(subscribeId);

        assertEquals(subscribeId, article.getSubscribeId());
    }

    @Test
    public void testGetAllArticleLimit() throws LDRException {
        long subscribeId = ldrClient.addFeed(TEST_FEED);
        Article article = ldrClient.getAllArticle(subscribeId, 10, 0);

        assertEquals(10, article.getItems().length);
    }

    @Test
    public void testGetAllArticleOffset() throws LDRException {
        long subscribeId = ldrClient.addFeed(TEST_FEED);
        Article article = ldrClient.getAllArticle(subscribeId, 10, 190);

        if (article.getItems().length > 0) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void testGetUnreadArticle() throws LDRException {
        long subscribeId = ldrClient.addFeed(TEST_FEED);
        Article article = ldrClient.getUnreadArticle(subscribeId);

        Item[] items = article.getItems();

        if (article.getItems().length > 0) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void testTouchAll() throws LDRException {
        long subscribeId = ldrClient.addFeed(TEST_FEED);
        ldrClient.touchAll(subscribeId);

        Feed[] feeds = ldrClient.getAllFeeds(true);

        assertEquals(0, feeds.length);
    }

    @Test
    public void testTouchAllInvalidSubscribeId() throws LDRException {
        ldrClient.addFeed(TEST_FEED);
        ldrClient.touchAll(1L);

        // 存在しないフィードIDを指定しても例外は起きない
        assertTrue(true);
    }

    @Test
    public void testSetRate() throws LDRException {
        long subscribeId = ldrClient.addFeed(TEST_FEED);
        ldrClient.setRate(subscribeId, 5);

        Feed[] feeds = ldrClient.getAllFeeds(false);
        for (Feed feed : feeds) {
            if (feed.getSubscribeId() == subscribeId) {
                assertEquals(5, feed.getRate());
                return;
            }
        }
        assertTrue(false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRateInvalidRate() throws LDRException {
        long subscribeId = ldrClient.addFeed(TEST_FEED);
        ldrClient.setRate(subscribeId, -10);
    }

    @Test(expected = LDRException.class)
    public void testSetRateInvalidSubscribeId() throws LDRException {
        ldrClient.addFeed(TEST_FEED);
        ldrClient.setRate(1L, 1);
    }

    @Test
    public void testMoveFeedLong() throws LDRException {
        long subscribeId = ldrClient.addFeed(TEST_FEED);
        ldrClient.moveFeed(subscribeId, TEST_FOLDER_NAME);

        ldrClient.moveFeed(subscribeId);

        Feed[] feeds = ldrClient.getAllFeeds(true);
        for (Feed feed : feeds) {
            if (feed.getSubscribeId() == subscribeId) {
                if (feed.getFolder().equals("")) {
                    assertTrue(true);
                } else {
                    assertTrue(false);
                }
            }
        }
    }

    @Test(expected = LDRException.class)
    public void testMoveFeedLongInvalidSubscribeId() throws LDRException {
        ldrClient.addFeed(TEST_FEED);
        ldrClient.moveFeed(1L);
    }

    @Test
    public void testMoveFeedLongString() throws LDRException {
        long subscribeId = ldrClient.addFeed(TEST_FEED);
        ldrClient.moveFeed(subscribeId, TEST_FOLDER_NAME);

        Feed[] feeds = ldrClient.getAllFeeds(true);
        for (Feed feed : feeds) {
            if (feed.getSubscribeId() == subscribeId) {
                if (feed.getFolder().equals(TEST_FOLDER_NAME)) {
                    assertTrue(true);
                } else {
                    assertTrue(false);
                }
            }
        }
    }

    @Test
    public void testMoveFeedLongStringNotExistFolder() throws LDRException {
        long subscribeId = ldrClient.addFeed(TEST_FEED);

        // 存在しないフォルダー名を指定しても例外は起きない
        ldrClient.moveFeed(subscribeId, null);
        assertTrue(true);
    }

    @Test
    public void testGetNotify() throws LDRException {
        ldrClient.addFeed(TEST_FEED);

        int notify = ldrClient.getNotify(properties.getProperty("livedoorId"));

        // 未読が必ずあること
        if (notify > 0) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void testGetNotifyNotUser() throws LDRException {
        ldrClient.addFeed(TEST_FEED);

        int notify = ldrClient.getNotify("aaaaaaaaaaaaaaaaaaaaaaaaaa");

        assertEquals(-1, notify);
    }

    @Test
    public void testDiscover() throws LDRException {
        FeedInfo[] feedInfos = ldrClient.discover("http://www.livedoor.com/");

        if (feedInfos.length > 0) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void testDiscoverNoFeed() throws LDRException {
        FeedInfo[] feedInfos = ldrClient.discover("http://mixi.jp/");

        assertEquals(0, feedInfos.length);
    }

    @Test
    public void testDiscoverInvalidURL() throws LDRException {
        FeedInfo[] feedInfos = ldrClient.discover("http://www.livedoorrrrrrr.com/");

        assertEquals(0, feedInfos.length);
    }
}
