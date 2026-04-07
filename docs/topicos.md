# Tópicos

## Objetivo

Automatizar el análisis de seguridad de aplicaciones web combinando la navegación controlada de Selenium con el escaneo de vulnerabilidades de OWASP ZAP, garantizando que ambos procesos se completen antes de generar los reportes.

## Alcance

El proyecto cubre los siguientes escenarios de navegación sobre [demoblaze.com](https://www.demoblaze.com):

| Escenario | Descripción |
|---|---|
| Navegar al menú Laptops | Click en categoría Laptops y verificación de productos cargados |
| Navegar al menú Phones | Click en categoría Phones y verificación de productos cargados |
| Navegar al menú Monitors | Click en categoría Monitors y verificación de productos cargados |

## Flujo de ejecución

```
1. Conexión con OWASP ZAP API
2. Apertura de Chrome con proxy apuntando a ZAP
3. Navegación a la URL objetivo (ZAP registra la URL)
4. Inicio del Spider y Active Scan de ZAP
5. Ejecución de la navegación E2E (Selenium)
   ├── Click Laptops  → captura antes y después
   ├── Click Phones   → captura antes y después
   └── Click Monitors → captura antes y después
6. Espera hasta que Spider y Active Scan lleguen al 100%
7. Generación del reporte HTML de ZAP
8. Generación del reporte Allure
9. Cierre del navegador
```

## Condiciones de finalización

El informe de seguridad **solo se genera** cuando se cumplen **ambas condiciones**:

- ✅ **Condición 1**: La navegación E2E finalizó exitosamente.
- ✅ **Condición 2**: El escáner ZAP (Spider + Active Scan) llegó al 100%.
