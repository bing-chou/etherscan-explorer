# etherscan-explorer
Yet another implementation of etherscan.io

A preview is here ==> [http://47.105.121.164/#/](http://47.105.121.164/#/)

## web3j-app
It is a java project using spring boot and web3j. Jdk8, Gradle and Mysql should be pre-installed.

The folder web3j-app/app is the core module doing sync job and providing rest api for frontend.

The project should be started after geth, because it will use geth ipc file to sync blockchain data to mysql.

Custom configurations are made in files:

1. web3j-app/app/src/main/resources/application.properties

2. web3j-app/app/src/main/java/me/bing/web3j/app/common/Constant.java

3. web3j-app/app/src/main/resources/logback.xml

By default: 

```java
//web3j-app/app/src/main/java/me/bing/web3j/app/common/Constant.java

public static String IpcAddress = "/Users/bing/test/data/geth.ipc";
public static String CapAddress = "https://api.coinmarketcap.com/v1/ticker/ethereum/";
```

```properties
#web3j-app/app/src/main/resources/application.properties

spring.datasource.url = jdbc:mysql://localhost:3306/eth_test?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.username = root
```

```xml
<!--/data/applogs should be accessable-->

<property name="log_path" value="/data/applogs/web3j-app"/>
```

## frontend
It is a project copied from [https://github.com/nebulasio/explorer/tree/develop/explorer-front](https://github.com/nebulasio/explorer/tree/develop/explorer-front) with little modification.

```bash
npm run dev # start in development
npm run build # build html&js which are in dist folder
```

## Depoly on server 
1. Pre-installation

    Nginx, Mysql, Java8, Geth 

2. build frontend, target directory is dist

```sh
cp dist/* /var/www/html
```

3. build web3j-app, target jar is app/build/web3j-app-1.0.0.jar

4. configure mysql, if you donot want to set your root password

```sql
use mysql;
update user set authentication_string=PASSWORD("") where User='root';
update user set plugin="mysql_native_password" where User='root';
```

create tables with these [schemes](https://raw.githubusercontent.com/bing-chou/etherscan-explorer/master/web3j-app/app/src/main/resources/mybatis/SCHEME.sql)

5. It is recommanded to run geth via [systemd](http://manpages.ubuntu.com/manpages/bionic/man1/systemctl.1.html)

```yaml
#/etc/systemd/system/geth.service

[Unit]
Description=geth

[Service]
Type=simple
User=root
Restart=always
WorkingDirectory=/root/webapp
ExecStart=/usr/bin/geth --datadir /Users/bing/test/data > geth.log

[Install]
WantedBy=default.target
```

```yaml
#/etc/systemd/system/web3japp.service

[Unit]
Description=web3japp
After=geth.service

[Service]
Type=simple
User=root
Restart=always
WorkingDirectory=/root/webapp
ExecStart=/usr/bin/java -jar web3j-app-1.0.0.jar > app.log

[Install]
WantedBy=default.target
```

```sh
systemctl daemon-reload
systemctl enable geth && systemctl enable web3japp
systemctl start geth && systemctl start web3japp && systemctl restart nginx
```

## Your contributions are welcome
The author is a newbie of gradle and vue, so there must be contributions you are willing to contribute.
