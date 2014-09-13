rem @echo off

rem current folder : %CD%
if .%1==. (goto :noparam) else (goto :param)

:noparam
cd ..
rem %CD%
goto :cmd

:param
cd /D "%1"
rem %CD%
goto :cmd

:cmd
rem current folder : %CD%

rem java -Xmx512M -XX:PermSize=64M -XX:MaxPermSize=128M -Djava.library.path="%CD%\lib" -Dinstalldir="%CD%" -jar "%CD%\pm.jar" "%CD%\db.properties"
start /min java -splash:"%CD%\icons\squeakyPig.png" -Xmx512M -XX:PermSize=64M -XX:MaxPermSize=128M -Djava.library.path="%CD%\lib" -Dinstalldir="%CD%" -jar "%CD%\pm.jar" "%CD%\db.properties"
