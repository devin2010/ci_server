#!/bin/bash
java -jar -Xms32m -Xmx256m -XX:MaxMetaspaceSize=256m -XX:CompressedClassSpaceSize=16m -Xss512k -Xmn16m -XX:InitialCodeCacheSize=8m -XX:ReservedCodeCacheSize=256m -XX:MaxDirectMemorySize=256m  /opt/ci_server/ci-server-1.0.0.jar --spring.config.location=/opt/ci_server/application-prod.properties &
/bin/tini -- /usr/local/bin/jenkins.sh
