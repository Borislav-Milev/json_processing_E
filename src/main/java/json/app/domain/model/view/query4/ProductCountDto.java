package json.app.domain.model.view.query4;

import com.google.gson.annotations.Expose;
import json.app.domain.model.view.ProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductCountDto {

    @Expose
    private int count;

    @Expose
    private List<ProductDto> products = new ArrayList<>();
}
