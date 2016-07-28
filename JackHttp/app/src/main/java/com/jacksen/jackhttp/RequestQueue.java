package com.jacksen.jackhttp;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请求队列
 * Created by Admin on 2016/7/28.
 */

public class RequestQueue {

    // 线程安全的请求队列
    private BlockingQueue<Request<?>> requestQueue = new PriorityBlockingQueue<Request<?>>();

    // 请求的序列生成器
    private AtomicInteger serialNumGenerator = new AtomicInteger(0);

    // 默认的线程数量
    public static int DEFAULT_CODE_NUM = Runtime.getRuntime().availableProcessors() + 1;

    //
    private int dispatchNum = DEFAULT_CODE_NUM;


    private NetworkExecutor[] dispatchers = null;

    // HTTP请求的执行者
    private HttpStack httpStack;

    /**
     * @param coreNum
     * @param httpStack
     */
    protected RequestQueue(int coreNum, HttpStack httpStack) {
        this.dispatchNum = coreNum;
        this.httpStack = httpStack != null ? httpStack : HttpStackFactory.createHttpStack();
    }

    /**
     *
     */
    private final void startNetworkExecutors() {
        this.dispatchers = new NetworkExecutor[dispatchNum];
        for (int i = 0; i < dispatchNum; i++) {
            dispatchers[i] = new NetworkExecutor(requestQueue, httpStack);
            dispatchers[i].start();
        }
    }

    /**
     * 启动NetworkExecutor
     */
    public void start() {
        stop();
        startNetworkExecutors();
    }

    /**
     * 停止NetworkExecutor
     */
    private void stop() {
        if (dispatchers != null && dispatchers.length > 0) {
            for (int i = 0; i < dispatchers.length; i++) {
                dispatchers[i].quit();
            }
        }
    }

    /**
     * @param request
     */
    public void addRequest(Request<?> request) {
        if (!requestQueue.contains(request)) {
            request.setSerialNum(this.generateSerialNumber());
            requestQueue.add(request);
        } else {
            Log.i("RequestQueue", "this request is already exists.");
        }
    }

    /**
     * 生成序列号
     *
     * @return
     */
    private int generateSerialNumber() {
        return serialNumGenerator.incrementAndGet();
    }

}
