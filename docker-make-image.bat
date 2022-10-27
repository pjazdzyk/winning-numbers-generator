call mvnw clean package
docker stop winning-numbers
docker rm winning-numbers
docker build -t winning-numbers .
docker tag winning-numbers pjazdzyk/winning-numbers:latest