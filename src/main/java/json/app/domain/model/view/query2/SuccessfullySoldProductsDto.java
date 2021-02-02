package json.app.domain.model.view.query2;

import com.google.gson.annotations.Expose;
import json.app.domain.model.view.BaseUserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class SuccessfullySoldProductsDto {

    @Expose
    @ToString.Exclude
    private BaseUserDto user;

    @Expose
    @ToString.Exclude
    SoldProductsDto[] productsSold;
}
