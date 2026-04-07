# QA Seguridad OWASP ZAP + Selenium

Proyecto de automatización de pruebas de seguridad desarrollado en **Pragma**, que integra **OWASP ZAP** como proxy de interceptación y escáner de vulnerabilidades con **Selenium** para la navegación automatizada sobre aplicaciones web.

## ¿Qué hace este proyecto?

Ejecuta pruebas de navegación E2E con Selenium sobre [demoblaze.com](https://www.demoblaze.com) mientras OWASP ZAP intercepta todo el tráfico HTTP/HTTPS en segundo plano, realizando:

- **Análisis pasivo**: inspección del tráfico interceptado sin enviar peticiones adicionales.
- **Spider**: rastreo automático de URLs y recursos del sitio.
- **Análisis activo**: envío de peticiones maliciosas controladas para detectar vulnerabilidades como XSS, SQL Injection, entre otras.

Al finalizar ambas condiciones (navegación E2E completada + escáner ZAP finalizado), se generan automáticamente los reportes de resultados.

## Reportes generados

| Reporte | Ruta | Descripción |
|---|---|---|
| Seguridad ZAP | `scan-results/SeleniumTest.html` | Vulnerabilidades detectadas por OWASP ZAP |
| Ejecución Allure | `target/site/allure-maven-plugin/` | Pasos, capturas de pantalla y resultados del test |
