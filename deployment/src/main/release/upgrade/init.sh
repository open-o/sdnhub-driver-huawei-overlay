#!/bin/bash
#check user
CUR_PATH=$(cd `dirname $0`;pwd)
SCRIPT_PATH=$0
IPMC_USER="`stat -c '%U' ${SCRIPT_PATH}`"
export IPMC_USER
CURRENT_USER="`/usr/bin/id -u -n`"
if [ "${IPMC_USER}" != "${CURRENT_USER}" ]
then
    echo "only ${IPMC_USER} can execute this script."
    exit 1
fi
umask 027

cd $APP_ROOT
JAVA=$JAVA_HOME/bin/java

DB_ID="acbranchdb"
SCRIPT=$APP_ROOT/init/acbranchservice_tables_@{dbtype}.sql

JVM_OPT="$JVM_OPT -Dlog.dir=$_APP_LOG_DIR"
JVM_OPT="$JVM_OPT -Dinit.appconfig=$APP_CONF_FILE"
JVM_OPT="$JVM_OPT -Dinit.approot=$APP_ROOT"
JVM_OPT="$JVM_OPT -Dinit.dataSourceId=$DB_ID"
JVM_OPT="$JVM_OPT -Dinit.script=$SCRIPT"
JVM_OPT="$JVM_OPT -Dinit.connectType=$1"

#ResPoolService use MSS bucketsys to init database
JVM_OPT="$JVM_OPT -Dinit.class=org.openo.sdno.tools.dbinit.init.impl.MSSModelIniter"    

#Move the jars which puer_initdbtool needs to the $APP_ROOT/modules/puer_initdbtool directory.
#For the service does not need these jars.
mkdir -p $APP_ROOT/modules/puer_initdbtool
mv -f $APP_ROOT/lib/org.openo.sdno.mss*.jar $APP_ROOT/modules/puer_initdbtool/
mv -f $APP_ROOT/lib/org.openo.sdno.tools.dbinit*.jar $APP_ROOT/modules/puer_initdbtool/
mv -f $APP_ROOT/lib/*liquibase*.jar $APP_ROOT/modules/puer_initdbtool/

CLASS_PATH="$APP_ROOT/lib/*:$APP_ROOT/modules/puer_initdbtool/*:$APP_ROOT/webapps/ROOT/WEB-INF/lib/*:"
JVM_OPT="$JVM_OPT -classpath $CLASS_PATH"

$JAVA $JVM_OPT org.openo.sdno.tools.dbinit.inittool.InitTool
result=$?
$CUR_PATH/../../../../manager/agent/tools/shscript/syslogutils.sh "$(basename $0)" "$result" "Execute($#):$CUR_PATH/$0 $@";exit $result
