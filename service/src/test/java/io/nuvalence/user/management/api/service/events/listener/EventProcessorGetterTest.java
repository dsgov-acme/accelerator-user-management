package io.nuvalence.user.management.api.service.events.listener;

import io.nuvalence.events.event.Event;
import io.nuvalence.events.event.EventMetadata;
import io.nuvalence.events.event.RoleReportingEvent;
import io.nuvalence.events.exception.EventProcessingException;
import io.nuvalence.events.subscriber.EventProcessor;
import io.nuvalence.user.management.api.service.events.listener.processors.RoleReportingEventProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

@ExtendWith(MockitoExtension.class)
public class EventProcessorGetterTest {
    @InjectMocks private EventProcessorRepository eventProcessorGetter;

    @Mock private ApplicationContext applicationContext;

    @Test
    public void testGetEventProcessor() throws EventProcessingException {
        RoleReportingEvent event = new RoleReportingEvent();
        event.setMetadata(new EventMetadata());
        event.getMetadata().setType("roleReporting");

        Mockito.when(applicationContext.getBean(RoleReportingEventProcessor.class))
                .thenReturn(new RoleReportingEventProcessor(null));

        EventProcessor result = eventProcessorGetter.get(event);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(RoleReportingEventProcessor.class, result.getClass());
    }

    @Test()
    public void testGetEventProcessorWithInvalidEventType() {
        Event event = new RoleReportingEvent();
        event.setMetadata(new EventMetadata());
        event.getMetadata().setType("invalidEventType");

        Assertions.assertThrows(
                EventProcessingException.class,
                () -> {
                    eventProcessorGetter.get(event);
                });
    }
}
