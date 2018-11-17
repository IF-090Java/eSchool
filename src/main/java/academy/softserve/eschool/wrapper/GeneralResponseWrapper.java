package academy.softserve.eschool.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GeneralResponseWrapper<T> {
    private Status status;
    private T data;
}
