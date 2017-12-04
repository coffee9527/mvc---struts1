# mvc---struts1
### 模仿struts1的思路自己实现一个mvc设计模式

### 关于MVC框架的一些介绍(才疏学浅，在网上搜了一些感觉还可以的材料，仅供参考)
  #### MVC框架
     MVC全名是Model View Controller，是模型(model)－视图(view)－控制器(controller)的缩写，一种软件设计典范，用一种业务逻辑、数据、界面显示分离的方法组织代码，将业务逻辑聚集到一个部件里面，在改进和个性化定制界面及用户交互的同时，不需要重新编写业务逻辑。MVC被独特的发展起来用于映射传统的输入、处理和输出功能在一个逻辑的图形化用户界面的结构中。
  #### MVC模型和MVC框架之间的区别
    https://www.cnblogs.com/understander/p/5552207.html  
  #### 架构（三层架构）、框架（MVC）、设计模式三者异同点
  http://blog.csdn.net/u010191034/article/details/24555161  
  #### 为什么MVC不是一种设计模式  
  https://www.cnblogs.com/aaronjs/p/3581904.html  
    
### 其他参考  
  由于Struts1和Spring MVC的入口是servlet，而Struts2的入口时Filter，这里有关于他们区别的参考  
  ##### Java过滤器与SpringMVC拦截器之间的关系与区别  
    http://blog.csdn.net/chenleixing/article/details/44573495  
  ##### servlet/filter/listener/interceptor区别与联系  
    https://www.cnblogs.com/doit8791/p/4209442.html
### 我的开发环境:    
  win7-64  
  jdk-1.8  
  eclipse-oxygen  
  tomcat-8.0  
  Servlet-3.0
  
### 项目简介  
  项目大致分为三个模块，分别为example,example1,struts1.  
  example中使用原始的servlet和jsp实现了注册和登陆功能，没有用到框架的思想。主要是想通过体会这两个功能，能从中找到他们的共性，进行mvc框架的抽取构思。  
  
  example2中使用了mvc的设计思想，不过是通过properties配置文件实现的  
  
  struts1和exaple2类似，配置文件是xml
  
  本框架中的mvc思想：ActionServlet是一个servlet,它充当Controller,在初始化时会加载xml配置文件，改配置文件中有请求路径，封装jsp页面参数的FormBean实现类路径和处理业务逻辑的Action实现类路径。ActionServlet中则通过获取请求路径在xml中查找FormBean和Action，进而通过反射对他们进行操作。
  
  总体来说只是一个思路的简单实现，有很多不严谨的地方，不要过于纠结
