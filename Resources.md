# REST Resources

Base URL: ```http://localhost:8080/eventvisualizer```

REST entrypoint: /app

### User

### Notebook

### Event

## DETAIL  /detail<br>


| Request Type | Path    | Parameter     | Type    | Description                          |
|--------------|---------|---------------|---------|--------------------------------------|
| GET          | /{id}   | id            | integer | the detail ID                        |
| POST         | /add    | body <b>*</b> | object  | adds new event detail                |
| PUT          | /update | body<b>*</b>  | Object  | Make changes and updateevent details |
|  DELETE | /remove/{id} | id | integer | Deletes details for an event |


### GET /{id} 
Gets event detail by ID<br>
Content-Type: appllication JSON<br>

```http request
curl -X GET "http://localhost:8080/eventvisualizer/app/detail/123"

```
### POST 
adds new event details<br>
```http request
curl -X POST --location "http://localhost:8080/eventvisualizer/app/detail/add/9" \
    -H "Content-Type: application/json, text/plain, text/html" \
    -d '{
           "dateOfEvent": "2025-04-02",
           "startTime": "09:00:00",
           "endTime": "02:00:00"
           "description": "Out of town headliners with local support!"
        }'
```


Body example<br>
```json
{
  "id": eventId,
  "dateOfEvent": "2025-01-01",
  "startTime": "09:00:00",
  "endTime": "03:00:00",
  "description": "Out of town talent with local support."
}
```

### PUT
Updates changes made to details<br>

Body example:
```json
{
  "id": detailId,
  "dateOfEvent": "2025-01-01",
  "startTime": "08:00:00",
  "endTime": "05:00:00",
  "description": "Out of town talent with local support."
}
```

### POST /add 
creates a new event detail