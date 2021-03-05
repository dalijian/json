package com.lijian.r2dbc;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Event;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.EventsResultCallback;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.TimeUnit;

public class DockerEventTest extends R2dbcApplicationTests {
    @Test
    public void dockerEventToFlux() throws InterruptedException {
        collectDockerEvents().subscribe(System.out::println);   // 5
        TimeUnit.MINUTES.sleep(1);  // 6
    }

    private Flux<Event> collectDockerEvents() {
        DockerClient docker = DockerClientBuilder.getInstance().build();    // 1
        return Flux.create((FluxSink<Event> sink) -> {
            EventsResultCallback callback = new EventsResultCallback() {    // 2
                @Override
                public void onNext(Event event) {   // 3
                    sink.next(event);
                }
            };
            docker.eventsCmd().exec(callback);  // 4
        });
    }
}
