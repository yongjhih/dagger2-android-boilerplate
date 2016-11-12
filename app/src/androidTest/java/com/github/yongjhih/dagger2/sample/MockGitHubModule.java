package com.github.yongjhih.dagger2.sample;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class MockGitHubModule {
    @Provides
    @Singleton
    GitHub provideGitHub() {
        return mock(GitHub.class);
    }
}
