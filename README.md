# demo-app-java
Demo Java Application

## Step 1
Create project using https://start.spring.io

```shell
curl https://start.spring.io/starter.tgz \
    -d type=gradle-project \
    -d language=java \
    -d javaVersion=11 \
    -d groupId=bdd.demo. \
    -d artifactId=app-java \
    -d description=Demo%20java%20application \
    -d dependencies=web,devtools,lombok,h2,data-jpa \
| tar -xzvf -
```

## Step 2
Create `TestAuthApi` class.

Implemente `testRegisterSuccess` test

## Step 3

https://springdoc.org/

"java.jdt.ls.vmargs": "-XX:+UseParallelGC -XX:GCTimeRatio=4 -XX:AdaptiveSizePolicyWeight=90 -Dsun.zip.disableMemoryMapping=true -Xmx1G -Xms100m"
"java.jdt.ls.vmargs": "-XX:+UseParallelGC -XX:GCTimeRatio=4 -XX:AdaptiveSizePolicyWeight=90 -Dsun.zip.disableMemoryMapping=true -Xmx1G -Xms100m -javaagent:\"/tmp/vscode-extensions/gabrielbb.vscode-lombok@1.0.1/extension/server/lombok.jar\""
