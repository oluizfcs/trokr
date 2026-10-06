#!/usr/bin/env bash
# Script para preparação de cenário didático da Aula 10 — Concorrência no Trokr.
set -euo pipefail

if ! command -v jq >/dev/null 2>&1; then
    echo "Erro: o utilitário 'jq' é obrigatório para extrair os IDs JSON, mas não foi encontrado."
    echo "Instale com: brew install jq (no Mac) ou apt install jq (no Linux)."
    exit 1
fi

BASE_URL=${BASE_URL:-"http://localhost:8090"}
SUFIXO=$(date +%s)$RANDOM

echo "==> 1. Criando usuários A, B e C..."
RESP_A=$(curl -s -f -X POST "$BASE_URL/usuarios" \
  -H "Content-Type: application/json" \
  -d "{\"nome\": \"Usuario A\", \"email\": \"usuarioA_${SUFIXO}@trokr.com\"}")
ID_A=$(echo "$RESP_A" | jq -r '.id')

RESP_B=$(curl -s -f -X POST "$BASE_URL/usuarios" \
  -H "Content-Type: application/json" \
  -d "{\"nome\": \"Usuario B\", \"email\": \"usuarioB_${SUFIXO}@trokr.com\"}")
ID_B=$(echo "$RESP_B" | jq -r '.id')

RESP_C=$(curl -s -f -X POST "$BASE_URL/usuarios" \
  -H "Content-Type: application/json" \
  -d "{\"nome\": \"Usuario C\", \"email\": \"usuarioC_${SUFIXO}@trokr.com\"}")
ID_C=$(echo "$RESP_C" | jq -r '.id')

echo "    Usuário A: id=$ID_A | Usuário B: id=$ID_B | Usuário C: id=$ID_C"

echo "==> 2. Criando itens com categorias diferentes..."
# A oferece PRODUTO (vale 1 créditos)
RESP_ITEM_A=$(curl -s -f -X POST "$BASE_URL/itens" \
  -H "Content-Type: application/json" \
  -d "{\"titulo\": \"Item A (Produto)\", \"descricao\": \"Oferecido por A\", \"usuarioId\": $ID_A, \"categoria\": \"PRODUTO\"}")
ITEM_A=$(echo "$RESP_ITEM_A" | jq -r '.id')

# B oferece SERVICO (vale 2 créditos)
RESP_ITEM_B=$(curl -s -f -X POST "$BASE_URL/itens" \
  -H "Content-Type: application/json" \
  -d "{\"titulo\": \"Item B (Serviço)\", \"descricao\": \"Oferecido por B\", \"usuarioId\": $ID_B, \"categoria\": \"SERVICO\"}")
ITEM_B=$(echo "$RESP_ITEM_B" | jq -r '.id')

# C oferece EXPERIENCIA (vale 3 créditos)
RESP_ITEM_C=$(curl -s -f -X POST "$BASE_URL/itens" \
  -H "Content-Type: application/json" \
  -d "{\"titulo\": \"Item C (Experiência)\", \"descricao\": \"Oferecido por C\", \"usuarioId\": $ID_C, \"categoria\": \"EXPERIENCIA\"}")
ITEM_C=$(echo "$RESP_ITEM_C" | jq -r '.id')

echo "    Item A (PRODUTO - 1 créditos): id=$ITEM_A"
echo "    Item B (SERVICO - 2 créditos): id=$ITEM_B"
echo "    Item C (EXPERIENCIA - 3 créditos): id=$ITEM_C"

echo "==> 3. Montando Negociação 1: Proposta Raiz de A aceita contraproposta de B..."
RESP_RAIZ_1=$(curl -s -f -X POST "$BASE_URL/propostas" \
  -H "Content-Type: application/json" \
  -d "{\"usuarioId\": $ID_A, \"itemId\": $ITEM_A, \"descricao\": \"Descrição item $ITEM_A\"}")
RAIZ_1=$(echo "$RESP_RAIZ_1" | jq -r '.id')

curl -s -f "$BASE_URL/propostas/$RAIZ_1/enviar" >/dev/null
curl -s -f "$BASE_URL/propostas/$RAIZ_1/aprovar" >/dev/null

RESP_CONTRA_1=$(curl -s -f -X POST "$BASE_URL/propostas" \
  -H "Content-Type: application/json" \
  -d "{\"usuarioId\": $ID_B, \"itemId\": $ITEM_B, \"propostaId\": \"$RAIZ_1\", \"descricao\": \"tanto faz\"}")
CONTRA_1=$(echo "$RESP_CONTRA_1" | jq -r '.id')

curl -s -f "$BASE_URL/propostas/$CONTRA_1/enviar" >/dev/null
curl -s -f "$BASE_URL/propostas/$RAIZ_1/selecionar/$CONTRA_1" >/dev/null

echo "    Negociação 1 pronta em NEGOCIADO: Raiz=$RAIZ_1 (Usuário A), Contraproposta=$CONTRA_1 (Usuário B)"

echo "==> 4. Montando Negociação 2: Proposta Raiz de C aceita contraproposta de A..."
RESP_RAIZ_2=$(curl -s -f -X POST "$BASE_URL/propostas" \
  -H "Content-Type: application/json" \
  -d "{\"usuarioId\": $ID_C, \"itemId\": $ITEM_C, \"descricao\": \"Descrição item $ITEM_C\"}")
RAIZ_2=$(echo "$RESP_RAIZ_2" | jq -r '.id')

curl -s -f "$BASE_URL/propostas/$RAIZ_2/enviar" >/dev/null
curl -s -f "$BASE_URL/propostas/$RAIZ_2/aprovar" >/dev/null

RESP_CONTRA_2=$(curl -s -f -X POST "$BASE_URL/propostas" \
  -H "Content-Type: application/json" \
  -d "{\"usuarioId\": $ID_A, \"itemId\": $ITEM_A, \"propostaId\": $RAIZ_2, \"descricao\": \"Descrição item $ITEM_A\"}")
CONTRA_2=$(echo "$RESP_CONTRA_2" | jq -r '.id')

curl -s -f "$BASE_URL/propostas/$CONTRA_2/enviar" >/dev/null
curl -s -f "$BASE_URL/propostas/$RAIZ_2/selecionar/$CONTRA_2" >/dev/null

echo "    Negociação 2 pronta em NEGOCIADO: Raiz=$RAIZ_2 (Usuário C), Contraproposta=$CONTRA_2 (Usuário A)"

echo ""
echo "================================================================="
echo "   CENÁRIO PRONTO PARA A DEMONSTRAÇÃO DE CONCORRÊNCIA"
echo "================================================================="
echo "Proposta Raiz 1 (Troca 1): ID = $RAIZ_1 (A oferece PRODUTO -> 1 créditos)"
echo "Proposta Raiz 2 (Troca 2): ID = $RAIZ_2 (A oferece PRODUTO -> 1 créditos)"
echo "Usuário A:                 ID = $ID_A (Saldo atual = 0 créditos)"
echo "Saldo esperado de A após a Corrida 2 (sem lost update): 2 créditos (1 + 1)"
echo ""
echo "Comandos para executar:"
echo "  Corrida 1 (finalizar mesma proposta 2x):"
echo "    ./corrida.sh $RAIZ_1"
echo ""
echo "  Corrida 2 (finalizar 2 propostas concorrentes com Usuário A):"
echo "    ./corrida.sh $RAIZ_1 $RAIZ_2"
echo "================================================================="
