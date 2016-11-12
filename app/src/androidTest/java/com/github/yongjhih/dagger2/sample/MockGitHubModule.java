package com.github.yongjhih.dagger2.sample;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import java.util.Arrays;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module
public class MockGitHubModule {
    @Provides
    @Singleton
    GitHub provideGitHub() {
        return mock(GitHub.class);
    }
}
