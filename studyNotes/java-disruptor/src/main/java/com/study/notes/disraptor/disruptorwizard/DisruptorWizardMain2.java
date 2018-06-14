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
 *    -->c1a-->c1b
 * p1              --> c3
 *    -->c2a-->c2b
 *    这张图的意思就是消费者1b消费时，必须保证消费者1a已经完成对该消息的消费；
 *    消费者2b消费时，必须保证消费者2a已经完成对该消息的消费；消费者c3消费时，
 *    必须保证消费者1b和2b已经完成对该消息的消费。
 */
public class DisruptorWizardMain2 {
    private static final Translator TRANSLATOR = new Translator();

    public static void main(String[] args) throws InterruptedException {
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger index = new AtomicInteger(1);

            public Thread newThread(Runnable r) {
                return new Thread(null, r, "disruptor-thread-" + index.getAndIncrement());
            }
        };

        int bufferSize = 1024;

        EventHandler c1a = (EventHandler<LongEvent>) (event, sequence, endOfBatch) -> System.out.println("c1a : " + event.getValue());
        EventHandler c1b = (EventHandler<LongEvent>) (event, sequence, endOfBatch) -> System.out.println("c1b : " + event.getValue() + " arrived. C1a should have completed. Completed.");

        EventHandler c2a = (EventHandler<LongEvent>) (event, sequence, endOfBatch) -> System.out.println("c2a : " + event.getValue());
        EventHandler c2b = (EventHandler<LongEvent>) (event, sequence, endOfBatch) -> System.out.println("c2b : " + event.getValue() + " arrived. C2a should have completed. Completed.");

        EventHandler lastHandler = (EventHandler<LongEvent>) (event, sequence, endOfBatch) -> System.out.println("lastHandler : " + event.getValue() + " arrived. C1a, C1b, H2a and H2b should have completed. Completed.\n");

        Disruptor<LongEvent> disruptor = new Disruptor(LongEvent::new, bufferSize, threadFactory);

        disruptor.handleEventsWith(c1a, c2a);
        disruptor.after(c1a).then(c1b);
        disruptor.after(c2a).then(c2b);
        disruptor.after(c1b, c2b).then(lastHandler);

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
