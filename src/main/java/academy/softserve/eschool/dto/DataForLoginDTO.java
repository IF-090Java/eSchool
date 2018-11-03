package academy.softserve.eschool.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("used to crate login")
public class DataForLoginDTO {
    @ApiModelProperty("contain first name")
    private String firstName;

    @ApiModelProperty("contain last name")
    private String lastName;

    @ApiModelProperty("contain patronymic")
    private String patronymic;

    @ApiModelProperty("contain login")
    private String login;

}
