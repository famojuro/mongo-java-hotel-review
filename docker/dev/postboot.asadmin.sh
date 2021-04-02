#!/usr/bin/env bash
#create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --restype javax.sql.DataSource --property user=root:password=password:DatabaseName=alvin:ServerName=172.17.0.1:port=3306:url=jdbc\:mysql\://172.17.0.1\:3306/alvin:allowPublicKeyRetrieval=true:useSSL=false alvin_pool
#create-jdbc-resource --connectionpoolid alvin_pool jdbc/alvin

deploy deployments/trmt.war
