FROM payara/server-full

COPY postboot.asadmin.sh $CONFIG_DIR/post-boot-commands.asadmin

COPY password.txt $CONFIG_DIR/password.txt

#COPY mysql-connector-java-5.1.49.jar $PAYARA_DIR/glassfish/lib/mysql-connector-java-5.1.49.jar

COPY mongo-java-fix-0.0.1.war $DEPLOY_DIR/trmt.war
