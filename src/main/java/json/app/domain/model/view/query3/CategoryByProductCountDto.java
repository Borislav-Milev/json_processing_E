package json.app.domain.model.view.query3;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryByProductCountDto {

    @Expose
    private String name;

    @Expose
    private Integer count;

    @Expose
    private Double averagePrice;

    @Expose
    private BigDecimal totalRevenue;
}
