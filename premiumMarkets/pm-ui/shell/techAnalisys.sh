#!/bin/bash

#export PATH="/usr/local/opt/java/bin:"$PATH
export DISPLAY=:0.0
xhost +local:

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

echo $PATH

cd $INSTALLDIR

xterm -e less  $INSTALLDIR/pmsjava.log &

nice -n 19 java -Xmx512M -XX:PermSize=64M -XX:MaxPermSize=128M -Dinstalldir=$INSTALLDIR -cp $INSTALLDIR/pm.jar com.finance.pms.MainLauncher all $INSTALLDIR/db.properties euronext yahoo false false $INSTALLDIR/list_test.txt

java -Xmx512M -XX:PermSize=64M -XX:MaxPermSize=128M -Djava.library.path=$INSTALLDIR/lib -Dinstalldir=$INSTALLDIR -jar $INSTALLDIR/pm.jar $INSTALLDIR/db.properties &

