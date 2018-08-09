FROM registry.cn-shenzhen.aliyuncs.com/devin/jenkins_dind:1.0
MAINTAINER devin <devin@qq.com>

#install jdk-7u79-linux-x64.gz
COPY jdk-7u79-linux-x64.gz /usr/share/java/jdk-7u79-linux-x64.gz
RUN cd /usr/share/java/ \
  && tar zxvf jdk-7u79-linux-x64.gz \
  && rm -f jdk-7u79-linux-x64.gz 


#install nodejs
ARG NODEJS_URL=https://nodejs.org/dist/v8.11.3/node-v8.11.3-linux-x64.tar.xz
ARG NODEJS_VERSION=8.11.3
RUN mkdir -p /usr/share/nodejs /usr/share/nodejs/ref \
  && cd /usr/share/nodejs \
  && curl -fsSL -o /usr/share/nodejs/node-v8.11.3-linux-x64.tar.xz ${NODEJS_URL} \
  && xz -d ./node-v8.11.3-linux-x64.tar.xz \
  && tar -vxf ./node-v8.11.3-linux-x64.tar -C /usr/share/nodejs --strip-components=1 \
  && rm -f ./node-v8.11.3-linux-x64.tar.xz \
  && rm -f ./node-v8.11.3-linux-x64.tar \
  && ln -s /usr/share/nodejs/bin/node /usr/bin/node \
  && ln -s /usr/share/nodejs/bin/npm /usr/bin/npm
  

#install maven 
ARG MAVEN_VERSION=3.5.4
ARG USER_HOME_DIR="/root"
ARG SHA=ce50b1c91364cb77efe3776f756a6d92b76d9038b0a0782f7d53acf1e997a14d
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha256sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
#COPY mvn-entrypoint.sh /usr/local/bin/mvn-entrypoint.sh
#COPY settings-docker.xml /usr/share/maven/ref/
#ENTRYPOINT ["/usr/local/bin/mvn-entrypoint.sh"]
#CMD ["mvn"]
ENTRYPOINT ["/bin/tini", "--", "/usr/local/bin/jenkins.sh"]