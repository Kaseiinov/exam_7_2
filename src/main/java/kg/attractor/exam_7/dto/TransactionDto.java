package kg.attractor.exam_7.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    @NotBlank
    private String fromAcc;
    @NotBlank
    private String toAcc;
    @NotNull
    private Double amount;
}
