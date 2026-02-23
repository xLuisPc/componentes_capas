# SistemaClientes - Componente Facade

## Descripción
SistemaClientes es un componente que implementa el patrón Facade para gestionar la integración de múltiples subsistemas de una empresa. El componente encapsula tres subsistemas principales:

- **Subsistema A**: Sistema contable (ClaseA)
- **Subsistema B**: Sistema de mensajería (ClaseB)
- **Subsistema C**: Sistema de configuración (ClaseC)

Ahora empaquetado como componente JAR con gestión de dependencias mediante **Maven**.

## Estructura del Proyecto

```
SistemaClientes/
├── pom.xml                         ← Configuración Maven ✨
├── SistemaClientes.jar             ← Componente ejecutable (legacy)
├── Inicio/
│   └── InicioFacade.java           # Clase principal del aplicativo
├── patron/
│   └── facade/
│       ├── IFacade.java            # Interfaz de la fachada
│       ├── ImplementacionFachada.java
│       └── ImplementacionFachadaOtros.java
├── subsistema/
│   ├── A/
│   │   └── ClaseA.java             # Subsistema contable
│   ├── B/
│   │   └── ClaseB.java             # Subsistema de mensajería
│   └── C/
│       └── ClaseC.java             # Subsistema de configuración
├── target/                         ← Artefactos compilados (Maven)
│   ├── classes/
│   ├── sistemat-clientes-1.0.0.jar ← JAR ejecutable
│   └── SistemaClientes-1.0.0-uber.jar ← Uber JAR
├── META-INF/
│   └── MANIFEST.MF                 # Configuración del componente JAR
├── .vscode/
│   └── tasks.json                  # Tareas VS Code (Maven + Legacy)
├── build-maven.bat                 # Script de build Maven (Windows) ✨
├── build-maven.sh                  # Script de build Maven (Linux/Mac) ✨
├── compilar.bat                    # Script legacy (Windows)
├── compilar.sh                     # Script legacy (Linux/Mac)
├── README.md                       # Este archivo
├── GUIA_MAVEN.md                   # Guía completa de Maven ✨
├── ESPECIFICACION_TECNICA.md       # Especificación técnica
└── RESUMEN_CONVERSION.md           # Resumen de cambios
```

## Requisitos

- **Java**: JDK 11+ (configurado en pom.xml)
- **Maven**: 3.6+ (para construcción) - [Instalar](GUIA_MAVEN.md)
- **JRE**: 11+ (para ejecutar)

## 🚀 Compilación y Ejecución

### Opción 1: Con Maven (Recomendado) ✨

#### Windows
```bash
build-maven.bat package
build-maven.bat run
```

#### Linux/Mac
```bash
bash build-maven.sh package
bash build-maven.sh run
```

#### Comando directo
```bash
mvn clean package
java -jar target/sistemat-clientes-1.0.0.jar
```

### Opción 2: Con VS Code
Presiona `Ctrl+Shift+B` y selecciona:
- `Maven: Compilar` → Compila el proyecto
- `Maven: Package (JAR)` → Genera el JAR
- `Maven: Ejecutar JAR` → Ejecuta el JAR generado

### Opción 3: Sin Maven (Legacy)

```bash
# Windows
compilar.bat

# Linux/Mac
bash compilar.sh
```

Luego ejecutar:
```bash
java -jar SistemaClientes.jar
```

## 📦 Configuración Maven

### pom.xml - Elementos Principales

```xml
<groupId>com.miempresa</groupId>
<artifactId>sistemat-clientes</artifactId>
<version>1.0.0</version>
<packaging>jar</packaging>
```

**Características configuradas:**
- ✅ Compilador Java 11
- ✅ Empaquetado JAR ejecutable
- ✅ MANIFEST.MF automático
- ✅ Assembly plugin para Uber JAR
- ✅ Shade plugin (opcional)
- ✅ Surefire para pruebas (stub)

### Plugins Incluidos

| Plugin | Propósito |
|--------|-----------|
| maven-compiler-plugin | Compilación con Java 11 |
| maven-jar-plugin | Empaquetado JAR normal |
| maven-assembly-plugin | JAR con dependencias |
| maven-shade-plugin | Uber JAR (shade) |
| maven-javadoc-plugin | Generación de documentación |

## 📊 Comandos Maven Disponibles

### Build
```bash
mvn compile              # Solo compilar
mvn clean package       # Clean + compilar + empaquetar
mvn clean install       # Clean + compilar + empaquetar + instalar localmente
```

### Ejecución
```bash
java -jar target/sistemat-clientes-1.0.0.jar                    # JAR normal
java -jar target/SistemaClientes-1.0.0-uber.jar                # Uber JAR
```

### Otros
```bash
mvn clean               # Limpiar artefactos
mvn javadoc:javadoc    # Generar documentación
mvn help:describe       # Describir plugins
```

## 🔧 Tareas VS Code

En `.vscode/tasks.json` están disponibles:

### Tareas Maven
- **Maven: Limpiar** - Ejecuta `mvn clean`
- **Maven: Compilar** - Ejecuta `mvn compile` (default)
- **Maven: Package (JAR)** - Ejecuta `mvn package`
- **Maven: Clean Package** - Ejecuta `mvn clean package`
- **Maven: Ejecutar JAR** - Ejecuta el JAR de Maven
- **Maven: Ejecutar JAR (Uber)** - Ejecuta el Uber JAR

### Tareas Legacy (Sin Maven)
- **Compilar proyecto (Javac Legacy)**
- **Empaquetar en JAR (Legacy)**
- **Ejecutar desde JAR (Legacy)**
- **Ejecutar programa (Legacy)**
- **Limpiar binarios**

## 📂 Artefactos Generados

### Después de `mvn clean package`:

En el directorio `target/`:
- `sistemat-clientes-1.0.0.jar` - Componente JAR ejecutable
- `SistemaClientes-1.0.0-uber.jar` - Uber JAR (si se ejecuta maven-shade-plugin)
- `classes/` - Clases compiladas
- `test-classes/` - Clases de prueba compiladas
- `maven-archiver/` - Metadatos de Maven

## 🛠️ Instalar Maven

### Windows
```powershell
# Opción 1: Manual
# Descarga desde https://maven.apache.org/download.cgi
# Descomprime en C:\Apache\maven
# Agrega MAVEN_HOME al PATH

# Opción 2: Chocolatey
choco install maven

# Opción 3: Windows Package Manager
winget install Maven.Maven
```

### macOS
```bash
brew install maven
```

### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install maven
```

### Verificar instalación
```bash
mvn --version
```

## 💡 Características del Componente

✅ **Patrón Facade**: Interfaz unificada para múltiples subsistemas  
✅ **Empaquetado JAR**: Componente ejecutable portable  
✅ **Gestión Maven**: Construcción profesional y reproducible  
✅ **Versionado**: Versión semántica (1.0.0)  
✅ **Múltiples implementaciones**: ImplementacionFachada y ImplementacionFachadaOtros  
✅ **Documentación completa**: README, GUIA_MAVEN.md, ESPECIFICACION_TECNICA.md  

## 📖 Documentación

- [GUIA_MAVEN.md](GUIA_MAVEN.md) - Guía completa de Maven y configuración
- [ESPECIFICACION_TECNICA.md](ESPECIFICACION_TECNICA.md) - Especificación técnica detallada
- [RESUMEN_CONVERSION.md](RESUMEN_CONVERSION.md) - Resumen de la conversión a componente

## 🔄 Próximos Pasos

1. **Instalar Maven**: Sigue las instrucciones en [GUIA_MAVEN.md](GUIA_MAVEN.md)
2. **Compilar**: Usa `mvn clean package`
3. **Ejecutar**: Usa `java -jar target/sistemat-clientes-1.0.0.jar`
4. **Integrar**: Usa como dependencia en otros proyectos Maven

## ✨ Ventajas de la Configuración Maven

| Ventaja | Descripción |
|---------|-------------|
| **Reproducibilidad** | Mismo resultado en cualquier máquina |
| **Gestión de dependencias** | Maven descarga automáticamente |
| **Control de versiones** | Fácil mantener múltiples versiones |
| **Integración CI/CD** | Compatible con Jenkins, GitHub Actions, etc. |
| **Reutilización** | Publicar en Maven Central Repository |
| **Estandarización** | Sigue convenciones Maven |

## 📝 Notas

- El proyecto continúa funcionando sin Maven (usa compilación manual)
- Maven es **recomendado** para desarrollo profesional
- El `pom.xml` es compatible con IDEs (Eclipse, IntelliJ, VS Code)
- Usa `mvn clean package` para construcción limpia

## 🐛 Troubleshooting

| Problema | Solución |
|----------|----------|
| "mvn command not found" | Instala Maven y agrega a PATH |
| "Could not find goal" | Verifica que estés en el directorio con pom.xml |
| JAR no ejecuta | Usa: `java -jar target/sistemat-clientes-1.0.0.jar` |
| Errores de compilación | Verifica Java 11+: `java -version` |

---

*SistemaClientes Component v1.0.0 - 2026*
