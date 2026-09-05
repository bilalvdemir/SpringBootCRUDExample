package com.bilald.crudsample.monitoring;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMetrics {

    private final MeterRegistry meterRegistry;

    public void incrementEmployeeCreated() {
        Counter.builder("employees.created")
                .description("Total number of employees created")
                .register(meterRegistry)
                .increment();
    }

    public void incrementEmployeeDeleted() {
        Counter.builder("employees.deleted")
                .description("Total number of employees deleted")
                .register(meterRegistry)
                .increment();
    }

    public void incrementEmployeeUpdated() {
        Counter.builder("employees.updated")
                .description("Total number of employees updated")
                .register(meterRegistry)
                .increment();
    }

    public Timer.Sample startTimer() {
        return Timer.start(meterRegistry);
    }

    public void recordEmployeeOperationDuration(Timer.Sample sample, String operation) {
        sample.stop(Timer.builder("employee.operation.duration")
                .tag("operation", operation)
                .description("Duration of employee operations")
                .register(meterRegistry));
    }
}
