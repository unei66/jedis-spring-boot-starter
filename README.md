# jedis-spring-boot-starter
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0) [![Release Version](https://img.shields.io/badge/release-0.1.0-red.svg)](https://github.com/TFdream/jedis-spring-boot-starter/releases) [![Build Status](https://travis-ci.org/TFdream/jedis-spring-boot-starter.svg?branch=master)](https://travis-ci.org/TFdream/jedis-spring-boot-starter)

## Overview
Jedis Spring Boot Starter.

## Quick Start
### 1. pom.xml
```
<dependency>
    <groupId>io.dreamstudio</groupId>
    <artifactId>jedis-spring-boot-starter</artifactId>
    <version>0.1.0</version>
</dependency>
```

### 2. application.yml
#### 1.Redis standalone
```
redis:
  database: 0
  host: localhost
  port: 6379
  password: root
  pool:
    max-active: 50
    max-idle: 1
    min-idle: 0
    max-wait: 5000
```

#### 2.Redis sentinel
```
redis:
  sentinel:
    master: mymaster
    nodes: 192.168.101.1:6379,192.168.102.1:6379,192.168.103.1:6379
  password: iqianjinTest001*
  pool:
    max-active: 50
    max-idle: 1
    min-idle: 0
    max-wait: 5000
```

#### 3.Redis cluster
```
redis:
  cluster:
    nodes: 192.168.101.1:6379,192.168.102.1:6379,192.168.103.1:6379
    max-redirects: 3
  password: iqianjinTest001*
  pool:
    max-active: 50
    max-idle: 1
    min-idle: 0
    max-wait: 5000
```
