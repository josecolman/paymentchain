#!/bin/bash
echo 'Building package...'
#./mvnw clean package -DskipTests -Pproduction
#businessdomain/product/mvnw clean package -DskipTests -Pproduction
cd /Users/jose/Documents/udemy/payment_chain/paymentchain || exit
cd ./businessdomain/product || exit
./mvnw clean package -DskipTests -Pproduction

cd ../transaction || exit
./mvnw clean package -DskipTests -Pproduction

cd ../customer || exit
./mvnw clean package -DskipTests -Pproduction

cd ../../infraestructuredomain || exit

cd ./adminserver || exit
./mvnw clean package -DskipTests -Pproduction

cd ../configserver || exit
./mvnw clean package -DskipTests -Pproduction

cd ../eurekaserver || exit
./mvnw clean package -DskipTests -Pproduction

cd ../gateway || exit
./mvnw clean package -DskipTests -Pproduction


#businessdomain/transaction/.mvnw clean package -DskipTests -Pproduction


#echo 'Building image...'
#docker build --platform linux/amd64 -t localhost:443/acciones-comerciales-service:latest .

#echo 'Pushing image...'
#docker push localhost:443/acciones-comerciales-service:latest

# sudo docker-compose pull
# sudo docker-compose up -d
