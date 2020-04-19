
# 复用说明
##如果需要修改数据库版本，在repair-mysql-Dockerfile 第一行 from mysql:xxxx

COPY ./repair-server/src/main/repair.sql /docker-entrypoint-initdb.d/repair.sql
其中repair.sql需要更换为自己导出的名字
路径默认放在了/src/main/resources下面，可以更改