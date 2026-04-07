# Tecnologías

## Stack principal

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 17 | Lenguaje de programación |
| Maven | 3.x | Gestión de dependencias y ciclo de build |
| JUnit 4 | 4.13.2 | Framework de pruebas |
| Selenium | 4.13.0 | Automatización de navegador |
| OWASP ZAP | 2.15.0 | Escáner de seguridad y proxy de interceptación |
| ZAP Client API | 1.14.0 | Cliente Java para controlar ZAP por API REST |
| WebDriverManager | 5.9.2 | Gestión automática del ChromeDriver |
| Allure | 2.24.0 | Reportes de ejecución con capturas de pantalla |
| AspectJ | 1.9.19 | Instrumentación para Allure con JUnit 4 |
| Selenide | 6.19.1 | Utilidades adicionales para Selenium |
| SLF4J | 2.0.7 | Logging |
| Netty | 4.1.113 | Soporte de red para ZAP |

## Plugins Maven

| Plugin | Versión | Propósito |
|---|---|---|
| maven-surefire-plugin | 2.22.2 | Ejecución de tests con listener Allure registrado |
| allure-maven | 2.12.0 | Generación automática del reporte Allure tras los tests |
| serenity-maven-plugin | 4.0.16 | Integración con reportes Serenity (post-integration-test) |

## Patrón de diseño

El proyecto implementa el patrón **Page Object Model (POM)**:

```
BasePage          ← captura de pantalla con @Attachment Allure
  ├── LaptopsPage
  ├── PhonesPage
  └── MonitorsPage
```
