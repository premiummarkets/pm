#!/bin/bash
#set -vx

cd /home/guil/Developpement/Quotes/pms/
echo $PWD
export PATH="/usr/local/opt/java/bin:"$PATH
echo $PATH

#postgres
startPostgres=`pidof postgres`
if [ -z "$startPostgres" ]; then
	sudo -u postgres sh -c ". /var/lib/postgresql/.bashrc;cd /var/lib/postgresql;/var/lib/postgresql/startServer.sh"
	echo "\n\n"
	sleep 1;
fi

#apache mq
#startActiveMq=`ps -ef|grep activemq|wc -l`
#startActiveMq=`ps -eo cmd|grep -c "^/.*activemq.*"`
#echo "nb mq pss : "$startActiveMq
##if [ $startActiveMq -ne 2 ]; then
#if [ $startActiveMq -ne 1 ]; then
#	/usr/local/opt/apache-activemq-4.1.1/bin/activemq &
#fi


D=`date '+%y-%m-%d'`

#Update Quotes
Ds=`date '+%s'`
#echo "Quotations update and calculations"  >> ~/tmp/pmscron$D.log 2>&1
echo "Quotations update and calculations" |tee /home/guil/tmp/pmscron$D.log 
/home/guil/Developpement/Quotes/pms/all.sh |tee -a /home/guil/tmp/pmscron$D.log 
Df=`date '+%s'`
M=`expr $Df - $Ds`
MINUTE=`expr $M / 60`	
echo |tee -a ~/tmp/pmscron$D.log
echo "Update duration : "$MINUTE" Minutes" |tee -a  /home/guil/tmp/pmscron$D.log
echo |tee -a ~/tmp/pmscron$D.log

read

