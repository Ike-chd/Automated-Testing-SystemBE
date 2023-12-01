    package Models.SuspensionRequest;

import Models.Users.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuspensionRequest {
    private int ssId;
    private Student student;
    private int requestId;
    private int duration;
    private int confirmId;
    private String reason;
    private boolean active;
    private LocalDateTime dateInitiated;
}
