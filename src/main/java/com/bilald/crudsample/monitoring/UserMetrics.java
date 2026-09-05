package com.bilald.crudsample.monitoring;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMetrics {

    private final MeterRegistry meterRegistry;

    public void incrementUserCreated() {
        Counter.builder("users.created")
                .description("Total number of users created")
                .register(meterRegistry)
                .increment();
    }

    public void incrementUserDeleted() {
        Counter.builder("users.deleted")
                .description("Total number of users deleted")
                .register(meterRegistry)
                .increment();
    }

    public void incrementUserUpdated() {
        Counter.builder("users.updated")
                .description("Total number of users updated")
                .register(meterRegistry)
                .increment();
    }

    public Timer.Sample startTimer() {
        return Timer.start(meterRegistry);
    }

    public void recordUserOperationDuration(Timer.Sample sample, String operation) {
        sample.stop(Timer.builder("user.operation.duration")
                .tag("operation", operation)
                .description("Duration of user operations")
                .register(meterRegistry));
    }
}
