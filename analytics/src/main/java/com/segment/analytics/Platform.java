package com.segment.analytics;

import com.squareup.okhttp.OkHttpClient;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import retrofit.client.Client;
import retrofit.client.OkClient;

class Platform {
  private static final Platform PLATFORM = findPlatform();

  static Platform get() {
    return PLATFORM;
  }

  private static Platform findPlatform() {
    return new Platform();
  }

  Client defaultClient() {
    OkHttpClient client = new OkHttpClient();
    client.setConnectTimeout(15, TimeUnit.SECONDS);
    client.setReadTimeout(15, TimeUnit.SECONDS);
    client.setWriteTimeout(15, TimeUnit.SECONDS);
    return new OkClient(client);
  }

  ExecutorService defaultNetworkExecutor() {
    return Executors.newCachedThreadPool(defaultThreadFactory());
  }

  ThreadFactory defaultThreadFactory() {
    return Executors.defaultThreadFactory();
  }

  public long defaultFlushIntervalInMillis() {
    return 10 * 1000; // 10s
  }

  public int defaultFlushQueueSize() {
    return 250;
  }
}
