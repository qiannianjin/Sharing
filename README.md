# Sharing
基于共享信息发布的网站

Sharing及SharingWeb运行tips:
1. 创建两个数据库并设置主主同步，在src/main/resources/application.properties中配置master和slave的用户名和密码
    master:负责写入操作 默认的数据源
    slave:负责读操作  负载主要的读取压力
2. 初始化数据
    执行src目录下的Sharing.sql, 会自动的创建数据库，表及数据, 默认的前台和后台用户名密码为：sandiegoe@126.com/shenzhaoquan
3. 安装依赖
    本项目依赖 FastDfs, EasyCaptcha, 需要首先将这两个依赖安装到本地maven仓库, 相关jar包可见src目录下的libs
4. 搭建FastDfs分布式文件存储系统
    本项目图片存储使用的是FastDfs，因此需要首先搭建FastDfs文件存储系统，在搭建完毕后，在src/main/resources/fdfs_client.conf中
    指定tracker_server的ip地址
5. 运行
   前台：打开Sharing/src/main/java/SharingApplication, 右击run
   后台: 打开SharingWeb项目，项目右击run as server
6. 浏览
   前台：http://localhost:9000
   后台：http://localhost:8080/SharingWeb/
7. 邮件功能
    邮件功能依赖于阿里的邮件服务，因此如果需要邮件功能，首先开通阿里的邮件服务功能，获取accessKeyId，accessKeySecret等，然后在src/main/resources/email.properties
    中配置相关信息
