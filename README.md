# fanfic-tracker

Aplicacion Jakarta EE para guardar fanfics de AO3, gestionarlos por usuario y consultar estadisticas basicas. Incluye:

- registro, login y logout
- importacion automatica desde AO3
- guardado manual como alternativa
- biblioteca personal con edicion y borrado
- panel de administracion para cuentas admin
- estadisticas a partir de 10 fanfics

## Estructura

- `src/main/java/com/ejemplo/controller`: endpoints HTTP
- `src/main/java/com/ejemplo/model`: entidades y acceso a datos
- `src/main/java/com/ejemplo/service`: scraping e inicializacion del esquema
- `src/main/java/com/ejemplo/util`: utilidades comunes
- `src/main/webapp`: frontend
- `mysql/init/01-bd1.sql`: base inicial para MySQL
