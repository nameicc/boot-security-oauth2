> Spring Security OAuth2

1、授权码模式的认证流程

auth-server             认证服务器       8080端口

resource-server     资源服务器       8081端口

client-app                第三方应用       8082端口

在8082应用的index.html页面模拟第三方登录，去8080认证服务器认证，取得token后，去8081资源服务器访问接口资源
