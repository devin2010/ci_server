
本服务为经过改造官方的Jenkins镜像而产生的新的集成服务镜像并存放于国内阿里云镜像库中.
先看镜像运行:
docker run -d -u root --name ci_server \
 --privileged -p 9090:8080 -p 50000:50000 \
 -v /mnt/lcy/dk/jenkins_home1:/var/jenkins_home \
 -v /var/run/docker.sock:/var/run/docker.sock \
 -v /usr/bin/docker:/usr/bin/docker \
 registry.cn-shenzhen.aliyuncs.com/devin/ci_server:1.0
 
 注释:
 (1) 运行参数解释:
     a. --privileged  以root权限运行
     b. -p -p 端口映射    eg:  宿主机端口:容器端口
     c. -v 数据磁盘挂载  eg:宿主机磁盘:容器磁盘
     d. -d 后台运行
     e. -u root 以root账号运行容器
     
 
 (2) 本容器运行后包含环境以及路径:
     a. java 1.8.0_171  /usr/bin/java   默认jdk环境
     b. java 1.7.0_79    /usr/share/java/jdk1.7.0_79/      备选jdk
     c. maven 3.5.4    /usr/bin/mvn    默认maven环境    maven_home=/usr/share/maven
     e. nodejs 8.11.3  /usr/bin/node   默认nodejs环境    node_home=/usr/share/nodejs
     f. svn /usr/bin/svn                        默认svn环境 
     g. git /usr/bin/git
     h. docker  通过挂载的方式获取宿主机的docker执行命令
