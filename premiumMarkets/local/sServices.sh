
#postgres
#sudo -u postgres sh -c ". /var/lib/postgresql/.bashrc;cd /var/lib/postgresql;/var/lib/postgresql/startServer.sh"
#echo "\n\n"
#sleep 1

#apache mq
#/usr/local/opt/apache-activemq-4.1.1/bin/activemq &

#derby
#cd /usr/local/opt/Prgs/db-derby-10.3.2.1-bin/bin
#. ./setDerbyHome.sh
#./setNetworkServerCP
#./startNetworkServer &
#xterm -sb $PWD/ij &
#cd -

#eclipse
#export GDK_NATIVE_WINDOWS=1
#export CLASSPATH=$CLASSPATH:/usr/lib/java/swt-gtk-3.5.1.jar
#export SWT_GTK3=0
cd /usr/local/opt/Prgs/eclipse
./eclipse &
cd -

#db admin
#pgadmin3 &

#profiling
#cd  /usr/local/opt/Prgs/tptpAgent/bin/
#./RAStart.sh
#cd -
