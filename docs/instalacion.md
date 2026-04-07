# Instalación

## 1. Instalar Java 17

### macOS (Homebrew)
```bash
brew install openjdk@17
```

### Windows
Descarga el instalador desde [adoptium.net](https://adoptium.net/) y sigue el asistente.

Verificar instalación:
```bash
java -version
```

---

## 2. Instalar Maven

### macOS (Homebrew)
```bash
brew install maven
```

### Windows
Descarga Maven desde [maven.apache.org](https://maven.apache.org/download.cgi), descomprime y agrega `bin/` al `PATH`.

Verificar instalación:
```bash
mvn -version
```

---

## 3. Instalar Google Chrome

Descarga desde [google.com/chrome](https://www.google.com/chrome/). WebDriverManager descargará automáticamente el ChromeDriver compatible.

---

## 4. Instalar OWASP ZAP 2.15.0

Descarga desde [zaproxy.org](https://www.zaproxy.org/download/).

### Configuración obligatoria tras la instalación:

**Puerto del proxy:**
```
Tools → Options → Local Proxies → Port: 8050
```

**Habilitar la API:**
```
Tools → Options → API → Enable API: ✅
```

**Anotar el API Key:**
```
Tools → Options → API → API Key: <copiar este valor>
```

Actualizar el API Key en el archivo de test:
```java
// src/test/java/org/demo/selenium_zap_pom/test/AnalisisFinalTest.java
private static final String ZAP_API_KEY = "<tu-api-key>";
```
