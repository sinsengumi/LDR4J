package org.ldr4j.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.arnx.jsonic.JSON;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.ldr4j.ApiUrl;
import org.ldr4j.LDRException;
import org.ldr4j.api.methods.FeedMethod;
import org.ldr4j.api.methods.FolderMethod;
import org.ldr4j.api.methods.PinMethod;
import org.ldr4j.api.response.ApiResponseStatus;
import org.ldr4j.api.response.Article;
import org.ldr4j.api.response.Feed;
import org.ldr4j.api.response.FeedInfo;
import org.ldr4j.api.response.Folder;
import org.ldr4j.api.response.FolderOuter;
import org.ldr4j.api.response.Pin;

/**
 * livedoor Reader のAPIクライアント。
 *
 * @author sinsengumi
 * @version $Revision$
 */
public class LDRClient extends AbstractLDRHttpClient
        implements FeedMethod, PinMethod, FolderMethod {

    /** APIキー */
    private String apiKey;

    /**
     * APIクライアントを構築します。<br>
     * 構築の際、livedoorの認証を行い、その後、livedoor Reader のページに行き、APIキーを発行します。
     *
     * @param livedoorId livedoor ID
     * @param password パスワード
     * @throws LDRException HTTP接続時に例外が発生した場合
     *
     */
    public LDRClient(String livedoorId, String password) throws LDRException {
        super();
        authenticate(livedoorId, password);
    }

    /**
     * プロキシ環境下でAPIクライアントを構築します。
     *
     * @param livedoorId livedoor ID
     * @param password パスワード
     * @param proxyHost プロキシホスト
     * @param proxyPort プロキシポート
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    public LDRClient(String livedoorId, String password, String proxyHost, int proxyPort)
            throws LDRException {
        super(proxyHost, proxyPort);
        authenticate(livedoorId, password);
    }

    /**
     * 認証を行い、APIキーを発行します。 livedoorの認証を行い、その後、livedoor Reader のページに行き、APIキーを発行します。
     *
     * @param livedoorId livedoor ID
     * @param password パスワード
     * @throws LDRException HTTP接続時に例外が発生した場合、livedoor の認証に失敗した場合、APIキーの取得に失敗した場合
     */
    private void authenticate(String livedoorId, String password) throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("livedoor_id", livedoorId);
        params.put("password", password);

        LDRHttpResponse response;
        try {
            response = super.doPost(ApiUrl.LIVEDOOR_LOGIN, params);

            // ログインページは、ログイン成功の場合、リダイレクトされる
            if (response.getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY) {

                // livedoor Readerのページに行き、reader_sid（ApiKey）を取得する。
                LDRHttpResponse response2 = super.doGet(ApiUrl.LDR_URI,
                        Collections.<String, String> emptyMap());
                Header[] cookieHeader = response2.getCookieHeader();

                for (Header cookie : cookieHeader) {
                    String apiKeyName = "reader_sid=";
                    if (cookie.getValue().startsWith(apiKeyName)) {
                        apiKey = cookie.getValue().substring(apiKeyName.length(),
                                apiKeyName.length() + 32);
                    }
                }

                if (apiKey == null || apiKey.equals("")) {
                    throw new LDRException("Failed authentication in livedoor.");
                }
            } else {
                throw new LDRException("Failed authentication in livedoor.");
            }
        } catch (LDRException e) {
            throw e;
        }
    }

    @Override
    public Folder[] getAllFolders() throws LDRException {

        LDRHttpResponse response = doPost(ApiUrl.FOLDER_ALL,
                Collections.<String, String> emptyMap());

        FolderOuter folderOuter = JSON.decode(response.getBody(), FolderOuter.class);

        Map<String, String> folders = folderOuter.getName2id();

        Folder[] result = new Folder[folders.size()];

        int i = 0;
        for (Map.Entry<String, String> e : folders.entrySet()) {
            Folder folder = new Folder();
            folder.setId(e.getValue());
            folder.setName(e.getKey());

            result[i++] = folder;
        }

        return result;
    }

    @Override
    public void createFolder(String folderName) throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", folderName);
        params.put("ApiKey", this.apiKey);

        LDRHttpResponse response = doPost(ApiUrl.FOLDER_CREATE, params);

        if (response.getStatusCode() == HttpStatus.SC_OK) {
            ApiResponseStatus result = JSON.decode(response.getBody(), ApiResponseStatus.class);

            if (result.getErrorCode() != ApiResponseStatus.ERRORCODE_NO_ERROR
                    || result.getIsSuccess() != ApiResponseStatus.ISSUCCESS_SUCCESS) {
                throw new LDRException("Failed create folder. ErrorCode " + result.getErrorCode());
            }
        } else if (response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            throw new LDRException("Failed authentication in livedoor.");
        } else {
            throw new LDRException("Failed create folder.");
        }
    }

    @Override
    public void updateFolder(String folderId, String folderName) throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("folder_id", folderId);
        params.put("name", folderName);
        params.put("ApiKey", apiKey);

        LDRHttpResponse response = doPost(ApiUrl.FOLDER_UPDATE, params);

        if (response.getStatusCode() == HttpStatus.SC_OK) {
            ApiResponseStatus result = JSON.decode(response.getBody(), ApiResponseStatus.class);

            if (result.getErrorCode() != ApiResponseStatus.ERRORCODE_NO_ERROR
                    || result.getIsSuccess() != ApiResponseStatus.ISSUCCESS_SUCCESS) {
                throw new LDRException("Failed update folder. ErrorCode " + result.getErrorCode());
            }
        } else if (response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            throw new LDRException("Failed authentication in livedoor.");
        } else {
            throw new LDRException("Failed update folder.");
        }
    }

    @Override
    public void deleteFolder(String folderId) throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("folder_id", folderId);
        params.put("ApiKey", apiKey);

        LDRHttpResponse response = doPost(ApiUrl.FOLDER_DELETE, params);

        if (response.getStatusCode() == HttpStatus.SC_OK) {
            ApiResponseStatus result = JSON.decode(response.getBody(), ApiResponseStatus.class);

            if (result.getErrorCode() != ApiResponseStatus.ERRORCODE_NO_ERROR
                    || result.getIsSuccess() != ApiResponseStatus.ISSUCCESS_SUCCESS) {
                throw new LDRException("Failed delete folder. ErrorCode " + result.getErrorCode());
            }
        } else if (response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            throw new LDRException("Failed authentication in livedoor.");
        } else {
            throw new LDRException("Failed delete folder.");
        }
    }

    @Override
    public Pin[] getAllPins() throws LDRException {

        LDRHttpResponse response = doPost(ApiUrl.PIN_ALL, Collections.<String, String> emptyMap());

        return JSON.decode(response.getBody(), Pin[].class);
    }

    @Override
    public void addPin(String link, String title) throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("link", link);
        params.put("title", title);
        params.put("ApiKey", apiKey);

        LDRHttpResponse response = doPost(ApiUrl.PIN_ADD, params);

        if (response.getStatusCode() == HttpStatus.SC_OK) {
            ApiResponseStatus result = JSON.decode(response.getBody(), ApiResponseStatus.class);

            if (result.getErrorCode() != ApiResponseStatus.ERRORCODE_NO_ERROR
                    || result.getIsSuccess() != ApiResponseStatus.ISSUCCESS_SUCCESS) {
                throw new LDRException("Failed add pin. ErrorCode " + result.getErrorCode());
            }
        } else if (response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            throw new LDRException("Failed authentication in livedoor.");
        } else {
            throw new LDRException("Failed add pin.");
        }
    }

    @Override
    public void removePin(String link) throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("link", link);
        params.put("ApiKey", apiKey);

        LDRHttpResponse response = doPost(ApiUrl.PIN_REMOVE, params);

        if (response.getStatusCode() == HttpStatus.SC_OK) {
            ApiResponseStatus result = JSON.decode(response.getBody(), ApiResponseStatus.class);

            if (result.getErrorCode() != ApiResponseStatus.ERRORCODE_NO_ERROR
                    || result.getIsSuccess() != ApiResponseStatus.ISSUCCESS_SUCCESS) {
                throw new LDRException("Failed remove pin. ErrorCode " + result.getErrorCode());
            }
        } else if (response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            throw new LDRException("Failed authentication in livedoor.");
        } else {
            throw new LDRException("Failed remove pin.");
        }
    }

    @Override
    public void clearPin() throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("ApiKey", apiKey);

        LDRHttpResponse response = doPost(ApiUrl.PIN_CLEAR, params);

        if (response.getStatusCode() == HttpStatus.SC_OK) {
            ApiResponseStatus result = JSON.decode(response.getBody(), ApiResponseStatus.class);

            if (result.getErrorCode() != ApiResponseStatus.ERRORCODE_NO_ERROR
                    || result.getIsSuccess() != ApiResponseStatus.ISSUCCESS_SUCCESS) {
                throw new LDRException("Failed clear pin. ErrorCode " + result.getErrorCode());
            }
        } else if (response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            throw new LDRException("Failed authentication in livedoor.");
        } else {
            throw new LDRException("Failed clear pin.");
        }
    }

    @Override
    public long addFeed(String feedUrl) throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("feedlink", feedUrl);
        params.put("ApiKey", apiKey);

        LDRHttpResponse response = doPost(ApiUrl.FEED_ADD, params);

        if (response.getStatusCode() == HttpStatus.SC_OK) {
            ApiResponseStatus result = JSON.decode(response.getBody(), ApiResponseStatus.class);

            if (result.getErrorCode() != ApiResponseStatus.ERRORCODE_NO_ERROR
                    || result.getIsSuccess() != ApiResponseStatus.ISSUCCESS_SUCCESS) {
                throw new LDRException("Failed add feed. ErrorCode " + result.getErrorCode());
            }

            return result.getSubscribeId();

        } else if (response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            throw new LDRException("Failed authentication in livedoor.");
        } else {
            throw new LDRException("Failed add feed.");
        }
    }

    @Override
    public void removeFeed(long subscribeId) throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("subscribe_id", Long.toString(subscribeId));
        params.put("ApiKey", apiKey);

        LDRHttpResponse response = doPost(ApiUrl.FEED_REMOVE, params);

        if (response.getStatusCode() == HttpStatus.SC_OK) {
            ApiResponseStatus result = JSON.decode(response.getBody(), ApiResponseStatus.class);

            if (result.getErrorCode() != ApiResponseStatus.ERRORCODE_NO_ERROR
                    || result.getIsSuccess() != ApiResponseStatus.ISSUCCESS_SUCCESS) {
                throw new LDRException("Failed remove feed. ErrorCode " + result.getErrorCode());
            }
        } else if (response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            throw new LDRException("Failed authentication in livedoor.");
        } else {
            throw new LDRException("Failed remove feed.");
        }
    }

    @Override
    public Feed[] getAllFeeds(boolean unread) throws LDRException {
        return getAllFeeds(unread, 0);
    }

    @Override
    public Feed[] getAllFeeds(boolean unread, int limit) throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("unread", unread ? "1" : "0");
        params.put("limit", Integer.toString(limit));

        LDRHttpResponse response = doPost(ApiUrl.FEED_ALL, params);

        return JSON.decode(response.getBody(), Feed[].class);
    }

    @Override
    public Feed[] getAllFeeds(boolean unread, int limit, long fromSubscribeId) throws LDRException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Article getAllArticle(long subscribeId) throws LDRException {
        return getAllArticle(subscribeId, 0, 200);
    }

    @Override
    public Article getAllArticle(long subscribeId, int limit, int offset) throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("subscribe_id", Long.toString(subscribeId));
        params.put("limit", Integer.toString(limit));
        params.put("offset", Integer.toString(offset));

        LDRHttpResponse response = doPost(ApiUrl.ARTICLE_ALL, params);

        return JSON.decode(response.getBody(), Article.class);
    }

    @Override
    public Article getUnreadArticle(long subscribeId) throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("subscribe_id", Long.toString(subscribeId));

        LDRHttpResponse response = doPost(ApiUrl.ARTICLE_UNREAD, params);

        return JSON.decode(response.getBody(), Article.class);
    }

    @Override
    public void touchAll(long subscribeId) throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("subscribe_id", Long.toString(subscribeId));
        params.put("ApiKey", apiKey);

        LDRHttpResponse response = doPost(ApiUrl.FEED_TOUCH_ALL, params);

        if (response.getStatusCode() == HttpStatus.SC_OK) {
            ApiResponseStatus result = JSON.decode(response.getBody(), ApiResponseStatus.class);

            if (result.getErrorCode() != ApiResponseStatus.ERRORCODE_NO_ERROR
                    || result.getIsSuccess() != ApiResponseStatus.ISSUCCESS_SUCCESS) {
                throw new LDRException("Failed touch all. ErrorCode " + result.getErrorCode());
            }
        } else if (response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            throw new LDRException("Failed authentication in livedoor.");
        } else {
            throw new LDRException("Failed touch all.");
        }
    }

    @Override
    public void setRate(long subscribeId, int rate) throws LDRException {

        if (rate < 0 || 5 < rate) {
            throw new IllegalArgumentException("レートは0～5の範囲で指定してください");
        }

        // リクエストパラメータの作成
        Map<String, String> params = new HashMap<String, String>();
        params.put("subscribe_id", Long.toString(subscribeId));
        params.put("rate", Integer.toString(rate));
        params.put("ApiKey", apiKey);

        LDRHttpResponse response = doPost(ApiUrl.FEED_SET_RATE, params);

        if (response.getStatusCode() == HttpStatus.SC_OK) {
            ApiResponseStatus result = JSON.decode(response.getBody(), ApiResponseStatus.class);

            if (result.getErrorCode() != ApiResponseStatus.ERRORCODE_NO_ERROR
                    || result.getIsSuccess() != ApiResponseStatus.ISSUCCESS_SUCCESS) {
                throw new LDRException("Failed set rate. ErrorCode " + result.getErrorCode());
            }
        } else if (response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            throw new LDRException("Failed authentication in livedoor.");
        } else {
            throw new LDRException("Failed set rate.");
        }
    }

    @Override
    public void moveFeed(long subscribeId) throws LDRException {
        moveFeed(subscribeId, "");
    }

    @Override
    public void moveFeed(long subscribeId, String folderName) throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("subscribe_id", Long.toString(subscribeId));
        params.put("to", folderName);
        params.put("ApiKey", apiKey);

        LDRHttpResponse response = doPost(ApiUrl.FEED_MOVE, params);

        if (response.getStatusCode() == HttpStatus.SC_OK) {
            ApiResponseStatus result = JSON.decode(response.getBody(), ApiResponseStatus.class);

            if (result.getErrorCode() != ApiResponseStatus.ERRORCODE_NO_ERROR
                    || result.getIsSuccess() != ApiResponseStatus.ISSUCCESS_SUCCESS) {
                throw new LDRException("Failed move folder. ErrorCode " + result.getErrorCode());
            }
        } else if (response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            throw new LDRException("Failed authentication in livedoor.");
        } else {
            throw new LDRException("Failed move folder.");
        }
    }

    @Override
    public int getNotify(String livedoorId) throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("user", livedoorId);

        LDRHttpResponse response = doGet(ApiUrl.FEED_NOTIFY, params);

        if (response.getStatusCode() == HttpStatus.SC_OK) {
            String[] responseText = response.getBody().split("\\|");
            return new Integer(responseText[1]);
        } else {
            throw new LDRException("Failed notify.");
        }
    }

    @Override
    public FeedInfo[] discover(String url) throws LDRException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("url", url);

        LDRHttpResponse response = doGet(ApiUrl.FEED_DISCOVER, params);

        return JSON.decode(response.getBody(), FeedInfo[].class);
    }
}
