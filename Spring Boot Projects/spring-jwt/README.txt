Start Application
=================
mvn spring-boot:run

Get JWT Token
=============
Request: curl -X POST http://localhost:8080/authenticate -H "content-type:application/json" -d "{\"username\":\"kumar\",\"password\":\"password\"}"

Response: {"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrdW1hciIsImV4cCI6MTU2MzgwNDA0OSwiaWF0IjoxNTYzNzg2MDQ5fQ.jHOSnUir0BfBaSa4eXqV9XvSr2pz4kI_rCCeFWFB_qpmny8bCUGF1AHu1eB_rh1sEpUHLYD39titDNNICdJFtQ"}

Use JWT Token
=============
Request: curl -X GET http://localhost:8080/hello -H "authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrdW1hciIsImV4cCI6MTU2MzgwNDA0OSwiaWF0IjoxNTYzNzg2MDQ5fQ.jHOSnUir0BfBaSa4eXqV9XvSr2pz4kI_rCCeFWFB_qpmny8bCUGF1AHu1eB_rh1sEpUHLYD39titDNNICdJFtQ"

Response: Kumar Chandrakant