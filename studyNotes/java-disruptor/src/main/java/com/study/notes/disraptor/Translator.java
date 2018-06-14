package com.study.notes.disraptor;

import com.lmax.disruptor.EventTranslatorOneArg;

import java.nio.ByteBuffer;

public class Translator implements EventTranslatorOneArg<LongEvent, ByteBuffer> {
    public void translateTo(LongEvent event, long sequence, ByteBuffer data) {
        event.set(data.getLong(0));
    }
}
