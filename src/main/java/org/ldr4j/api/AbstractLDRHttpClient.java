package org.ldr4j.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.ldr4j.LDR4J;
import org.ldr4j.LDRException;

/**
 * livedoor Reader のAPIクライアントの基底クラス。
 *
 * @author sinsengumi
 * @version $Revision$
 */
abstract class AbstractLDRHttpClient {

    /** HTTPクライアント */
    private HttpClient httpClient;

    /**
     * LDR4JのHTTPクライアントを構築します。
     */
    protected AbstractLDRHttpClient() {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
        HttpProtocolParams.setUserAgent(params, LDR4J.TITLE + "/" + LDR4J.VERSION);

        httpClient = new DefaultHttpClient(params);
    }

    /**
     * プロキシ環境下でLDR4JのHTTPクライアントを構築します。
     *
     * @param proxyHost プロキシホスト
     * @param proxyPort プロキシポート
     */
    protected AbstractLDRHttpClient(String proxyHost, int proxyPort) {
        this();
        HttpHost proxy = new HttpHost(proxyHost, proxyPort);
        httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
    }

    /**
     * POSTメソッド（パラメータなし）
     *
     * @param url 接続するURL
     * @return HTTPのレスポンス情報
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    protected LDRHttpResponse doPost(String url) throws LDRException {
        return doPost(url, Collections.<String, String> emptyMap());
    }

    /**
     * POSTメソッド
     *
     * @param url 接続するURL
     * @param requestParams リクエストパラメータ
     * @return HTTPのレスポンス情報
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    protected LDRHttpResponse doPost(
            String url, Map<String, String> requestParams) throws LDRException {

        HttpPost post = null;

        try {
            post = new HttpPost(url);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : requestParams.entrySet()) {
                params.add(new BasicNameValuePair(
                        (String) entry.getKey(), (String) entry.getValue()));
            }

            post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse response = httpClient.execute(post);

            LDRHttpResponse ldrResponse = new LDRHttpResponse();
            ldrResponse.setStatusCode(response.getStatusLine().getStatusCode());
            ldrResponse.setHeader(response.getAllHeaders());
            ldrResponse.setBody(EntityUtils.toString(response.getEntity()));
            ldrResponse.setCookieHeader(response.getHeaders("Set-Cookie"));

            return ldrResponse;

        } catch (UnsupportedEncodingException e) {
            throw new LDRException(e);
        } catch (ClientProtocolException e) {
            throw new LDRException(e);
        } catch (IOException e) {
            throw new LDRException(e);
        } finally {
            if (post != null) {
                post.abort();
            }
        }
    }

    /**
     * GETメソッド（パラメータなし）
     *
     * @param url 接続するURL
     * @return HTTPのレスポンス情報
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    protected LDRHttpResponse doGet(String url) throws LDRException {
        return doGet(url, Collections.<String, String> emptyMap());
    }

    /**
     * GETメソッド
     *
     * @param url 接続するURL
     * @param requestParams リクエストパラメータ
     * @return HTTPのレスポンス情報
     * @throws LDRException HTTP接続時に例外が発生した場合
     */
    protected LDRHttpResponse doGet(String url, Map<String, String> requestParams)
            throws LDRException {

        HttpGet get = null;

        try {
            StringBuilder builder = new StringBuilder(url);
            builder.append("?");
            for (Map.Entry<String, String> entry : requestParams.entrySet()) {
                builder.append((String) entry.getKey());
                builder.append("=");
                builder.append((String) entry.getValue());
                builder.append("&");
            }

            String tmpUrl = builder.toString();
            tmpUrl = tmpUrl.substring(0, tmpUrl.length() - 1);

            get = new HttpGet(tmpUrl);

            HttpResponse response = httpClient.execute(get);
            LDRHttpResponse ldrResponse = new LDRHttpResponse();
            ldrResponse.setStatusCode(response.getStatusLine().getStatusCode());
            ldrResponse.setHeader(response.getAllHeaders());
            ldrResponse.setBody(EntityUtils.toString(response.getEntity()));
            ldrResponse.setCookieHeader(response.getHeaders("Set-Cookie"));

            return ldrResponse;

        } catch (UnsupportedEncodingException e) {
            throw new LDRException(e);
        } catch (ClientProtocolException e) {
            throw new LDRException(e);
        } catch (IOException e) {
            throw new LDRException(e);
        } finally {
            if (get != null) {
                get.abort();
            }
        }
    }

    /**
     * HTTPクライアントをシャットダウンします。<br>
     * この操作を行うことで、セッション状態が切断されます。
     *
     */
    public void shutdown() {
        httpClient.getConnectionManager().shutdown();
    }
}
