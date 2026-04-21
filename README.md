# Codigo exacto para el AO3 Fanfic Tracker

Esta carpeta contiene los archivos completos que tendrias que usar como reemplazo base dentro de tu proyecto:

- `pom.xml`
- `mysql/init/01-bd1.sql`
- `src/main/java/com/ejemplo/model/ConexionBD.java`
- `src/main/java/com/ejemplo/model/Fanfic.java`
- `src/main/java/com/ejemplo/model/TagCount.java`
- `src/main/java/com/ejemplo/model/FanficDAO.java`
- `src/main/java/com/ejemplo/model/StatsDAO.java`
- `src/main/java/com/ejemplo/service/Ao3ScraperService.java`
- `src/main/java/com/ejemplo/controller/ImportarFanficServlet.java`
- `src/main/java/com/ejemplo/controller/ListarFanficsServlet.java`
- `src/main/java/com/ejemplo/controller/EstadisticasServlet.java`
- `src/main/webapp/WEB-INF/web.xml`
- `src/main/webapp/index.html`
- `src/main/webapp/js/app.js`
- `src/main/webapp/css/estilos.css`

## Como aplicarlo al proyecto base

1. Reemplaza los archivos antiguos de contactos por los nuevos.
2. Crea la carpeta `service` dentro de `src/main/java/com/ejemplo/`.
3. Borra o deja de usar:
   - `Contacto.java`
   - `ContactoDAO.java`
   - `BuscarContactosServlet.java`
4. Sustituye el SQL de `mysql/init/01-bd1.sql`.
5. Reconstruye con Docker Compose.

## Nota importante

El scraper de AO3 esta hecho con selectores estables de la pagina publica de works, pero no he podido verificarlo en vivo desde este entorno porque no tengo acceso de red aqui. Si AO3 cambia alguna clase HTML, habria que ajustar `Ao3ScraperService.java`.
