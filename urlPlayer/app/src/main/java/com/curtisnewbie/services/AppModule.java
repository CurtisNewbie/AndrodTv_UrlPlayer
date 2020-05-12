package com.curtisnewbie.services;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * ------------------------------------
 * <p>
 * Author: Yongjie Zhuang
 * <p>
 * ------------------------------------
 * <p>
 * Module for DI in this application
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    HttpService provideHttpService() {
        return new HttpService();
    }

}
