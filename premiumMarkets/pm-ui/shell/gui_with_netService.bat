rem @echo off
if not .%1==. goto javacall
    cd ..
    goto endif

:javacall
    %1\shell\guishortcut.vbs %1
    cd /D "%1"
    rem starting pmarkets
    net start pmarkets
  
:endif

rem current folder : %CD%

java -Xmx512M -XX:PermSize=64M -XX:MaxPermSize=128M -Djava.library.path="%CD%\lib" -Dinstalldir="%CD%" -jar "%CD%\pm.jar" "%CD%\db.properties"

rem if not .%1==. goto startsrv
rem 	goto endbat
rem :startsrv
rem 	rem starting pmarkets
rem 	net start pmarkets
rem :endbat
