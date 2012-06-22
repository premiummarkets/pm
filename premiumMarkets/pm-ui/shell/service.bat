rem @echo off

if not .%1==. goto javacall
	cd ..
	goto endif
:javacall
	cd /D "%1"
:endif

rem current folder : %CD%


sc stop pmarkets
sc delete pmarkets
sc create pmarkets binpath= "%CD%\shell\srvany.exe" type= own start= auto DisplayName= "Piggy Market Squeak"
rem regedit shell\pmarkets.reg
regedit /S shell\pmarkets.reg

