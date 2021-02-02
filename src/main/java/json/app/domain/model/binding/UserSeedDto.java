package json.app.domain.model.binding;

import com.google.gson.annotations.Expose;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSeedDto {

    @Expose
    private String firstName;

    @Expose
    @NonNull
    @NotNull(message = "User last name cannot be null.")
    @Length(min = 3, message = "User last name must be at least 3 characters long.")
    private String lastName;

    @Expose
    @PositiveOrZero(message = "Age cannot be negative.")
    private Short age;
}
