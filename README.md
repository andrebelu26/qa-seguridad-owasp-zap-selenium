# qa-seguridad-owasp-zap-selenium

Proyecto de automatización de pruebas de seguridad desarrollado en **Pragma**, que integra **OWASP ZAP** como proxy de interceptación y escáner de vulnerabilidades con **Selenium** para la navegación automatizada sobre aplicaciones web.

> Toda la documentación detallada del repositorio se encuentra en la carpeta [`/docs`](./docs).

---

## Descripción general

Este proyecto permite ejecutar pruebas E2E con Selenium mientras OWASP ZAP intercepta el tráfico HTTP/HTTPS, realizando análisis pasivo y activo de seguridad de forma simultánea. Al finalizar, genera dos reportes:

- **Reporte de seguridad** (ZAP): `scan-results/SeleniumTest.html`
- **Reporte de ejecución** (Allure): `target/site/allure-maven-plugin/index.html`

---

## Estructura del repositorio

```
qa-seguridad-owasp-zap-selenium/
├── src/test/java/org/demo/selenium_zap_pom/
│   ├── helpers/
│   │   ├── clientapiow.java     # Configuración del cliente ZAP API
│   │   ├── locators.java        # Selectores de elementos web
│   │   └── reporter.java        # Utilidad de generación de reportes ZAP
│   ├── pages/
│   │   ├── BasePage.java        # Clase base con captura de pantalla Allure
│   │   ├── LaptopsPage.java     # Page Object del menú Laptops
│   │   ├── PhonesPage.java      # Page Object del menú Phones
│   │   └── MonitorsPage.java    # Page Object del menú Monitors
│   └── test/
│       └── AnalisisFinalTest.java  # Clase principal del test
├── docs/                        # Documentación detallada del proyecto
├── pom.xml                      # Configuración Maven y dependencias
├── catalog-info.yaml            # Metadata para plataforma de ingeniería
└── mkdocs.yaml                  # Configuración de navegación de docs
```

---

## Documentación

| Documento | Descripción |
|---|---|
| [Tópicos](./docs/topicos.md) | Objetivo y alcance del proyecto |
| [Tecnologías](./docs/tecnologias.md) | Stack tecnológico utilizado |
| [Consideraciones](./docs/consideraciones.md) | Requisitos previos y restricciones |
| [Instalación](./docs/instalacion.md) | Pasos para configurar el entorno |
| [Descarga](./docs/descarga.md) | Cómo clonar y preparar el proyecto |
| [Tests](./docs/tests.md) | Cómo ejecutar las pruebas y ver reportes |
