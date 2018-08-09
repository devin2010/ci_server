
### 本服务为经过改造官方的Jenkins镜像而产生的新的集成服务镜像并存放于国内阿里云镜像库中.
一: 镜像:registry.cn-shenzhen.aliyuncs.com/devin/jenkins_dind:1.0
 ----
 ##### 说明:
 该镜像是基于管网jenkins:2.60.3镜像基础上加入docker依赖组件,以实现容器内操作docker命令.
 
 ##### 运行:
 docker run -d -u root --name ci_server \
 --privileged -p 8080:8080 -p 50000:50000 \
 -v /somedir/jenkins_home:/var/jenkins_home \
 -v /var/run/docker.sock:/var/run/docker.sock \
 -v /usr/bin/docker:/usr/bin/docker \
 registry.cn-shenzhen.aliyuncs.com/devin/jenkins_dind:1.0
 
 ##### 注释:
 **(1) 运行参数解释:**
   * --privileged  以root权限运行
   * -p -p 端口映射    eg:  宿主机端口:容器端口
   * -v 数据磁盘挂载  eg:宿主机磁盘:容器磁盘 /somedir/jenkins_home为需要宿主机挂载的Jenkins数据目录
   * -d 后台运行
   * -u root 以root账号运行容器
   
 **(2) 服务访问:**
   * jenkins访问:http://ip:8080 (第一次运行设置参考Jenkins运行设置)

二: 镜像:registry.cn-shenzhen.aliyuncs.com/devin/ci_server:1.0
----
##### 说明:
 该镜像是基于自制jenkins_dind:1.0镜像基础上加入相关集成环境,以完善Jenkins环境.

 ##### 运行:
docker run -d -u root --name ci_server \
 --privileged -p 8080:8080 -p 50000:50000 \
 -v /somedir/jenkins_home:/var/jenkins_home \
 -v /var/run/docker.sock:/var/run/docker.sock \
 -v /usr/bin/docker:/usr/bin/docker \
 registry.cn-shenzhen.aliyuncs.com/devin/ci_server:1.0
 
 ##### 注释:
 **(1) 运行参数解释:**
   * --privileged  以root权限运行
   * -p -p 端口映射    eg:  宿主机端口:容器端口
   * -v 数据磁盘挂载  eg:宿主机磁盘:容器磁盘 /somedir/jenkins_home为需要宿主机挂载的Jenkins数据目录
   * -d 后台运行
   * -u root 以root账号运行容器
     
 **(2) 本容器运行后包含环境以及路径:**
   * java 1.8.0_171  /usr/bin/java   默认jdk环境
   * java 1.7.0_79    /usr/share/java/jdk1.7.0_79/      备选jdk
   * maven 3.5.4    /usr/bin/mvn    默认maven环境    maven_home=/usr/share/maven
   * nodejs 8.11.3  /usr/bin/node   默认nodejs环境    node_home=/usr/share/nodejs
   * svn /usr/bin/svn                        默认svn环境 
   * git /usr/bin/git
   * docker  通过挂载的方式获取宿主机的docker执行命令
   
 **(3) 服务访问**
   * jenkins访问:http://ip:8080 (第一次运行设置参考Jenkins运行设置)
   
   
三: 镜像:registry.cn-shenzhen.aliyuncs.com/devin/ci_server:1.0.0
----
##### 说明:
 该镜像是基于自制ci_server:1.0镜像基数上加入Jenkins服务二次开发的接口,为上层应用提供接口服务.

 ##### 运行:
docker run -u root --name ci_server -e JENKINS_USER_NAME="admin" -e JENKINS_PWD="123456" --privileged -p 8080:8080 -p 50000:000 -p 8181:8181 -v /mnt/lcy/dk/jenkins_home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -v /usr/bin/docker:/usr/bin/docker registry.cn-shenzhen.aliyuncs.com/devin/ci_server:1.0.0
 
 ##### 注释:
 **(1) 运行参数解释:**
   * --privileged  以root权限运行
   * -p -p 端口映射    eg:  宿主机端口:容器端口
   * -v 数据磁盘挂载  eg:宿主机磁盘:容器磁盘 /somedir/jenkins_home为需要宿主机挂载的Jenkins数据目录
   * -d 后台运行
   * -u root 以root账号运行容器
   * -e 环境变量设置 JENKINS_USER_NAME:Jenkins账号 JENKINS_PWD:Jenkins账号密码
     
 
 **(2) 本容器运行后包含环境以及路径:**
   * java 1.8.0_171  /usr/bin/java   默认jdk环境
   * java 1.7.0_79    /usr/share/java/jdk1.7.0_79/      备选jdk
   * maven 3.5.4    /usr/bin/mvn    默认maven环境    maven_home=/usr/share/maven
   * nodejs 8.11.3  /usr/bin/node   默认nodejs环境    node_home=/usr/share/nodejs
   * svn /usr/bin/svn                        默认svn环境 
   * git /usr/bin/git
   * docker  通过挂载的方式获取宿主机的docker执行命令
   
 **(3) 服务访问**
   * jenkins访问:http://ip:8080 (第一次运行设置参考Jenkins运行设置)  
   * ci_server二次开发接口地址访问:http://ip:8181/swagger-ui.html
