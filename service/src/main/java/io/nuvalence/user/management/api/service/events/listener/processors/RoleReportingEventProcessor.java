package io.nuvalence.user.management.api.service.events.listener.processors;

import io.nuvalence.events.event.RoleReportingEvent;
import io.nuvalence.events.exception.EventProcessingException;
import io.nuvalence.events.subscriber.EventProcessor;
import io.nuvalence.user.management.api.service.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for processing RoleReporting events.
 */
@Component
@RequiredArgsConstructor
public class RoleReportingEventProcessor implements EventProcessor<RoleReportingEvent> {

    private final ApplicationService applicationService;
    private RoleReportingEvent event;

    @Override
    public void setData(RoleReportingEvent event) {
        this.event = event;
    }

    @Override
    public RoleReportingEvent getData() {
        return null;
    }

    @Override
    public void execute() throws EventProcessingException {
        try {
            applicationService.setApplicationRoles(event.getName(), event.getRoles());
        } catch (Exception e) {
            throw new EventProcessingException(e);
        }
    }
}
