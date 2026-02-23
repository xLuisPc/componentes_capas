# Arquitectura Monolítica con Capas Lógicas

Aplicación monolítica construida con **componentes organizados en capas lógicas**, que resuelve dos problemas de dominio distintos (**Sistema de Clientes** y **Gestor de Documentos**), cada uno apoyándose en su propia base de datos.

---

## Diagrama de Arquitectura

```
                    ┌──────────────────────────────────────────────────────────────────┐
                    │                                                                  │
  ┌─────────┐      │  ┌──────────────┐                                                │
  │         │      │  │ VistaConsola │──┐    ┌────────────────────────────────────┐    │
  │ Inicio  │──────│  └──────────────┘  ├──►│          Controlador              │    │
  │         │      │  ┌──────────────┐  │    │   (depende de IFachada)           │    │
  └─────────┘      │  │VistaEscrit. │──┘    └───────┬───────────────┬───────────┘    │
                    │  └──────────────┘              │               │                │
                    │      Presentación              │            ○──┘ (interfaz)     │
                    │                     ┌──────────▼──────┐  ┌────▼──────┐          │
                    │                     │ LógicaNegocio   │  │  Fachada  │          │
                    │                     │ Casos de Uso    │  │           │          │
                    │                     │ Servicio        │  └─────┬─────┘          │
                    │                     └────────┬────────┘        │                │
                    │                              │          ┌──────┴──────┐          │
                    │                     ┌────────▼───┐      │             │          │
                    │                     │ Entidades  │  ┌───▼────┐ ┌─────▼─────┐    │
                    │                     └────────────┘  │Sistema │ │  Gestor   │    │
                    │                                     │Clientes│ │Documentos │    │
                    │                     ┌────────────┐  └───┬────┘ └─────┬─────┘    │
                    │                     │Persistencia│◄─────┘            │           │
                    │                     └──┬─────┬──┘◄───────────────────┘           │
                    │                        │     │                                   │
                    └────────────────────────│─────│───────────────────────────────────┘
                                             │     │
                    ┌────────────────────────────────────────────┐
                    │         DTO          (visible para todos)  │
                    │   Fábrica de Objetos (visible para todos)  │
                    └────────────────────────────────────────────┘
                                             │     │
                                        ┌────▼┐   ┌▼────┐
                                        │MySQL│   │ H2  │
                                        └─────┘   └─────┘
```

---

## Estructura del Proyecto (Maven Multi-Módulo)

Cada módulo genera su propio **JAR** y al final se empaquetan en un **fat-jar** ejecutable.

```
ComponentesConCapas/
├── pom.xml                          ← POM padre (define los 6 módulos)
├── docker-compose.yml               ← MySQL en Docker
├── init-db.sql                      ← Script SQL de inicialización
│
├── modulo-dto/                      ← Grupo 5: DTO
│   └── src/.../dto/
│       ├── ClienteDTO.java
│       └── DocumentoDTO.java
│
├── modulo-fabrica/                  ← Grupo 6: Fábrica de Objetos
│   └── src/.../fabrica/
│       └── FabricaDeObjetos.java        (Singleton + Factory Method + Builder)
│
├── modulo-persistencia/             ← Grupo 4: Entidades + Persistencia + Subsistemas
│   └── src/.../
│       ├── entidades/
│       │   ├── Cliente.java
│       │   └── Documento.java
│       ├── persistencia/
│       │   ├── IRepositorioCliente.java
│       │   └── IRepositorioDocumento.java
│       ├── sistemaclientes/             ← SistemaClientes → MySQL
│       │   ├── RegistroCliente.java
│       │   ├── MensajeriaCliente.java
│       │   ├── ConfiguracionCliente.java
│       │   └── RepositorioClienteMySQL.java
│       └── gestordocumentos/            ← GestorDocumentos → H2
│           ├── IDocumentoComponente.java
│           ├── DocumentoHtml.java
│           ├── DocumentoTexto.java
│           ├── ComponentePDF.java
│           ├── DocumentoPdfAdaptado.java
│           └── RepositorioDocumentoH2.java
│
├── modulo-negocio/                  ← Grupo 3: Controlador + Lógica de Negocio + Fachada
│   └── src/.../negocio/
│       ├── controlador/
│       │   └── Controlador.java
│       ├── fachada/
│       │   ├── IFachada.java            ← Interfaz (Controlador ↔ Fachada)
│       │   └── Fachada.java
│       └── servicio/
│           ├── ServicioClientes.java
│           └── ServicioDocumentos.java
│
├── modulo-presentacion/             ← Grupo 2: Vistas (Consola + Escritorio)
│   └── src/.../presentacion/
│       ├── consola/
│       │   └── VistaConsola.java
│       └── escritorio/
│           └── VistaEscritorio.java     (Swing)
│
└── modulo-inicio/                   ← Grupo 1: Punto de entrada (fat-jar)
    └── src/.../inicio/
        └── Inicio.java
```

---

## Requisitos Previos

- **Java 21** (JDK)
- **Apache Maven 3.8+**
- **Docker** (para la base de datos MySQL)

---

## Cómo Ejecutar

### 1. Levantar MySQL con Docker

```bash
docker-compose up -d
```

Esto crea un contenedor con:
- **Base de datos**: `sistema_clientes`
- **Usuario**: `app_user` / **Contraseña**: `app_password`
- **Puerto**: `3306`

### 2. Compilar y empaquetar

```bash
mvn clean package
```

Genera los JARs individuales de cada módulo y el fat-jar ejecutable `MonolitoCapas.jar`.

### 3. Ejecutar la aplicación

```bash
java -jar modulo-inicio/target/MonolitoCapas.jar
```

Se muestra un menú para elegir la interfaz:
- **Opción 1**: Vista de Consola (menú interactivo por terminal)
- **Opción 2**: Vista de Escritorio (ventana Swing)

### 4. Detener MySQL

```bash
docker-compose down
```

---

## Bases de Datos

| Dominio | Motor | Conexión | Descripción |
|---------|-------|----------|-------------|
| Sistema Clientes | **MySQL** | `localhost:3306/sistema_clientes` | Base de datos relacional en Docker |
| Gestor Documentos | **H2** | En memoria (`jdbc:h2:mem:gestor_documentos`) | Base de datos embebida, no requiere instalación |

---

## Patrones de Diseño Aplicados

### Fachada (Facade)
- **Clase**: `Fachada` implementa `IFachada`
- **Propósito**: Oculta la complejidad de los subsistemas (SistemaClientes y GestorDocumentos) y expone operaciones simplificadas al Controlador.
- El Controlador se conecta a la Fachada **exclusivamente por medio de la interfaz** `IFachada`.

### Factory Method
- **Clase**: `FabricaDeObjetos`
- **Propósito**: Centraliza la creación de DTOs (`ClienteDTO`, `DocumentoDTO`) para ambos dominios.
- También se aplica internamente en `ServicioDocumentos` para crear componentes de documento (HTML, Texto, PDF).

### Singleton
- **Clase**: `FabricaDeObjetos.getInstance()`
- **Propósito**: Garantiza una única instancia de la fábrica, accesible desde todas las capas.
- Implementación thread-safe con double-checked locking.

### Builder
- **Clases**: `FabricaDeObjetos.ClienteDTOBuilder`, `FabricaDeObjetos.DocumentoDTOBuilder`
- **Propósito**: Permite construir DTOs complejos paso a paso de forma legible.
- Ejemplo:
  ```java
  ClienteDTO dto = FabricaDeObjetos.getInstance()
      .builderClienteDTO()
      .conId(1)
      .conNombres("Juan")
      .conApellidos("Martinez")
      .conEmail("jmartinez@gmail.com")
      .conMensajeBienvenida("Bienvenido")
      .build();
  ```

### Adapter
- **Clase**: `DocumentoPdfAdaptado` adapta `ComponentePDF` a la interfaz `IDocumentoComponente`.
- **Propósito**: Permite que un componente PDF con interfaz incompatible se use a través de la interfaz estándar de documentos.

---

## Principios SOLID Aplicados

| Principio | Dónde se aplica |
|-----------|----------------|
| **S**ingle Responsibility | Cada clase tiene una única responsabilidad (ej: `ServicioClientes` solo lógica de clientes, `RepositorioClienteMySQL` solo persistencia MySQL) |
| **O**pen/Closed | Se pueden agregar nuevos tipos de documento o repositorios sin modificar los existentes |
| **L**iskov Substitution | `RepositorioClienteMySQL` y cualquier otra implementación de `IRepositorioCliente` son intercambiables |
| **I**nterface Segregation | Interfaces específicas: `IRepositorioCliente`, `IRepositorioDocumento`, `IFachada`, `IDocumentoComponente` |
| **D**ependency Inversion | El Controlador depende de `IFachada` (interfaz), no de `Fachada` (implementación). Los servicios dependen de interfaces de repositorio, no de MySQL/H2 directamente |

### Inyección de Dependencias

Se realiza **manualmente** en la clase `Inicio.java`:

```java
// 1. Crear repositorios concretos
IRepositorioCliente repoClientes = new RepositorioClienteMySQL();
IRepositorioDocumento repoDocumentos = new RepositorioDocumentoH2();

// 2. Inyectar en la Fachada
IFachada fachada = new Fachada(repoClientes, repoDocumentos);

// 3. Inyectar en el Controlador (solo conoce IFachada)
Controlador controlador = new Controlador(fachada);

// 4. Inyectar en la Vista
VistaConsola vista = new VistaConsola(controlador);
```

---

## JARs Generados

| JAR | Módulo | Contenido |
|-----|--------|-----------|
| `modulo-dto-1.0-SNAPSHOT.jar` | DTO | ClienteDTO, DocumentoDTO |
| `modulo-fabrica-1.0-SNAPSHOT.jar` | Fábrica de Objetos | FabricaDeObjetos (Singleton + Factory + Builder) |
| `modulo-persistencia-1.0-SNAPSHOT.jar` | Persistencia | Entidades, Repositorios, SistemaClientes, GestorDocumentos |
| `modulo-negocio-1.0-SNAPSHOT.jar` | Negocio | Controlador, Servicios, Fachada |
| `modulo-presentacion-1.0-SNAPSHOT.jar` | Presentación | VistaConsola, VistaEscritorio |
| `modulo-inicio-1.0-SNAPSHOT.jar` | Inicio | Punto de entrada |
| **`MonolitoCapas.jar`** | **Fat-JAR** | **Todos los módulos + dependencias (ejecutable)** |

---

## Tecnologías

- Java 21
- Maven (multi-módulo)
- JDBC (conexiones a base de datos)
- MySQL 8.0 (Docker)
- H2 Database (embebida en memoria)
- Swing (interfaz gráfica de escritorio)
