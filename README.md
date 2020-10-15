# beertech.bancobeer.transfer
1 Rodar o comando abaixo para subir o projeto (consumer, api, mysql e rabbitma)
docker-compose up

2 Acessar a url http://localhost:15672

3- Selecionar a fila conta-corrente.


Enviar o(s) payload(s):

{"tipo": "DEPOSITO", "valor": 1, "hash": 1}

{"tipo": "SAQUE", "valor": 1, "hash": 1}

{"tipo": "TRANSFERENCIA", "valor": 1, "origem": 1, "destino": 2}
