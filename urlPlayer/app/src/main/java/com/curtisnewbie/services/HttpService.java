package com.curtisnewbie.services;

import android.util.Log;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * ------------------------------------
 * <p>
 * Author: Yongjie Zhuang
 * <p>
 * ------------------------------------
 * <p>
 * Http Service
 */
@Singleton
public class HttpService {

    String baseUrl;

    @Inject
    ObjectMapper objectMapper;

    public HttpService() {
        App.getAppComponent().inject(this);
    }

    /**
     * Init the http service with the base url
     */
    public void init(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Return whether the http service is initialised
     *
     * @return
     */
    public boolean isInitialised() {
        return this.baseUrl != null;
    }

    /**
     * Fetch a media list from backend
     *
     * @return list of media name or {@code NULL} if failed
     */
    public List<String> getMediaList() {
        if (!isInitialised())
            return null;

        List<String> list = null;
        InputStream in = null;
        try {
            URL url = new URL(String.format("http://%s:8080/media/all", baseUrl));
            URLConnection conn = url.openConnection();
            in = conn.getInputStream();
            StringBuilder sb = new StringBuilder();
            int i;
            while ((i = in.read()) != -1) {
                sb.append((char) i);
            }
            String json = sb.toString();
            list = objectMapper.readValue(json, List.class);
        } catch (Exception e) {
            Log.e("Error", Log.getStackTraceString(e));
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e1) {
                    Log.e("Error", Log.getStackTraceString(e1));
                }
            }
        }
        return list;
    }

    /**
     * Return the stream of a media
     *
     * @param filename
     * @return stream of a media
     */
    public InputStream getMedia(String filename) {
        return null;
    }
}
