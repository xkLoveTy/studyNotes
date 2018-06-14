package com.study.notes.disraptor;

import com.lmax.disruptor.WorkHandler;

public class LongWorkerHandler implements WorkHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent) throws Exception {
        System.out.println(Thread.currentThread().getName() + "消费者消费了消息：" + longEvent.toString());
    }
}
