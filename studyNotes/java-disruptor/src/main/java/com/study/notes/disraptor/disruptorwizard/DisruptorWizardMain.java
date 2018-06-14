package com.study.notes.disraptor.disruptorwizard;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.study.notes.disraptor.LongEvent;
import com.study.notes.disraptor.Translator;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *    -->c1
 * p1        --> c3
 *    -->c2
 *
 * 这张图的意思就是消费者3消费时，必须保证消费者1和消费者2已经完成对该消息的消费。
 * 举个例子，在处理实际的业务逻辑（C3）之前，需要校验数据（C1），以及将数据写入磁盘（C2）。
 *
 */
public class DisruptorWizardMain {
    private static final Translator TRANSLATOR = new Translator();

    public static void main(String[] args) throws InterruptedException {
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger index = new AtomicInteger(1);

            public Thread newThread(Runnable r) {
                return new Thread(null, r, "disruptor-thread-" + index.getAndIncrement());
            }
        };

        int bufferSize = 1024;

        EventHandler handler1 = (EventHandler<LongEvent>) (event, sequence, endOfBatch) -> System.out.println("handler1 : " + event.getValue());
        EventHandler handler2 = (EventHandler<LongEvent>) (event, sequence, endOfBatch) -> System.out.println("handler2 : " + event.getValue());
        EventHandler handler3 = (EventHandler<LongEvent>) (LongEvent event, long sequence, boolean endOfBatch) -> System.out.println("handler3 : " + event.getValue() + " arrived. Handler1 and handler2 should have completed. Completed.\n");

        Disruptor<LongEvent> disruptor = new Disruptor(LongEvent :: new, bufferSize, threadFactory);
        disruptor.handleEventsWith(handler1, handler2).then(handler3);
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            ringBuffer.publishEvent(TRANSLATOR, bb);
            Thread.sleep(1000);
        }
    }
}
