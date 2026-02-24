# ComponentesConCapas

Aplicación Java con arquitectura de componentes por capas lógicas.  
Incluye tres dominios independientes, dos interfaces gráficas y dos motores de base de datos.

---

## Dominios

| Dominio | Base de datos | Acceso |
|---------|--------------|--------|
| Sistema Clientes | MySQL (Docker) | Vía Fachada + interfaces |
| Gestor Documentos | H2 embebida | Vía Fachada + interfaces |
| Gestión Inventario | MySQL (Docker) | Directo (acoplado) |

---

## Módulos Maven

```
ComponentesConCapas/
├── DTO/               → DTOs transversales (PersonaDTO, ProductoDTO, DocumentoDTO)
├── FabricaObjetos/    → Fábrica de DTOs (Singleton + Factory Method)
├── Aplicaciones/      → SistemaClientes, GestorDocumentos, GestionInventario
├── CapaMediana/       → Controlador, LogicaNegocio, IFachada, Fachada
├── Presentacion/      → VistaConsola + VistaEscritorio (Swing)
├── Inicio/            → Punto de entrada (main)
└── Ejecutable/        → Fat JAR (Maven Shade)
```

---

## Patrones de diseño aplicados

- **Singleton** — FabricaDTO, PersonaDAOImpl, ProductoDAOImpl, PermisosImpl
- **Factory Method** — FabricaDTO, GestorDocumentosImpl
- **Builder** — PersonaDTO, ProductoDTO, DocumentoDTO, Persona, Producto
- **Facade** — Fachada (capa media), SistemaClientesFachada (interno)
- **Adapter** — DocumentoPdfAdaptado adapta ComponentePDF a la interfaz Documento
- **Dependency Injection** — por constructor en todas las capas

---

## Requisitos

- Java 11+
- Maven 3.6+
- Docker + Docker Compose

---

## Ejecución

**1. Levantar la base de datos MySQL:**
```bash
docker-compose up -d
```

**2. Compilar:**
```bash
mvn clean package
```

**3. Ejecutar:**
```bash
java -jar Ejecutable/target/ComponentesConCapas-ejecutable.jar
```

Al iniciar, elegir entre:
- `1` → Vista Consola
- `2` → Vista Escritorio (Swing)
