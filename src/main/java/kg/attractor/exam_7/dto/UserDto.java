package kg.attractor.exam_7.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 12, max = 12)
    private String phone;

    @NotBlank
    @Size(min = 4, max = 24, message = "Length must be >= 4 and <= 24")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{4,24}$", message = "Should contain at least one uppercase letter and one lowercase letter and at least one number")
    private String password;
}
