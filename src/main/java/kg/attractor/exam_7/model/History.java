package kg.attractor.exam_7.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class History {
    private Long id;
    private String fromAcc;
    private String toAcc;
    private Double amountMoney;
    private Boolean approved;
}
