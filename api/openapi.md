## OpenAPI规范介绍

### 背景

随着微服务架构风格，云计算等相关技术的流行，应用之间将越来越多是通过服务之间提供的API进行通信。如何定义良好的API以满足开发人员之间的交流和方便机器解析是一件越来越重要的事情。OpenAPI规范的出现也许可以帮助我们在一定程度上做好这件事情。

### 介绍

OpenAPI是REST风格的轻量级API定义规范，设计的目标就是定位于语言中立，便于人类阅读和机器解析。通过阅读基于OpenAPI的API定义，我们就能知道服务的功能，安全，调用方式等各种信息。OpenAPI通过定义一系列Schema，使用Schema来描述我们的API。一般来说我们定义基于REST风格的API,要素包括有请求地址，请求方法，请求参数，输出模型，安全要求等信息，OpenAPI 定义了一系列对象规范API的定义。

### Schema介绍

#### 顶级结点

```yaml
#版本信息 REQUIRED 
openapi: 3.0.0 

#API描述信息，包括标题，描述，版本，联系信息等   REQUIRED
info: 
	type : object

#API服务器地址 可以包含多个地址, 可选结点，未定义默认路径是/
servers: 
	type: object & array
	
#API路径和操作定义 REQUIRED
paths: 
	type: object & array

#为API定义可维度可得用的组件
components:
	content:  schemas,  responses, parameters,  examples,  requestBodies,  headers,  securitySchemes,  links,  callbacks

#API访问安全定义
security : 
	type: object

#附加元数据
tags : 
	type: object
	
#外部链接说明
externalDocs:
	type: object
```

#### 主要结点详细介绍

##### paths结点

一般形式如下，

```yaml
/{path} : path item object 
```



### 实例

```yaml
#版本信息
openapi: 3.0.0  

#提供API的元数据信息
info:     
  title: "API"
  version: "1.0.0"
  contact:
    name: Contact Name
    email: contact@example.com
    url: https://example.com/

#服务器地址信息, 如果没有定义将使用/
servers:
  - url: https://example.com/
    description: "test url"

#提供附加的元数据信息
tags:
  - name: Metadata
    description: Metadata related requests

#API的定义，包括路径，请求方法，响应等信息
paths:
  /order:
    get:
      operationId: getOrderById
      summary: get order details
      tags: [ 'Metadata' ]
      parameters:
        - name: 'id'
          in: ''
          description: 'test'
      responses:
        '200':
          description: OK
          content: 
            application/json:
              schema:
                $ref: "#/components/schemas/orderDto"

# 模型定义,可以让其它位置通过$ref进行引用
components:
      schemas: 
           orderDto:
              type: object
              properties:
                id:
                  type: integer
                  format: int64
                name:
                  type: string  
```

### 其它

类型说明， 自定义扩展，版本管理

### 总结

API First

### 参考

[OpenAPI ClientSDK和 Server Stub生成工具](https://github.com/OpenAPITools/openapi-generator)

[OpenAPI Web Online UI Editor 支持2.0](https://openapi.design)

[OpenAPI WebUI Editor 支持3.0](https://mermade.github.io/openapi-gui/) 

其它的IDE一般也有针对手动编写OpenAPI规范插件支持

#### Schema

#### 版本

#### 安全

#### 扩展

### 其它

### 定义

#### Path 模板

#### Media Type 

#### HTTP 状态码

### 内容

### 总结

wsdl

#### openpai

#### info 

```yaml
title: Sample Pet Store App
description: This is a sample server for a pet store.
termsOfService: http://example.com/terms/
contact:
  name: API Support
  url: http://www.example.com/support
  email: support@example.com
license:
  name: Apache 2.0
  url: https://www.apache.org/licenses/LICENSE-2.0.html
version: 1.0.1
```

#### servers

```yaml
servers:  
  url: https://development.gigantic-server.com/v1
  description: Development server
  url: https://staging.gigantic-server.com/v1
  description: Staging server
  url: https://api.gigantic-server.com/v1
  description: Production server
```

```yaml
servers:

- url: https://{username}.gigantic-server.com:{port}/{basePath}
  description: The production API server
  variables:
    username:
      # note! no enum here means it is an open value
      default: demo
      description: this value is assigned by the service provider, in this example `gigantic-server.com`
    port:
      enum:
        - '8443'
        - '443'
      default: '8443'
    basePath:
      # open meaning there is the opportunity to use special base paths as assigned by the provider, default is `v2`
      default: v2
```

#### paths

```yaml
/pets:
  get:
    description: Returns all pets from the system that the user has access to
    responses:
      '200':
        description: A list of pets.
        content:
          application/json:
            schema:
              type: array
              items:
                $ref: '#/components/schemas/pet'
```



#### components

```yaml
components:
  schemas:
    GeneralError:
      type: object
      properties:
        code:
          type: integer
          format: int32
        message:
          type: string
    Category:
      type: object
      properties:
        id:
          type: integer
          format: int64
        name:
          type: string
    Tag:
      type: object
      properties:
        id:
          type: integer
          format: int64
        name:
          type: string
  parameters:
    skipParam:
      name: skip
      in: query
      description: number of items to skip
      required: true
      schema:
        type: integer
        format: int32
    limitParam:
      name: limit
      in: query
      description: max records to return
      required: true
      schema:
        type: integer
        format: int32
  responses:
    NotFound:
      description: Entity not found.
    IllegalInput:
      description: Illegal input for operation.
    GeneralError:
      description: General Error
      content:
        application/json:
          schema:
            $ref: '#/components/schemas/GeneralError'
  securitySchemes:
    api_key:
      type: apiKey
      name: api_key
      in: header
    petstore_auth:
      type: oauth2
      flows: 
        implicit:
          authorizationUrl: http://example.org/api/oauth/dialog
          scopes:
            write:pets: modify pets in your account
            read:pets: read your pets
```

#### security

#### tags

#### externalDocs

### 参考

[OpenAPI规范V3.0.2](https://swagger.io/specification/)

[语义化版本管理2.0](<https://semver.org/lang/zh-CN/>)