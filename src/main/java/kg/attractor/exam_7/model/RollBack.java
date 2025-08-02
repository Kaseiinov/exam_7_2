package kg.attractor.exam_7.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RollBack {
    private Long id;
    private String fromAcc;
    private String toAcc;
    private Double amountMoney;
    private Boolean successful;
    private Boolean enabled;
}
