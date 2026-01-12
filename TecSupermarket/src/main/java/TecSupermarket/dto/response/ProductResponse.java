package TecSupermarket.dto.response;

public record ProductResponse(
        Long id,
        String name,
        String category,
        Double price,
        int stock
) {
}
