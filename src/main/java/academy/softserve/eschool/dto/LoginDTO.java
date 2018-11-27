package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @ApiModelProperty(notes = "Contains the user's login. This field's maximum length is 100 symbols " +
            "and minimum length - 5 symbols. It can't be blank.")
    private String login;

    @ApiModelProperty(notes = "Contains the user's password. This field's maximum length is 255 symbols. It can't be blank.")
    private String password;
}
