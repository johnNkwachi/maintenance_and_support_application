package arnergy.solar.ticket;

import arnergy.solar.exception.UserException;
import arnergy.solar.user.User;
import arnergy.solar.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TicketImpl implements TicketService{
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketImpl(TicketRepository ticketRepository, UserRepository userRepository){
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Ticket> getAllTicket() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicketById(String ticketId) {
        return ticketRepository.findByTicketId(ticketId).orElseThrow(() -> new TicketException("Could not find ticket"));
    }

    @Override
    public Ticket createTicket(Ticket ticket, String userId) {
        if (userId != null) { // Changed the condition to check if userId is NOT null
            var user = userRepository.findById(userId).orElseThrow(() -> new UserException("Could not find user"));
            ticket.setUser(user);
            TicketPriority priority = TicketPriority.MINOR;
            ticket.setPriority(priority);
            return ticketRepository.save(ticket);
        } else {
            throw new IllegalArgumentException("User ID Cannot be null"); // Throwing an exception when userId is null
        }
    }
}
