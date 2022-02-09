package pl.w65154.helpdesk.domain;

import lombok.Data;

@Data
public class Statistics {
    Long closedTickets;
    Long openTickets;
    Long newTickets;
}
