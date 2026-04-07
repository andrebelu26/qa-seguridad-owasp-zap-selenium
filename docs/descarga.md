# Descarga

## Clonar el repositorio

```bash
git clone https://github.com/pragma/<nombre-del-repo>.git
cd qa-seguridad-owasp-zap-selenium
```

## Descargar dependencias Maven

```bash
mvn dependency:resolve
```

Esto descargará automáticamente todas las dependencias declaradas en `pom.xml`, incluyendo:
- Selenium y ChromeDriver
- OWASP ZAP Client API
- Allure y AspectJ
- JUnit 4

## Verificar que el proyecto compila

```bash
mvn clean compile
```

La salida esperada:
```
[INFO] BUILD SUCCESS
```

## Estructura descargada

```
qa-seguridad-owasp-zap-selenium/
├── src/
│   └── test/java/org/demo/selenium_zap_pom/
│       ├── helpers/
│       ├── pages/
│       └── test/
├── docs/
├── pom.xml
├── catalog-info.yaml
├── mkdocs.yaml
├── .gitignore
└── README.md
```

> Los directorios `target/`, `scan-results/` y `.allure/` son generados durante la ejecución y están excluidos del repositorio por `.gitignore`.
