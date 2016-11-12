package com.github.yongjhih.dagger2.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.view.Menu;
import android.view.MenuItem;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * TODO restructure packages:
 * <pre>
 * domain/
 * domain/GetRepos
 * domain/GetUsers
 * entity/Repo
 * entity/User
 * presenter/
 * presenter/SimpleReposPresenter
 * repository/
 * repository/GitHubRepository
 * inject/
 * inject/module/AppModule
 * inject/module/GitHubModule
 * inject/component/MainComponent
 * net/
 * net/GitHubService
 * ui/
 * ui/MainActivity
 * </pre>
 */
@VisibleForTesting
public class TestActivity
        extends RxAppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
    }
}
