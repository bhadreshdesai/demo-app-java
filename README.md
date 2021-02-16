# demo-app-java
Demo Java Application

curl https://start.spring.io/starter.tgz \
    -d type=gradle-project \
    -d language=java \
    -d javaVersion=11 \
    -d groupId=bdd.demo. \
    -d artifactId=app-java \
    -d description=Demo%20java%20application \
    -d dependencies=web,devtools,lombok,h2,data-jpa \
| tar -xzvf -