package academy.softserve.eschool.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    private int code;
    private String message;

    public static Status of(HttpStatus status) {
        return new Status(status.value(), status.getReasonPhrase());
    }
}