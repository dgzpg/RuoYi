##  系统简介
本系统主要模仿公司管理员管理公司的部门，岗位，角色和员工的系统。系统管理员拥有本系统最大的权限，可以针对部门、岗位、角色和用户进行管理操作。不同的用户拥有不同的权限，不同的权限可以进行不同的操作，最低权限的用户只能进行查询操作，稍高一点的可以对用户进行增删改等操作，再高一点的可以针对岗位进行增删改操作，然后再高的可以针对部门进行增删改操作，只有系统管理员可以为用户修改用户的角色。

##  系统项目模块图
![img](https://github.com/pgoup/BlogSystem/blob/master/%E5%9B%BE%E7%89%87/ruoyi1.png)

##  系统的项目设计图
![img](https://github.com/pgoup/BlogSystem/blob/master/%E5%9B%BE%E7%89%87/ruoyi2.png)

##  系统的数据库设计图
![img](https://github.com/pgoup/BlogSystem/blob/master/%E5%9B%BE%E7%89%87/ruoyi3.jpg)

##  系统的主要技术
前端：html、js、css

后端： spring boot  、jpa、spring  session +spring security+redis
其中，spring boot主要使用了Thymeleaf模板，另外还添加了spring boot的热部署，不需要每次修改后运行项目都要手动的重启项目。
jpa主要负责实体对象的持久化，完成系统model层的操作，包括实体的增删改查等，这样就可以进行面向对象编程。
另外，系统的session主要是运用了spring session联合spring security和redis来管理，spring security监控session的状态，当session过期的时候跳转到指定的请求，而session是保存到redis中的。

##  在编码阶段遇到的坑：
