# Tests

## Prerrequisito: OWASP ZAP debe estar corriendo

Antes de ejecutar cualquier comando, abre OWASP ZAP y verifica:
- Proxy en `localhost:8050`
- API Key coincide con el configurado en `AnalisisFinalTest.java`

---

## Ejecutar los tests

```bash
mvn clean test
```

Este comando ejecuta todo el ciclo:
1. Limpia compilados anteriores
2. Compila el proyecto
3. Corre el test `AnalisisFinalTest`
4. Genera `target/allure-results/`
5. Genera el reporte Allure en `target/site/allure-maven-plugin/`

---

## Ver el reporte Allure en el navegador

```bash
mvn allure:serve
```

Abre automáticamente el reporte interactivo en el navegador con:
- Resultado del test (passed/failed/broken)
- Pasos detallados con `@Step`
- Capturas de pantalla antes y después de cada acción
- Información de severidad, story y descripción

---

## Reporte de seguridad ZAP

Al finalizar la ejecución se genera automáticamente en:

```
scan-results/SeleniumTest.html
```

Contiene:
- Vulnerabilidades detectadas clasificadas por riesgo (High / Medium / Low / Informational)
- Descripción de cada hallazgo
- URLs afectadas
- Recomendaciones de remediación

---

## Salida esperada en consola

```
[CONDICIÓN 1 ✅] Navegación completada.
Esperando que el spider ZAP finalice...
Progreso spider: 45%
Progreso spider: 100%
Spider finalizado.
Esperando que el escaneo activo ZAP finalice...
Progreso escaneo activo: 30%
...
Progreso escaneo activo: 100%
Escaneo activo finalizado.
[CONDICIÓN 2 ✅] Escaneo ZAP completado.
[✅ AMBAS CONDICIONES CUMPLIDAS] Generando informe...
```

---

## Duración estimada

| Fase | Tiempo aproximado |
|---|---|
| Setup + navegación E2E | 1 - 3 minutos |
| Spider ZAP | 2 - 5 minutos |
| Active Scan ZAP | 5 - 30 minutos |
| **Total** | **8 - 38 minutos** |

> La duración del Active Scan varía según la cantidad de endpoints que ZAP encuentre en el sitio.
