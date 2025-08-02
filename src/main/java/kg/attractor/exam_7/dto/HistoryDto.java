package kg.attractor.exam_7.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDto {
    private Long id;
    private String fromAcc;
    private String toAcc;
    private Double amountMoney;
    private Boolean approved;
}
