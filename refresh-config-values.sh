#!/bin/bash
echo 'Refreshing config variables...'

curl -X POST http://localhost:8080/actuator/refresh
echo 'Done!'

echo 'Getting config variables...'
curl -X GET http://localhost:8994/config-client/development