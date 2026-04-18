# Order Service - Ejemplos CURL

## Crear un pedido
```bash
curl -X POST http://localhost:8081/orders \
  -H "Content-Type: application/json" \
  -d '{
    "productId": 1,
    "sellerId": 100,
    "buyerId": 200
  }'
```

## Obtener pedido por ID
```bash
curl http://localhost:8081/orders/1
```

## Obtener pedidos de un comprador
```bash
curl http://localhost:8081/orders/buyer/200
```

## Obtener pedidos de un vendedor
```bash
curl http://localhost:8081/orders/seller/100
```

## Obtener mensajes de un pedido
```bash
curl http://localhost:8081/orders/1/messages
```

## Enviar mensaje en un pedido
```bash
curl -X POST http://localhost:8081/orders/1/messages \
  -H "Content-Type: application/json" \
  -d '{
    "authorId": 200,
    "content": "Hola, estoy interesado en el producto"
  }'
```

## Actualizar estado del pedido
```bash
curl -X PUT "http://localhost:8081/orders/1/status?status=COMPLETED"