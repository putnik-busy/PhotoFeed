package com.justapp.photofeed.data.network;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sergey Rodionov
 */
public class AuthInterceptorTest {

    private static final String TOKEN = "someToken";
    private static final String EXPECTED_TOKEN = "OAuth someToken";
    private static final String HEADER = "Authorization";
    private static final String PATH_SEPARATOR = "/";

    private MockWebServer mockWebServer;

    @Before
    public void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockWebServer.enqueue(new MockResponse());
        HttpUrl httpUrl = mockWebServer.url(PATH_SEPARATOR);

        AuthInterceptor authInterceptor = new AuthInterceptor();
        authInterceptor.setToken(TOKEN);

        prepareOkHttpClient(authInterceptor, httpUrl);
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void interceptTokenAuthorizationHeader() throws IOException, InterruptedException {
        RecordedRequest actualRequest = mockWebServer.takeRequest();

        assertThat(actualRequest.getHeader(HEADER), is(EXPECTED_TOKEN));
    }

    private void prepareOkHttpClient(AuthInterceptor authInterceptor, HttpUrl url) throws IOException {
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .addInterceptor(authInterceptor)
                .build();

        client.newCall(new Request.Builder()
                .url(url)
                .build())
                .execute();
    }

}
