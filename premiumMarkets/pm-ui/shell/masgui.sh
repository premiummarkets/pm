#java -jar /home/dev/Quotes/pms/pms_db.jar $@
java -Djava.library.path=/usr/local/lib -cp /home/dev/Quotes/pms/pms_fat.jar pms.mas.gui.MainGui /home/dev/Quotes/pms/db.properties /home/dev/Quotes/pms/mas.properties $@
