# Despliegue en Coolify

## Configuración en Coolify

### 1. Crear el Proyecto

1. En Coolify, crea un nuevo proyecto
2. Selecciona "Docker Compose" como tipo de despliegue
3. Sube el archivo `docker-compose.coolify.yml` al repositorio o pégalo directamente

### 2. Variables de Entorno

Configura las siguientes variables de entorno en Coolify para cada servicio:

#### Variables Comunes (en todos los servicios)

| Variable | Valor por defecto | Descripción |
|----------|-------------------|-------------|
| `MYSQL_ROOT_PASSWORD` | `root` | Contraseña del root de MySQL |
| `MYSQL_USER` | `productuser` | Usuario MySQL para product-service |
| `MYSQL_PASSWORD` | `productpass` | Contraseña del usuario MySQL product-service |
| `RABBITMQ_USER` | `guest` | Usuario RabbitMQ |
| `RABBITMQ_PASSWORD` | `guest` | Contraseña RabbitMQ |

#### Variables Específicas por Servicio

**product-service:**
- `SPRING_DATASOURCE_URL`: `jdbc:mysql://mysql-product:3306/productdb`
- `SPRING_DATASOURCE_USERNAME`: `productuser`
- `SPRING_DATASOURCE_PASSWORD`: `productpass`
- `SPRING_RABBITMQ_HOST`: `rabbitmq`
- `SPRING_RABBITMQ_PORT`: `5672`
- `SPRING_RABBITMQ_USERNAME`: `guest`
- `SPRING_RABBITMQ_PASSWORD`: `guest`

**user-service:**
- `SPRING_DATASOURCE_URL`: `jdbc:mysql://mysql-user:3306/userdb`
- `SPRING_DATASOURCE_USERNAME`: `useruser`
- `SPRING_DATASOURCE_PASSWORD`: `userpass`
- `SPRING_RABBITMQ_HOST`: `rabbitmq`
- `SPRING_RABBITMQ_PORT`: `5672`
- `SPRING_RABBITMQ_USERNAME`: `guest`
- `SPRING_RABBITMQ_PASSWORD`: `guest`

**order-service:**
- `SPRING_DATASOURCE_URL`: `jdbc:mysql://mysql-order:3306/orderdb`
- `SPRING_DATASOURCE_USERNAME`: `orderuser`
- `SPRING_DATASOURCE_PASSWORD`: `orderpass`
- `SPRING_RABBITMQ_HOST`: `rabbitmq`
- `SPRING_RABBITMQ_PORT`: `5672`
- `SPRING_RABBITMQ_USERNAME`: `guest`
- `SPRING_RABBITMQ_PASSWORD`: `guest`

**frontend-service:**
- `PRODUCT_SERVICE_URL`: `http://product-service:8080`
- `USER_SERVICE_URL`: `http://user-service:8080`
- `ORDER_SERVICE_URL`: `http://order-service:8080`

### 3. Configuración de Redes

Coolify maneja automáticamente las redes Docker internas. No es necesario configurar redes manualmente.

### 4. Configuración de SSL

Coolify configura automáticamente SSL con Let's Encrypt para los servicios expuestos. El frontend-service será el servicio principal que tendrá el dominio público.

### 5. Healthchecks

Los servicios tienen healthchecks configurados:
- MySQL: 30s de start_period, 10s interval, 5 retries
- RabbitMQ: 30s de start_period, 10s interval, 5 retries
- Services: 60s de start_period, 30s interval, 3 retries

### 6. Volumes

Los volúmenes se gestionan automáticamente por Coolify:
- `mysql_product_data`
- `mysql_user_data`
- `mysql_order_data`
- `rabbitmq_data`

### 7. Acceso a los Servicios

Una vez desplegado, los servicios estarán disponibles en:
- **Frontend**: `https://tu-dominio.com` (configurado en Coolify)
- **Product Service**: `https://product-service.tu-dominio.com`
- **User Service**: `https://user-service.tu-dominio.com`
- **Order Service**: `https://order-service.tu-dominio.com`
- **RabbitMQ Management**: `https://rabbitmq.tu-dominio.com` (puerto 15672)

### 8. Notas Importantes

1. **Sin puertos**: El archivo `docker-compose.coolify.yml` NO tiene puertos expuestos, ya que Coolify maneja el networking internamente.
2. **Nombres de servicio**: Los servicios se comunican entre sí usando los nombres de servicio (ej: `product-service`, `user-service`).
3. **Build automático**: Coolify construirá las imágenes automáticamente desde los Dockerfiles.
4. **Restart policy**: Todos los servicios tienen `restart: unless-stopped` para alta disponibilidad.

## Estructura del Proyecto

```
olx-certus-servicios/
├── docker-compose.coolify.yml    # Docker Compose para Coolify
├── docker-compose.yml            # Docker Compose local
├── docker-compose.local.yml      # Docker Compose local con debug
├── frontend-service/             # Frontend Service
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/resources/
│       └── application.properties
├── product-service/              # Product Service
│   ├── Dockerfile
│   └── pom.xml
├── user-service/                 # User Service
│   ├── Dockerfile
│   └── pom.xml
└── order-service/                # Order Service
    ├── Dockerfile
    └── pom.xml