FROM registry.cn-shenzhen.aliyuncs.com/devin/ci_server:1.0
MAINTAINER devin <devin@qq.com>

# mkdir
RUN mkdir -p /opt/ci_server /opt/ci_server/log

COPY ci-server-1.0.0.jar /opt/ci_server
COPY application-prod.properties /opt/ci_server
COPY ci_server.sh /usr/local/bin

RUN chmod -R 755 /opt/ci_server

ENTRYPOINT ["/bin/bash", "/usr/local/bin/ci_server.sh"]
EXPOSE 8181