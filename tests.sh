BASE=${BASE:-"http://localhost:8090"}

curl $BASE/hello;
curl -X POST $BASE/usuarios -H "Content-Type: application/json" -d '{"nome": "Luiz", "email": "luiz@email.com"}'
curl -X POST $BASE/usuarios -H "Content-Type: application/json" -d '{"nome": "Felipe", "email": "felipe@email.com"}'

curl -X POST $BASE/itens -H "Content-Type: application/json" -d '{"titulo": "Item do Luiz", "descricao": "item A", "usuarioId": 1, "categoria": "PRODUTO"}'
curl -X POST $BASE/itens -H "Content-Type: application/json" -d '{"titulo": "Item do Felipe", "descricao": "item B", "usuarioId": 2, "categoria": "SERVICO"}'

curl -X POST $BASE/propostas -H "Content-Type: application/json" -d '{"usuarioId": 1, "descricao": "proposta", "itemId": 1}'
curl -X POST $BASE/propostas -H "Content-Type: application/json" -d '{"usuarioId": 2, "descricao": "contraproposta", "itemId": 2, "propostaId": 1}'

curl -X POST $BASE/propostas/1/enviar -H "Content-Type: application/json"
curl -X POST $BASE/propostas/1/aprovar -H "Content-Type: application/json"
curl -X POST $BASE/propostas/2/enviar -H "Content-Type: application/json"
# curl -X POST $BASE/propostas/1/selecionar/2 -H "Content-Type: application/json"
# curl -X POST $BASE/propostas/1/confirmar -H "Content-Type: application/json"
