package json.app.domain.model.view.query4;

import com.google.gson.annotations.Expose;
import json.app.domain.model.view.BaseUserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@NoArgsConstructor
public class UserDto{

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Integer age;

    @Expose
    @ToString.Exclude
    private ProductCountDto soldProducts;

}
