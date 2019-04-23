## OpenAPI规范学习笔记

### 背景

API定义First,  语言中立, 人类和机器可以直接阅读了解API的功能，意味着定义不能太复杂

使用生成工具自动生成文档和多种语言代码，甚至测试代码

### 介绍

### OpenAPI定义

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