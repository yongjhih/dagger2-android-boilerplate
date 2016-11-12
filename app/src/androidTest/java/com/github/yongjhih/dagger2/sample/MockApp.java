package com.github.yongjhih.dagger2.sample;

/**
 * Created by andrew on 11/13/16.
 */
public class MockApp extends App {
    @Override
    protected MainComponent createMainComponent() {
        //return DaggerTestComponent.builder().build();
        return DaggerMainActivityTest_TestComponent.builder().build();
    }
}
