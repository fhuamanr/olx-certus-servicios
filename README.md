# 🚀 Product Service - Microservicio

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-4.x-green)
![Docker](https://img.shields.io/badge/Docker-Ready-blue)
![Status](https://img.shields.io/badge/Status-Active-success)

---

## 📌 Descripción

Este proyecto corresponde al **primer microservicio** desarrollado por los alumnos de **Certus** para el curso:

> 🎓 **DISEÑO DE SOLUCIONES BASADAS EN SERVICIOS**

El microservicio **Product Service** permite gestionar productos mediante operaciones CRUD (Crear, Listar, Obtener, Actualizar y Eliminar), siguiendo principios de arquitectura de microservicios.

Este servicio es la base de una arquitectura distribuida que crecerá con nuevos módulos.

---

## 🧠 Arquitectura

```
[ Cliente (Postman / Frontend) ]
              |
              v
     [ Product Service API ]
              |
              v
         [ MySQL DB ]
```

---

## 🛠️ Stack Tecnológico

- ☕ Java 21
- 🌱 Spring Boot
- 🗄️ MySQL 8
- 🐳 Docker & Docker Compose
- 📦 Maven
- 🔗 REST API

---

## 📂 Estructura del Proyecto

```
src/main/java/pe/product/service
│
├── controller
├── entity
├── repository
├── services
└── ProductServiceApplication.java
```

---

## ⚙️ Funcionalidades

- Crear productos
- Listar productos
- Obtener producto por ID
- Actualizar producto
- Eliminar producto

---

## 🌐 Endpoints

| Método | Endpoint              |
|--------|----------------------|
| GET    | /products            |
| GET    | /products/{id}       |
| POST   | /products            |
| PUT    | /products/{id}       |
| DELETE | /products/{id}       |

---

## 🧪 Ejemplo

```json
{
  "name": "iPhone 15",
  "price": 1200.50
}
```

---

## 🚀 Ejecución del Proyecto

### 1. Clonar repositorio

```bash
git clone <URL>
cd product-service
```

### 2. Build

```bash
./mvnw clean package
```

Windows:

```bash
mvnw.cmd clean package
```

---

### 3. Levantar con Docker

```bash
docker-compose up --build
```

---

### 4. Acceso

```
http://localhost:8080/products
```

---

## 🐳 Contenedores

| Servicio | Puerto |
|----------|--------|
| MySQL    | 3307   |
| API      | 8080   |

---

## ⚠️ Requisitos

- Docker instalado
- Java 21
- RAM recomendada: 4GB+

---

## 🧠 Roadmap

- Validaciones avanzadas
- Manejo de errores global
- Swagger / OpenAPI
- Seguridad JWT
- Microservicios adicionales

---

## 👨‍💻 Autoría

Proyecto académico desarrollado por los los alumnos de Certus.

**Certus - Diseño de Soluciones Basadas en Servicios**

---

## 📄 Licencia

Uso académico.
