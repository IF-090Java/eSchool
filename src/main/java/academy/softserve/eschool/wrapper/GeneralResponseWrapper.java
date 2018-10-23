package academy.softserve.eschool.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralResponseWrapper<T> {
    private Status status;
    private T data;
}
