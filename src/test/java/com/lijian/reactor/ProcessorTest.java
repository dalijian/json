package com.lijian.reactor;

import org.junit.Test;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.UnicastProcessor;

public class ProcessorTest {

    @Test
    public void processorTest(){
        UnicastProcessor<Integer> processor = UnicastProcessor.create();
        FluxSink<Integer> sink = processor.sink(FluxSink.OverflowStrategy.ERROR);
        sink.next(1).onRequest(value -> System.out.println(value));
    }

}
