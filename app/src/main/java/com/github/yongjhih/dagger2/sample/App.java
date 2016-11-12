package com.github.yongjhih.dagger2.sample;

import android.support.multidex.MultiDexApplication;

import javax.inject.Singleton;

import dagger.Component;

public class App extends MultiDexApplication {
    MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = createMainComponent();
    }

    // Allow override by flavor such as androidTest
    protected MainComponent createMainComponent() {
        AppModule appModule = new AppModule(this);
        GitHubModule gitHubModule = new GitHubModule("https://api.github.com/");

        return DaggerMainComponent.builder()
                .appModule(appModule)
                .gitHubModule(gitHubModule)
                .build();
    }

    public MainComponent mainComponent() {
        return mainComponent;
    }
}
