#!/bin/bash
#set -vx
#export PATH="/usr/local/opt/java/bin:"$PATH

BN=`basename $0`
INSTALLDIR=`expr $0 : '\(.*\)'$BN`..

INSTALLDIRFIRST=`echo "$INSTALLDIR" | cut -c1-1`
echo "start  : "$INSTALLDIRFIRST
if [ "$INSTALLDIRFIRST" != "/" ];
        then
                INSTALLDIR=$PWD/$INSTALLDIR;
fi

if [ -n "$1" ];
        then
                INSTALLDIR=$1;
fi

echo "install dir :"$INSTALLDIR

cd $INSTALLDIR


#postgres
#startPostgres=`pidof postgres`
#if [ -z "$startPostgres" ]; then
#	sudo -u postgres sh -c ". /var/lib/postgresql/.bashrc;cd /var/lib/postgresql;/var/lib/postgresql/startServer.sh"
#	echo "\n\n"
#	sleep 1;
#fi

#configuration mas
D=`date -d"6 month ago" +%Y-%m-%d`
sed "s/1970-01-01/$D/" ~/.mas/mas_dbrc.ref > ~/.mas/mas_dbrc.updated
cp ~/.mas/mas_dbrc.updated ~/.mas/mas_dbrc

D=`date '+%y-%m-%d'`

#Update Quotes
Ds=`date '+%s'`
#echo "Quotations update and calculations"  >> ~/tmp/pmscron$D.log 2>&1
echo "Quotations update and calculations" |tee $INSTALLDIR/pmscron$D.log 
/bin/bash $INSTALLDIR/shell/all.sh |tee -a $INSTALLDIR/pmscron$D.log 
Df=`date '+%s'`
M=`expr $Df - $Ds`
MINUTE=`expr $M / 60`	
echo |tee -a ~/tmp/pmscron$D.log
echo "Update duration : "$MINUTE" Minutes" |tee -a  $INSTALLDIR/pmscron$D.log
echo |tee -a ~/tmp/pmscron$D.log

read

