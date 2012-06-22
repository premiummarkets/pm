#!/bin/bash
#set -vx

#postgres
sudo -u postgres sh -c ". /var/lib/postgresql/.bashrc;cd /var/lib/postgresql;/var/lib/postgresql/startServer.sh"
echo "\n\n"
sleep 1

#apache mq
/usr/local/opt/apache-activemq-4.1.1/bin/activemq &


D=`date '+%y-%m-%d'`

#Update Quotes
Ds=`date '+%s'`
echo "Mise à jour des quotations"  >> ~/tmp/pmscron$D.log 2>&1
/home/guil/Developpement/Quotes/pms/pms.sh DB /home/guil/Developpement/Quotes/pms/db.properties boursorama -fileStocks /home/guil/Developpement/Quotes/pms/empty_list.txt -majStocks >> ~/tmp/pmscron$D.log 2>&1
Df=`date '+%s'`
M=`expr $Df - $Ds`
MINUTE=`expr $M / 60`	
echo >> ~/tmp/pmscron$D.log
echo "Durée de la mise à jour : "$MINUTE" Minutes" >> ~/tmp/pmscron$D.log
echo >> ~/tmp/pmscron$D.log

#Launch Talib calculation
Ds=`date '+%s'`
echo "Starting calculation service"  >> ~/tmp/pmscron$D.log 2>&1
/home/guil/Developpement/Quotes/pms/calcS.sh talib -default >> ~/tmp/pmscron$D.log 2>&1
Df=`date '+%s'`
M=`expr $Df - $Ds`
MINUTE=`expr $M / 60`	
echo >> ~/tmp/pmscron$D.log
echo "calculation time : "$MINUTE" Minutes" >> ~/tmp/pmscron$D.log
echo >> ~/tmp/pmscron$D.log

#mas??

