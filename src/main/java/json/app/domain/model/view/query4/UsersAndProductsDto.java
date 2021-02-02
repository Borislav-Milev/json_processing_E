package json.app.domain.model.view.query4;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersAndProductsDto {

    @Expose
    private Integer userCount;

    @Expose
    private List<UserDto> users = new ArrayList<>();
}
