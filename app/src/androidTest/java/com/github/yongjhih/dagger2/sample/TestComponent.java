package com.github.yongjhih.dagger2.sample;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrew on 11/13/16.
 */

@Singleton
@Component(modules = {AppModule.class, MockGitHubModule.class})
public interface TestComponent extends MainComponent {
    void inject(MainActivityTest mainActivityTest);
}

