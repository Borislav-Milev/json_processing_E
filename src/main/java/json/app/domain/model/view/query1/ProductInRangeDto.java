package json.app.domain.model.view.query1;


import com.google.gson.annotations.Expose;
import json.app.domain.model.view.ProductDto;
import json.app.domain.model.view.BaseUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInRangeDto {

    @Expose
    private ProductDto product;

    @Expose
    private BaseUserDto seller;
}
