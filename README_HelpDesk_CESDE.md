# Help Desk CESDE

## Descripción
Sistema de Mesa de Ayuda desarrollado con Java, Spring Boot, PostgreSQL y JWT para la gestión de tickets de soporte. Permite registrar usuarios, autenticar mediante JWT, crear tickets, asignarlos automáticamente a agentes de soporte según la categoría, gestionar estados, registrar historial, generar notificaciones y enviar correos mediante Mailpit.

## Tecnologías
- Java 17+
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA / Hibernate
- PostgreSQL
- Swagger / OpenAPI
- Mailpit
- Maven
- Lombok

## Arquitectura
```text
presentation
├── controller
application
├── dto
├── service
│   └── impl
infrastructure
├── persistence
│   ├── entity
│   └── repository
├── security
│   ├── filter
│   ├── jwt
│   └── handler
domain
├── enums
└── exception
```

## Funcionalidades Implementadas

### Autenticación y Seguridad
- Registro de usuarios.
- Inicio de sesión con JWT.
- Passwords cifradas con BCrypt.
- Roles:
  - ROLE_USUARIO
  - ROLE_SOPORTE
  - ROLE_ADMIN
- Autorización mediante `@PreAuthorize`.

### Gestión de Categorías
- Crear categorías.
- Consultar categorías.
- Asignación de responsable de soporte por categoría.

### Gestión de Tickets
- Crear ticket.
- Asignación automática a soporte según categoría.
- Consultar tickets asignados al agente autenticado.
- Cambio de estados:
  - ABIERTO
  - EN_PROCESO
  - RESUELTO
  - CERRADO
  - CANCELADO

### Historial de Tickets
Registro de:
- Usuario que realizó el cambio.
- Campo modificado.
- Valor anterior.
- Valor nuevo.
- Fecha del cambio.

### Notificaciones
- Consultar notificaciones.
- Consultar cantidad de notificaciones no leídas.
- Marcar notificaciones como leídas.

### Correo Electrónico
- Envío de correo automático al agente asignado.
- Integración con Mailpit para ambiente de desarrollo.

## Configuración PostgreSQL
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/mesa_ayuda
    username: postgres
    password: ********
```

## Configuración Mailpit
```yaml
spring:
  mail:
    host: localhost
    port: 1025

    properties:
      mail:
        smtp:
          auth: false
          starttls:
            enable: false
```

Interfaz Mailpit:

```text
http://localhost:8025
```

## Principales Endpoints

### Auth
```http
POST /auth/register
POST /auth/login
```

### Categorías
```http
GET    /categorias
POST   /categorias
```

### Tickets
```http
POST   /tickets
GET    /tickets/asignados
PATCH  /tickets/{id}/iniciar
PATCH  /tickets/{id}/resolver
PATCH  /tickets/{id}/cerrar
GET    /tickets/{id}/historial
```

### Notificaciones
```http
GET    /notificaciones
GET    /notificaciones/no-leidas
PATCH  /notificaciones/{id}/leer
```

## Flujo de Negocio
```text
Usuario crea ticket
        ↓
Categoría seleccionada
        ↓
Asignación automática de soporte
        ↓
Creación de notificación
        ↓
Registro en historial
        ↓
Envío de correo al responsable
        ↓
Gestión del ticket por soporte
```

## Seguridad
- JWT Bearer Token.
- Endpoints protegidos por rol.
- Control de acceso por propietario de ticket.
- CustomAccessDeniedHandler para respuestas 403 consistentes.

## Próximas Mejoras
- Comentarios por ticket.
- Adjuntos.
- Dashboard de métricas.
- SLA y tiempos de atención.
- Escalamiento automático.
- Envío de correos HTML.
- WebSockets para notificaciones en tiempo real.

## Autor
Selva Natalia Benítez Peláez
Eusebio José Caballero Olivella

Proyecto académico CESDE - Help Desk.
