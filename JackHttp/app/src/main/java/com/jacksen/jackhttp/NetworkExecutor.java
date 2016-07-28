package com.jacksen.jackhttp;

import java.util.concurrent.BlockingQueue;

/**
 * 网络请求执行者
 * Created by Admin on 2016/7/28.
 */

public class NetworkExecutor extends Thread {

    // 网络请求队列
    private BlockingQueue<Request<?>> requestQueue;

    // 网络请求栈
    private HttpStack httpStack;

    // 结果分发器
    private static ResponseDelivery responseDelivery = new ResponseDelivery();

    private static Cache<String, Response> cache = new LruMemoryCache();

    private boolean isStop = false;

    public NetworkExecutor(BlockingQueue<Request<?>> requests, HttpStack httpStack) {
        this.requestQueue = requests;
        this.httpStack = httpStack;
    }

    @Override
    public void run() {
        while (!isStop) {
            try {
                final Request<?> request = requestQueue.take();
                if (request.isCancel()) {
                    // xxx 被取消执行了
                    continue;
                }
                Response response = null;
                if (isUseCache(request)) {// 从缓存中读取
                    response = cache.get(request.getUrl());
                } else {
                    response = httpStack.performRequest(request);
                    // 如果请求需要缓存
                    if (request.isShouldCache() && isSuccess(response)) {
                        cache.put(request.getUrl(), response);
                    }
                }

                //
                responseDelivery.deliveryResponse(request, response);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param response
     * @return
     */
    private boolean isSuccess(Response response) {
        return response != null && response.getStatusCode() == 200;
    }

    /**
     * @param request
     * @return
     */
    private boolean isUseCache(Request<?> request) {
        return request.isShouldCache() && cache.get(request.getUrl()) != null;
    }

    /**
     *
     */
    public void quit() {
        isStop = true;
        interrupt();
    }
}
