package Models.Communication;

import java.util.Calendar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SMS {

    private String message;
    private Calendar date;
}
