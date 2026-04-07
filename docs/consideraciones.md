# Consideraciones

## Requisitos previos

Antes de ejecutar el proyecto es obligatorio tener instalado y configurado lo siguiente:

### 1. Java 17
```bash
java -version
# Debe mostrar: openjdk version "17.x.x"
```

### 2. Maven 3.x
```bash
mvn -version
# Debe mostrar: Apache Maven 3.x.x
```

### 3. Google Chrome
El proyecto usa **ChromeDriver** gestionado automáticamente por WebDriverManager. Chrome debe estar instalado en el sistema.

### 4. OWASP ZAP 2.15.0
OWASP ZAP debe estar **abierto y corriendo** antes de ejecutar los tests con la siguiente configuración:

| Parámetro | Valor |
|---|---|
| Host del proxy | `localhost` |
| Puerto del proxy | `8050` |
| API Key | Configurado en `AnalisisFinalTest.java` |

#### Configurar el puerto en ZAP:
`Tools → Options → Local Proxies → Port: 8050`

#### Obtener/verificar el API Key:
`Tools → Options → API → API Key`

> ⚠️ Si el API Key de ZAP no coincide con el configurado en `ZAP_API_KEY` del test, la conexión será rechazada.

## Restricciones conocidas

- El test está diseñado para ejecutarse en **modo local**. No está configurado para ejecución en CI/CD headless sin ajustes adicionales en ChromeOptions.
- Chrome versión 146+ genera un warning de CDP (`Unable to find CDP implementation matching 146`). Es solo un aviso y **no afecta la ejecución**.
- La duración del test depende del tiempo que ZAP tarde en completar el Active Scan, lo cual varía según la complejidad del sitio (puede tomar entre 5 y 30 minutos).
- Demoblaze.com es una **SPA** (Single Page Application), por lo que las esperas usan `WebDriverWait` con `visibilityOfElementLocated` en lugar de `stalenessOf`.
