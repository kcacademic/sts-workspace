curl -X POST \
  http://localhost:8080/payments/paypal \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 248' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: abd42a6e-78f2-4ede-bed1-df80cac3cbfd,e6bd0924-3b02-4da2-a38d-2ca49aac18a2' \
  -H 'User-Agent: PostmanRuntime/7.19.0' \
  -H 'cache-control: no-cache' \
  -d '{
	"idempotencyKey":"12345",
	"userId":"user",
	"paymentProvider":"paypal",
	"paymentMethod":"paypal",
	"description":"This is a dummy transaction.",
	"intent":"sale",
	"subTotal":"5",
	"shipping":"1",
	"tax":"1",
	"total":"7",
	"currency":"USD"
}'