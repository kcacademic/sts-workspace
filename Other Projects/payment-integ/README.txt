CONFIG SERVER
=============
cd apps\e2e-application\cloud\config-server
mvn spring-boot:run

KAFKA
=====
Apps\kafka_2.11-2.1.0\bin\windows\zookeeper-server-start.bat Apps\kafka_2.11-2.1.0\config\zookeeper.properties
Apps\kafka_2.11-2.1.0\bin\windows\kafka-server-start.bat Apps\kafka_2.11-2.1.0\config\server.properties
Apps\kafka_2.11-2.1.0\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic coc_payments

CASSANDRA
=========
cassandra
cqlsh -u cassandra -p cassandra -k coc_payments

OAUTH MOCK
==========
cd Apps\wiremock-standalone-2.25.1
java -jar wiremock-standalone-2.25.1.jar --port 9999

PAYMENT SERVICE
===============
cd C:\Users\kumchand0\Apps\sts-workspace\Other Projects\payment-integ
mvn spring-boot:run

TEST SERVICE
============
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