package arnergy.solar.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {
    @Autowired
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }



    @PostMapping("/{userId}")
    public ResponseEntity<?> createTicket(@RequestBody Ticket ticket,@PathVariable String userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("User ID is required.");
        }
        Ticket createdTicket = ticketService.createTicket(ticket, userId);
        return ResponseEntity.ok(createdTicket);
    }

    @GetMapping("/getAllTickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTicket();

        if (tickets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tickets);
    }



}
