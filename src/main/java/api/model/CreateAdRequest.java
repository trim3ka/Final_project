package api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdRequest {
    private String name;
    private String category;
    private String condition;
    private String city;
    private String description;
    private int price;
}
