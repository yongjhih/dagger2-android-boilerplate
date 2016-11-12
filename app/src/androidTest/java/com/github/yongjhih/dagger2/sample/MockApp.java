package com.github.yongjhih.dagger2.sample;

/**
 * Created by andrew on 11/13/16.
 */
public class MockApp extends App {
    @Override
    protected MainComponent createMainComponent() {
        AppModule appModule = new AppModule(this);
        GitHubModule gitHubModule = new GitHubModule("https://api.github.com/");

        return DaggerTestComponent.builder()
                .appModule(appModule)
                .gitHubModule(gitHubModule)
                .build();
    }
}
