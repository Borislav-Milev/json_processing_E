package json.app.domain.model.view.query2;

import com.google.gson.annotations.Expose;
import json.app.domain.model.view.ProductDto;
import json.app.domain.model.view.BaseUserDto;
import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoldProductsDto {

    @Expose
    @ToString.Exclude
    private ProductDto product;

    @Expose
    @ToString.Exclude
    private BaseUserDto buyer;
}
