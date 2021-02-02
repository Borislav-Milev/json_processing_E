package json.app.domain.model.view;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
public class BaseUserDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;
}
