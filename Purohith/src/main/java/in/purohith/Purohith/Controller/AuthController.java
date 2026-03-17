package in.purohith.Purohith.Controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import in.purohith.Purohith.Model.BookingModel;
import in.purohith.Purohith.Model.BookingRequest;
import in.purohith.Purohith.Model.LoginModel;
import in.purohith.Purohith.Model.LoginResponse;
import in.purohith.Purohith.Model.PoojatypeModel;
import in.purohith.Purohith.Model.PriestModel;
import in.purohith.Purohith.Model.RegistrationModel;
import in.purohith.Purohith.Repository.BookingRepository;
import in.purohith.Purohith.Repository.RegisterRepository;
import in.purohith.Purohith.Service.BookingService;
import in.purohith.Purohith.Service.PoojaService;
import in.purohith.Purohith.Service.PriestService;
import in.purohith.Purohith.Service.RegistrationService;
import in.purohith.Purohith.Util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private RegistrationService registerService;

    @PostMapping("/register")
    public String register(@RequestBody RegistrationModel user){

        return registerService.register(user);
    }
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private RegisterRepository userRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginModel login) {
        // Find user by username
        RegistrationModel user = userRepo.findByUsername(login.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Verify password
        if (passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            // Generate JWT token
            String token = jwtUtil.generateToken(user.getUsername());
            return new LoginResponse(token);
        } else {
            throw new RuntimeException("Invalid password");
        }
    }
    
    @RestController
    @RequestMapping("/api/poojas")
    public class PoojaController{
    	@Autowired
        private PoojaService service;

        @GetMapping
        public List<PoojatypeModel> getAll(){
            return service.getAllPoojas();
        }

        @GetMapping("/search")
        public List<PoojatypeModel> search(@RequestParam String name){
            return service.searchPooja(name);
        }

        @PostMapping
        public PoojatypeModel save(@RequestBody PoojatypeModel pooja){
            return service.addPooja(pooja);
        }
    	
    }
    
    @RestController
    @RequestMapping("/api/priests")
    public class PriestController {

        @Autowired
        private PriestService priestService;

        @PostMapping
        public PriestModel addPriest(@RequestBody PriestModel priest){
            return priestService.addPriest(priest);
        }

        @GetMapping
        public List<PriestModel> getAllPriests(){
            return priestService.getAllPriests();
        }

        @GetMapping("/{id}")
        public PriestModel getPriest(@PathVariable Long id){
            return priestService.getPriestById(id);
        }
    }
    
    @RestController
    @RequestMapping("/booking")
    public class BookingController {

        @Autowired
        private BookingService bookingService;

        @Autowired
        private BookingRepository bookingRepository;

        // Book priest only (no PayPal)
        @PostMapping("/book")
        public ResponseEntity<?> bookPriest(@RequestBody BookingRequest request) {
            try {
                // Booking only
                BookingModel booking = bookingService.bookPriest(
                    request.getUserId(),
                    request.getPriestAvailabilityId(),
                    request.getPoojaTypeId()
                );

                return ResponseEntity.ok(booking);

            } catch (Exception e) {
                Map<String, String> error = new HashMap<>();
                error.put("message", e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
        }

        // Get bookings by user
        @GetMapping("/user/{userId}")
        public ResponseEntity<List<BookingModel>> getUserBookings(@PathVariable Long userId) {
            return ResponseEntity.ok(bookingService.getUserBookings(userId));
        }

        // Cancel a booking
        @PutMapping("/cancel/{bookingId}")
        public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId) {
            try {
                String message = bookingService.cancelBooking(bookingId);
                return ResponseEntity.ok(message);
            } catch (Exception e) {
                Map<String, String> error = new HashMap<>();
                error.put("message", e.getMessage());
                return ResponseEntity.badRequest().body(error);
            }
        }

        // Auto-cancel expired bookings
        @Scheduled(fixedRate = 60000) // every 1 min
        public void autoCancelBookings() {
            List<BookingModel> expired = bookingRepository.findByEndTimeBeforeAndStatus(
                LocalDateTime.now(),
                "BOOKED"
            );

            for (BookingModel booking : expired) {
                booking.setStatus("CANCELLED");
                bookingRepository.save(booking);
            }
        }

        // =======================
        // Payment endpoints commented
        // =======================
        /*
        @PostMapping("/capture-payment")
        public ResponseEntity<?> capturePayment(@RequestParam String orderId) { ... }
        */
    }
     
   
}   