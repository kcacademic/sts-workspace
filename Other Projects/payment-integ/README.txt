curl -X POST \
  http://localhost:8080/payments/paypal \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Authorization: Bearer 9e97aff7-d211-42ae-b59a-1f9defa869fc' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 247' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: ca60b28a-3c95-41eb-88c2-6c9766179cbd,c9a2f3e7-ce25-4ba0-8e85-5311a4abbc3e' \
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