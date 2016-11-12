package com.github.yongjhih.dagger2.sample;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import rx.Observable;

import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static mocker.Mocker.mocker;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by andrew on 11/8/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Inject
    GitHub github;

    @Singleton
    @Component(modules = MockGitHubModule.class)
    public interface TestComponent extends MainComponent {
        void inject(MainActivityTest mainActivityTest);
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public FragmentTestRule<ReposFragment> mFragmentRule = new FragmentTestRule<>(ReposFragment.class);

    @Test
    public void testListDisplayed() {
        // Launch the activity to make the fragment visible
        mFragmentRule.launchActivity(null);
        onView(withId(R.id.list)).check(matches(isDisplayed()));
    }

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        App app = (App) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = (TestComponent) app.mainComponent();
        component.inject(this);
    }

    @Test
    public void testRepos() {
        Repo repo = new Repo();
        repo.owner.login = "yongjhh";
        repo.owner.login = "https://avatars.githubusercontent.com/u/213736?v=3";
        repo.name = "android-proguards";
        when(github.repos("yongjhih")).thenReturn(Observable.just(Arrays.asList(repo)));

        //mocker(GitHub.class).when(github -> github.repos("yongjhih")).thenReturn(github -> Arrays.asList(repo));
        mFragmentRule.launchActivity(null);
        onView(withText("android-proguards")).check(matches(isDisplayed()));
    }
}
