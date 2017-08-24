This sample application shows several usefull features:

1. spring boot
2. spring security
2. spring data rest
3. MockMvc
4. mysql/h2 (dev/test) use

NB: main rest services feature could be seen through:
1. tests (RestControllerTest.java) no external db is needed
2. or using external mysql db; sql scripts could be found here:

/spring-boot-ex2/src/main/resources/static/db/ ...

1. mvn clean package
2. if using external db, set mysql and run db_structure.sql and insert_roles.sql
3. java -jar target\demo-0.0.1-SNAPSHOT.jar


Testing with curl

1.
curl -H "Content-Type: application/json"  -X POST -d '{"id":null,"email":"test2@test.com","password":"password2","name":"user2","lastName":"lastName2","active":1,"roles":["ADMIN"]}' http://localhost:8080/registration

2.
curl  http://localhost:8080/user

3.
curl -w "%{http_code}"  -H "Content-Type: application/json" -H "Authorization: Basic dGVzdDJAdGVzdC5jb206cGFzc3dvcmQy" -X POST -d '{"result":"process me, please"}' http://localhost:8080/process
