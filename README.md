# Avaliação - Sprint 4 - API Partidos
## Os endpoints para essa API serão os seguintes:
### Associado
- POST - /associados
- POST - /associados/partidos
- GET - /associados
- GET - /associados/{id}
- PUT - /associados/{id}
- DELETE - /associados/{id}
- DELETE - /associados/{id}/partidos/{id}
### Partido
- POST - /partidos
- GET - /partidos
- GET - /partidos/{id}
- GET - /partidos/{id}/associados
- PUT - /partidos/{id}
- DELETE

### Parametros

### Associado
- Filtrar por cargos politicos (GET)
``http://localhost:8080/associados/position=PRESIDENTE``
- Ordenar por nome (GET)
``http://localhost:8080/associados/sort=nome``
- Vincula um associado a um partido (POST)
``http://localhost:8080/associados/partidos``
Body
``{“idAssociado”: 
10, “idPartido”: 10}``
- Remove determinado associado daquele partido (DELETE)
- ``http://localhost:8080//associados/{id}/partidos/{id}``

### Partido
- Ordenar por nome (GET)
``http://localhost:8080/partidos/ideology=CENTRO``
- Listar todos os associados daquele partido (GET)
``http://localhost:8080/partidos/{id}/associados``
