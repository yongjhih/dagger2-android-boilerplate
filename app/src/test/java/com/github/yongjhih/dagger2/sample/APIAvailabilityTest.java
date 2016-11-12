package com.github.yongjhih.dagger2.sample;

import android.annotation.TargetApi;
import android.os.Build;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import com.google.common.io.Resources;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import br.ufs.github.rxassertions.RxAssertions;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;

import static org.assertj.core.api.Assertions.*;

public class APIAvailabilityTest {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Test public void testGitHubRepos() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        GitHub github = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(LoganSquareConverterFactory.create())
                .baseUrl(mockWebServer.url("").toString())
                .client(client.build())
                .build().create(GitHub.class);

        String repos = Resources.toString(getClass().getClassLoader().getResource("repos.json"), StandardCharsets.UTF_8);
        mockWebServer.enqueue(new MockResponse().setBody(repos));
        RxAssertions.assertThat(github.repos("yongjhih")
                .flatMap(Observable::from)
                .take(1)
                .map(repo -> repo.owner.login))
                .expectedSingleValue("yongjhih");
    }
}
