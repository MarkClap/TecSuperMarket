package TecSupermarket.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfficeDTO {
    private Long id;
    private String name;
    private String direction;
}
