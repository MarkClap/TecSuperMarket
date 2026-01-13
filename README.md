# 🛒 Marketplace API - Sistema de Ventas
Esta es una API REST robusta para un sistema de ventas de productos entre usuarios, desarrollada con Java 17 y el ecosistema de Spring Boot. El sistema permite la gestión de productos, autenticación de usuarios y procesos de compra, asegurando la integridad de los datos y la seguridad mediante tokens.

# 🚀 Tecnologías Utilizadas
- Backend: Java 17, Spring Boot 3.

- Seguridad: Spring Security

- Persistencia: Spring Data JPA, Hibernate

- Base de Datos: PostgreSQL

- Documentación: Swagger / OpenAPI 2.8

- Gestión de Dependencias: Maven

# 🔐 Arquitectura de Seguridad
- El sistema implementa un flujo de autenticación basado en JWT (Stateless).

- El usuario envía sus credenciales al endpoint /auth/login.

- El servidor valida y devuelve un Token JWT.

- El cliente incluye el token en el header Authorization: Bearer <token> para peticiones protegidas.

- Spring Security valida el token en cada petición antes de permitir el acceso a los recursos.

- Cada recurso de la API está protegido por roles lo que fomenta una mayor seguridad

# 🛠️ Configuración

- JDK 17 o superior.
  
- Maven.
