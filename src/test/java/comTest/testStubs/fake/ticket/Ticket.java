package comTest.testStubs.fake.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder=true)
public class Ticket {
    private int bookingId;
    private String name;
    private String bus;
    private String gender;
    private String timings;
}
