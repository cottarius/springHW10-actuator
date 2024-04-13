package ru.cotarius.springcourse.actuator.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyMetric {

    private final AtomicInteger testNumber;
    private final Counter testCounter;

    @Autowired
    public MyMetric(MeterRegistry meterRegistry) {
        testNumber = meterRegistry.gauge("my_custom_gauge", new AtomicInteger());
        testCounter = meterRegistry.counter("my_custom_counter");
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 0)
    public void schedulingTask() {
        int random = (int) (Math.random()*100);
        testNumber.set(random);
        testCounter.increment();
    }
}
