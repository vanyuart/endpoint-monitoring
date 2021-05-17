# Endpoint monitoring
Endpoint monitoring - interview task.
REST API provides an interface to store URLs for scheduled checks.
A scheduled task sends asynchronous request to stored URLs in configured intervals.
Results of the checks are then stored in one bulk.

## How to run
Following command builds maven project and initializes application instance with MySQL database.

`docker compose up --build`

## How to test
Import attached [Postman collection](Endpoint%20monitoring.postman_collection.json)

Authorization token is set in the collection's environment.
Two users are seeded in applicaiton.properties.
In order to change a user update Authorization in the collection's settings.

## Configurables

### Users
Two users are seeded from application properties

```
[
  {
    "username": "Applifting",
    "accessToken": "93f39e2f-80de-4033-99ee-249d92736a25"
  },
  {
  "username": "Batman",
  "accessToken": "dcb20f8a-5657-4f1b-9f7f-ce65739b359e"
  }
]
```

### EndpointCheckerScheduledTask
Task is scheduled to run every 10 seconds using crontab from application.properties

`endpointmonitoring.scheduled.endpointChecker.cron=0/10 * * * * *`

## Security
Spring Security was configured to check Authorization header for the presence of Bearer token with UUID (accessToken).
User is retrieved by the provided token and added to Security context.
