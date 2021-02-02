package json.app.domain.model.binding;

import com.google.gson.annotations.Expose;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorySeedDto {

    @Expose
    @NonNull
    @NotNull(message = "Category name cannot be null.")
    @Length(min = 3, max = 15, message = "Category name must be between 3 and 15 characters long.")
    private String name;
}
