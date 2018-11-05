package academy.softserve.eschool.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Status {
	private int code;
	private String message;
}