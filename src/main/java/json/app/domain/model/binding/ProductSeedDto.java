package json.app.domain.model.binding;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
public class ProductSeedDto {

    @Expose
    @NonNull
    @NotNull(message = "Product name cannot be null.")
    @Length(min = 3)
    private String name;

    @Expose
    @NonNull
    @NotNull(message = "Product price cannot be null.")
    @DecimalMin(value = "0.01", message = "Product price cannot be negative.")
    private BigDecimal price;
}
