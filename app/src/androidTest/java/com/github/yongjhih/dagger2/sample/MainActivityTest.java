package com.github.yongjhih.dagger2.sample;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.novoda.rxpresso.RxPresso;
import com.novoda.rxpresso.matcher.RxExpect;
import com.novoda.rxpresso.matcher.RxMatcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import rx.Notification;
import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.novoda.rxpresso.matcher.RxExpect.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;

/**
 * Created by andrew on 11/8/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Inject
    GitHub github;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public FragmentTestRule<ReposFragment> mFragmentRule = new FragmentTestRule<>(ReposFragment.class);

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        App app = (App) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = (TestComponent) app.mainComponent();
        component.inject(this);
    }

    @Test
    public void testListDisplayed() {
        // Launch the activity to make the fragment visible
        mFragmentRule.launchActivity(null);
        onView(withId(R.id.list)).check(matches(isDisplayed()));
    }

    @Test
    public void testRepos() {
        Repo repo = new Repo();
        repo.owner = new User();
        repo.owner.login = "yongjhh";
        repo.owner.avatar_url = "https://avatars.githubusercontent.com/u/213736?v=3";
        repo.name = "android-proguards";
        when(github.repos("yongjhih")).thenReturn(Observable.just(Arrays.asList(repo)));

        //mocker(GitHub.class).when(github -> github.repos("yongjhih")).thenReturn(github -> Observable.just(Arrays.asList(repo)));
        mFragmentRule.launchActivity(null);
        onView(withText("android-proguards")).check(matches(isDisplayed()));
    }

    //@Test
    //public void testRxRepos() {
    //    Repo repo = new Repo();
    //    repo.owner = new User();
    //    repo.owner.login = "yongjhh";
    //    repo.owner.avatar_url = "https://avatars.githubusercontent.com/u/213736?v=3";
    //    repo.name = "android-proguards";

    //    //List<Repo> t = Collections.emptyList();
    //    RxPresso rxpresso = RxPresso.from(github);
    //    Espresso.registerIdlingResources(rxpresso);
    //    rxpresso.given(github.repos("yongjhih"))
    //            .withEventsFrom(Observable.just(Arrays.asList(repo)))
    //            .expect(any(t.getClass()))
    //            .thenOnView(withText("android-proguards"))
    //            .check(matches(isDisplayed()));
    //}
}
