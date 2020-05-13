package com.curtisnewbie.services;

import com.curtisnewbie.activities.MainActivity;
import com.curtisnewbie.activities.MediaListActivity;
import com.curtisnewbie.activities.MediaListAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ------------------------------------
 * <p>
 * Author: Yongjie Zhuang
 * <p>
 * ------------------------------------
 * <p>
 * Component for DI.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainActivity activity);

    void inject(HttpService httpService);

    void inject(MediaListActivity mediaListActivity);

    void inject(MediaListAdapter mediaListAdapter);
}
