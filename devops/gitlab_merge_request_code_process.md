## GitLab代码质量检查调研

### 背景

为了提高公司的代码质量，引入了在开发过程加入代码检查，CodeReview等流程，本文重点关注是的代码检查自动化，即代码在提交后，自动扫描代码是否符合质量要求，对不符合质量规则的给出错误，不允许合并进主分支。

### 实现

在开始我们尝试了基于Git的Hook技术来实现，即代码提交时，触发相应的hook脚本进行单元测试，代码扫描等。经过实验发现基本不太可行。通过了解业界的最佳实践，通常是代码在merge request时，引入一个单独的代码质量检验流程，检验通过后才允许merge进入主分支。 整体大致流程如下:

![1560148382619](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560148382619.png)

### Merge request代码质量检测

##### 环境

Jenkins + GitLab + SonarQube

##### GitLab

1. 新建一个用于jenkins集成GitLab用户，生成一个AccessToken,赋于api和read_repository权限

![1560135967198](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560135967198.png)

2. 配置好jenkins环境，见下面jenkins配置说明

3. 配置集成触发merge request 事件

   Url 格式为http://USERID:APITOKEN@JENKINS_URL/project/YOUR_JOB

   ![1560157169787](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560157169787.png)

4. 尝试提交一个merge request,  会自动触发检查流程， 在merge request界面可以查看merge request结果

   ![1560151540020](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560151540020.png)

##### Jenkins

1.正常安装好Jenkins后，配置好Git, MSBuild, NuGet, SonarQube等工具

![1560158107973](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560158107973.png)

2.安装Git plugin 和 GitLab plugin, SonarQube Scanner for Jenkins,  Sonarqube Quality Gates Plugin(注意不要安装Quality Gates Plugin， 这个插件不支持新的Sonarqube api)

![1560136704036](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560136704036.png)

 3.在系统设置GitLab访问凭据(使用先前生成的AccessToken)和sonarqube 服务器访问

![1560137056919](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560137056919.png)

![1560232694473](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560232694473.png)

![1560232722322](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560232722322.png)

 4.新建一个自由风格的构建项目

​	GitLab Connection选择上一步创建的GitLab Connection

​	![1560150151417](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560150151417.png)

​     Source Code Management 选择Git 

​     Repository URL 设置当前项目的Git Url地址

​     Credentials 添加访问凭据 

​     Name设为origin

​     Refspec设为+refs/heads/*:refs/remotes/origin/* +refs/merge-requests/*/head:refs/remotes/origin/merge-requests/*

​    Branch Specifier 设为origin/${gitlabSourceBranch}

​    Additional Behaviours 添加Merge before build, 设置*Name of repository* to origin, brancher to merge 为    ${gitlabTargetBranch} 

![1560150412268](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560150412268.png)

   设置Build Trigger动作

​	![1560150734395](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560150734395.png)

​     添加正常的项目构建动作

​	![1560150898979](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560150898979.png)

​       添加单元测试或代码扫描等代码检查动作

​		![1560158202342](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560158202342.png)

​        

```
# project key全SonarQube Server唯一不可重复
sonar.projectKey=JenkinsSonarQubeTest
# Project name用于再SonarQube Server显示报告
sonar.projectName=JenkinsSonarQubeTest
# project Version版本号
sonar.projectVersion=1.0
# 指定源码所在目录
sonar.sources=$WORKSPACE\merge_request_test\TestAspNetMvcIntegration
sonar.login=
sonar.password=
```

 最后在Post-build Actions 添加Quality Gate 和 Publish build stats to GitLab动作

​    ![1560232907376](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560232907376.png)

5.新建一个用于GitLab集成的用户，有最小的Job Build权限，生成一个api token用于集成

![1560137505293](C:\Users\jianyong.jiang\AppData\Roaming\Typora\typora-user-images\1560137505293.png)

### 总结

通过以上的配置就可以实现merge request时自动运行sonarqube进行代码检查，当sonqrqube检查不通过时会通知GitLab当前的merge request 质量检查失败。检查步骤是可以扩展，可以在build中添加单元测试，集成测试等。

### 参考

[Jenkins GitLab plugin](<https://github.com/jenkinsci/gitlab-plugin>)