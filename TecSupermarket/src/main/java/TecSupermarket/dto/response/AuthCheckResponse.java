package TecSupermarket.dto.response;

public record AuthCheckResponse(
    String email,
    String role
) {}
