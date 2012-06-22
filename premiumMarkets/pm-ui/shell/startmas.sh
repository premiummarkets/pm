#!/bin/bash

export MAS_DB_CONFIG_FILE=~/.mas/mas_dbrc
export MAS_DIRECTORY=~/.mas/

#demarage postgresql
#STATUS=`/etc/init.d/postgresql status|cut -d" " -f3`
#if [ "$STATUS" == "stopped" ]; then
#	        /etc/init.d/postgresql start
#fi

#configuration mas
#D=`date -d"5 years ago" +%Y-%m-%d`
#D=`date -d"30 years ago" +%Y-%m-%d`
D=`date -d"6 month ago" +%Y-%m-%d`
sed "s/1970-01-01/$D/" ~/.mas/mas_dbrc.ref > ~/.mas/mas_dbrc.updated
cp ~/.mas/mas_dbrc.updated ~/.mas/mas_dbrc
		
#demarage mas
NBTH=10
THPORTFIRST=13579
for (( CPT=0 ; CPT < ${NBTH} ; CPT++ )) ;do
	THPORT=`expr ${THPORTFIRST} + ${CPT}`
	/usr/local/opt/Prgs/mas/bin/mas ${THPORT} -p -b &
done

#Pool pour la supression des cores.
CONTINU=0
while [ "$CONTINU" -eq 0 ]; do
	echo "Suppression des cores"
	rm $PWD/core.*
	sleep 60
	MASSES=`/usr/bin/pgrep -f -l "mas.* -p -b"`
	echo "Processes MAS en cour : "
	echo "$MASSES"
	if [ -z "$MASSES" ]; then
		CONTINU=1
		echo "Arret du pool de suppression des cores"
	fi
done
	
