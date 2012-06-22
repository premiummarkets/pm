rem @echo off
if not .%1==. goto croncall
    cd ..
    goto endif
:croncall
    cd /D "%1"
:endif

echo current folder : %CD% > d:\tmp.txt

java -Xmx512M -XX:PermSize=64M -XX:MaxPermSize=128M -Dinstalldir="%CD%" -cp "%CD%\pms.jar" com.finance.pms.MainLauncher all "%CD%\db.properties" euronext yahoo false false "%CD%\list_test.txt"

java -Xmx512M -XX:PermSize=64M -XX:MaxPermSize=128M -Djava.library.path="%CD%\lib" -Dinstalldir="%CD%" -jar "%CD%\pms.jar" "%CD%\db.properties" 
