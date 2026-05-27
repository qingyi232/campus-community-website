# 校园社团网站

基于 Spring Boot + Thymeleaf + JPA + MySQL 的校园社团管理网站。

## 功能模块

- **首页** - 展示社团概况、最新活动、资讯和公告
- **社团信息** - 社团列表、详情、创建、编辑、搜索
- **活动管理** - 活动发布、编辑、查看、搜索
- **成员管理** - 加入申请、审批、职位调整、移除
- **在线留言** - 用户留言、管理员回复
- **社团资讯** - 资讯发布、浏览、搜索
- **通知公告** - 公告发布、置顶、浏览
- **登录/注册** - 用户认证、个人信息管理
- **用户管理** - 管理员后台用户管理

## 技术栈

- **后端**: Spring Boot 2.7.18, Spring Data JPA, Lombok
- **前端**: Thymeleaf, 原生 CSS
- **数据库**: MySQL 5.7+

## 环境要求

- JDK 1.8+
- Maven 3.6+
- MySQL 5.7+

## 快速启动

### 1. 创建数据库

```sql
CREATE DATABASE campus_community DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. 修改数据库配置

编辑 `src/main/resources/application.yml`，修改数据库连接信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/campus_community?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: root
```

### 3. 运行项目

```bash
mvn spring-boot:run
```

### 4. 访问

浏览器打开 http://localhost:8080

### 5. 初始化数据（可选）

在 MySQL 中执行 `src/main/resources/data.sql` 导入示例数据。

## 默认账号

| 角色 | 用户名 | 密码 |
|------|--------|------|
| 系统管理员 | admin | admin123 |
| 社团管理员 | lisi | 123456 |
| 普通用户 | zhangsan | 123456 |

## 角色权限

- **普通用户(0)**: 浏览信息、加入社团、发表留言、编辑个人信息
- **社团管理员(1)**: 普通用户权限 + 管理活动、资讯、回复留言
- **系统管理员(2)**: 全部权限 + 用户管理、公告管理、删除社团

## 项目结构

```
src/main/java/com/campus/community/
├── CampusCommunityApplication.java  # 启动类
├── config/                          # 配置类
├── controller/                      # 控制器层
├── entity/                          # 实体类
├── interceptor/                     # 拦截器
├── repository/                      # 数据访问层
└── service/                         # 业务逻辑层

src/main/resources/
├── application.yml                  # 应用配置
├── data.sql                         # 初始化数据
├── static/css/                      # 样式文件
└── templates/                       # 页面模板
    ├── fragments/                   # 公共片段
    ├── activity/                    # 活动模块
    ├── admin/                       # 管理模块
    ├── club/                        # 社团模块
    ├── member/                      # 成员模块
    ├── message/                     # 留言模块
    ├── news/                        # 资讯模块
    ├── notice/                      # 公告模块
    └── user/                        # 用户模块
```
