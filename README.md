### email-service

Reads input csv file (seperated with semicolon and double quotes) and sends email to the recipients in the file.

##### build and run
```bash
mvn clean install
mvn spring-boot:run
```

##### API to send email

specify the file path to the API below.  
e.g. if you have kept file under `/Users/user/Documents/EmailList.csv`

POST http://localhost:8091/email
with body
```json
{
  "filePath": "/Users/user/Documents/EmailList.csv"
}
```