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

if [ $# -eq 0 ];
then
	echo please, give a list of symbols as parameter;
	exit 1;
fi

nice -n 19 java -Xconcurrentio -Xmx2256M -Xss128k -XX:PermSize=64M -XX:MaxPermSize=192M -Dinstalldir=$INSTALLDIR -cp $INSTALLDIR/pm.jar com.finance.pms.admin.GnucashGetPriceHelper \
$INSTALLDIR/db.properties -symbols $@;


