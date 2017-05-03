*** 学生信息管理系统
该项目是个人兴趣前后端项目，前端负责获取数据渲染页面，后端负责处理数据接口。
前端基于Angular开发，后端基于Hibernate+Struts2

部署步骤：
    1.数据库部分
        版本：MySQL - 5.5.37
        数据库SQL文件：Table.sql(根目录下)
        任意MySQL图形化工具下，执行该文件即可，一般可以直接将文件拖到窗口内，然后执行即可。

        --表
            数据库内有用户表和学生表
            -用户表，存储管理系统用户相关信息（用户名、密码）
                可以用来登录管理系统
            -学生表，存储学生相关信息
                用户管理系统增删改查操作


        * 注意：数据库名、表名请勿修改，
                如果数据库名存在冲突，可修改Table.sql文件.

        ************ 若无须修改数据库名，该部分请自行忽略 ************

        ----Table.sql(16~18行)
            修改前：
            CREATE DATABASE /*!32312 IF NOT EXISTS*/`design` /*!40100 DEFAULT CHARACTER SET utf8 */;

            USE `design`;
            修改后：
            CREATE DATABASE /*!32312 IF NOT EXISTS*/`newDesign` /*!40100 DEFAULT CHARACTER SET utf8 */;

            USE `newDesign`;
        ----
        第一步：找到以上内容，将其中的`design` => 新的数据库名，注意符号`(    上句点，键盘左上角Esc键，垂直往下一位的键)

        第二步：打开"后端-Java"文件夹->"Design"->"src"->"hibernate.cfg.xml"
                打开hibernate配置文件，找到如下一行：
            修改前：
                <property name="hibernate.connection.url">jdbc:mysql://127.0.0.1/design</property>

            修改后：
                <property name="hibernate.connection.url">jdbc:mysql://127.0.0.1/newDesign</property>

            * 其中"newDesign"为新的数据库名，用于hibernate连接数据库

        ************ 如果不修改数据库名，该部分可以忽略 ************

    2.后端项目部分(以Eclipse为例)
        前端对接的后端域端口为8888，而非默认的8080端口
    
        1) 导入项目
            打开Eclipse，菜单栏选择File(文件) > Import(导入) > General(常规) > 双击 Existing Projects into Workspace(将现有项目导入到工作空间) > 找到 Select root directory一栏 > 点击 Browse(浏览) > 弹出对话框， 找到项目文件夹Design，单击选中 > 点击对话框中的 "确定" 按钮 > 点击 Finish(完成) > 完成项目导入

        2) 部署到服务器
            部署在Tomcat7.0上时，遇到不支持Java 1.8的项目
            将项目Project facet Java version 1.8 降为1.6可行
                目前项目已降级为Java 1.6
            
            Eclipse下，右击导入后的项目，选择Run As(运行方式) > 选择Run on Server (在服务器上运行)
            弹出服务器配置窗口
                有以下两项可选：
                    1.Manually define a new server (选择一个现有的服务器)
                        建议选择Tomcat7.0以上部署即可，7.0以下版本未测试过。
        
                    2.Manually define a new server (手动定义新的服务器)
                        选中Tomcat v7.0 Server以上选项 > 点击Next > 
                        Tomcat installation directory 一栏， 点击Browse > 
                        找到Tomcat安装目录选中，单击对话框 "确定" 按钮 > 

                        JRE: 尽量选择下拉菜单的最新版本 > 点击Finish (完成)
                         > Add and Remove对话框中，再次点击Finish完成部署
         
        此时若服务器启动毫秒数等信息显示在控制台，表示部署成功。
        若部署失败请及时联系。

    3.前端项目部分
        less编译后的项目文件，原有less依然保留。

        1) 安装 nodeJs 环境
            打开"安装包"文件夹 > 找到node-v6.10.0-x64.msi文件，双击运行 >
            一路默认next next > finish完成

        2) 启动 node 服务器
            Ctrl + R 打开运行窗口 > 输入cmd > 打开终端窗口 > 输入node -v
            输出 v6.10.0 表示node安装成功
            
            若输出'node -v' 不是内部或外部命令，也不是可运行的程序
            或批处理文件。 表示node 安装失败，请及时联系。

            * 注意: node安装前打开的终端无效，安装成功后打开的终端才能正常显示

             > 然后打开前端项目文件夹，复制文件夹目录地址栏的地址 > 切换到终端窗口 > 输入一下指令 > 
                cd C:\Users\Administrator\Desktop\毕业设计\前端\ngDesign
                (此目录要请以本地存放的目录为准)
             然后回车，此时终端应该已经打开了目标文件夹。
            * 注意: 如果项目存放在其他盘，如E盘，还需要输入盘符：
                E:
             回车即可 > 输入 node app.js > 然后回车 > 输出一下内容：
                server running at port 3000
             表示node服务器启动在3000端口

        3) 浏览器运行项目
            打开浏览器，建议使用Chrome、FireFox、Opera等浏览器，
            360，搜狗，百度，遨游也行，别用IE8以下，否则可能不支持。

            地址栏输入: localhost:3000 回车访问

        往后模块简述功能：

            1.此时跳转至用户登录界面
                根据数据库user表完成登录，
                可以使用如下账号：
                    用户名：Tom
                    密码： 123456
                功能： 用户登录，用户名检测，密码检测

            2.用户登录界面点击Create an account按钮，跳转注册页面
                用户名: 3-16位
                密码：6-30位
                功能：用户注册、登录、用户名检测

            3.用户登录后，跳转仪表盘dashboard页，显示学生相关图表信息
                左侧导航栏中，Student模块增伤改查完毕，Teacher模块待完善

            4...学生列表、学生详情、创建学生、删除学生等功能。

关于后端服务器端口问题：默认使用8080端口，
如果后端项目Tomcat服务器端口不是8080，一下两种解决办法：
    1)修改服务器端口号为8080，修改Tomcat conf/server.xml文件

    2)修改前端项目请求端口号，js文件中的一个变量而已
        ngDesign/js/controller/appCtrl.js
        ---appCtrl.js (修改后)
            // *****************指定后端API所在端口*****************
            var port = 8080;
            // *****************指定后端数据地址*****************
        ---
        找到appCtrl.js文件，找到以上代码，将其中的8080修改为当前Java项目部署的服务器端口号，如81
        ---appCtrl.js (修改前)
            // *****************指定后端API所在端口*****************
            var port = 81;
            // *****************指定后端数据地址*****************
        ---

        记得然后Ctrl + S保存更改，然后重新刷新浏览器页面，再次查看是否报错，如果依然报错，请及时联系。


后端负责根数据库打交道，处理数据的业务逻辑，最终以接口的形式，暴露给前端，初步模拟前后端分离场景，职责单一。

前端负责根据后端提供的接口，请求数据，根据数据渲染模板，添加交互，完善视图。