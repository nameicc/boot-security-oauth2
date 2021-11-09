> Spring Security OAuth2

1、授权码模式的认证流程

auth-server             认证服务器       8080端口

resource-server     资源服务器       8081端口

client-app                第三方应用       8082端口

在8082应用的index.html页面模拟第三方登录，去8080认证服务器认证，取得token后，去8081资源服务器访问接口资源

2、token存入Redis

3、客户端(第三方应用)信息存入数据库

4、客户端(第三方应用)封装token操作

5、OAuth2 结合 JWT，无状态登录
