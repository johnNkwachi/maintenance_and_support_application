package arnergy.solar.ticket;

import arnergy.solar.ticket.dto.ticketRequest;
import arnergy.solar.user.User;

import java.util.List;

public interface TicketService {
    List<Ticket> getAllTicket();
    Ticket getTicketById(String id);
    Ticket createTicket(Ticket ticket, String userId);
}
