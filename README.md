# Endpoint monitoring
Endpoint monitoring - interview task.
REST API provides an interface to store URLs for scheduled checks.
Results of the checks are stored and might be retrieved later.
Scheduled task sends asynchronous request to stored URLs in configured intervals.

## How to run
`docker compose up --build`

## How to test
Import attached [Postman collection](Endpoint%20monitoring.postman_collection.json)

Authorization token is set in the collection environment.
Two users are seeded in applicaiton.properties.
In order to change a user update Authorization in collection settings.

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
Task is scheduled to run every 10 seconds in application.properties

`0/10 * * * * *`

## Security
Spring Security was configured to check Authorization header for the presence of Bearer token with UUID.
User is retrieved by the provided token and added to Security context.
