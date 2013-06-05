#!/bin/bash

#export PATH="/usr/local/opt/java/bin:"$PATH
export GDK_NATIVE_WINDOWS=1
export LIBOVERLAY_SCROLLBAR=0

BN=`basename "$0"`
INSTALLDIR=`expr "$0" : '\(.*\)'"$BN"`..

echo "install dir "$INSTALLDIR

INSTALLDIRFIRST=`echo "$INSTALLDIR" | cut -c1-1`
echo "start  : "$INSTALLDIRFIRST
if [ "$INSTALLDIRFIRST" != "/" ];
	then
		INSTALLDIR="$PWD"/"$INSTALLDIR";
fi

if [ -n "$1" ];
	then
		INSTALLDIR="$1";
fi

echo "install dir :"$INSTALLDIR

echo $PATH

cd "$INSTALLDIR"
	
java -Xmx512M -XX:PermSize=64M -XX:MaxPermSize=128M -XstartOnFirstThread -Djava.library.path="$INSTALLDIR"/lib -Dinstalldir="$INSTALLDIR" -jar "$INSTALLDIR"/pm.jar "$INSTALLDIR"/db.properties 
