## Steps to run
1. Build the project using
  `mvn clean install`
2. Run using `mvn spring-boot:run`
3. The web application is accessible via localhost:8080

## API
### Base URL Conference: /api/v1/conference
- POST /room/{roomId} (Attach room to conference)
- POST /{conferenceId}/add/{participantId} (Attach participant to conference)
- DELETE /{conferenceId}/cancel (Delete conference)
### Base URL Participant: /api/v1/participants
- POST "/" (Create new Participant)
- DELETE {participantId}/leave/{conferenceId} (Leave participant from conference)
### Base URL Room: /api/v1/room
- POST "/" (Create new Room)
- GET /{roomId} (Does room available for new participants)
