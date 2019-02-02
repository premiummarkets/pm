#!/bin/sh

INSTALLDIR="$1";

echo "install dir :"$INSTALLDIR

chmod u+x ${INSTALLDIR}/shell/gui.sh
chmod u+x ${INSTALLDIR}/shell/gui.command

chmod u+x ${INSTALLDIR}/shell/gui_64.sh
chmod u+x ${INSTALLDIR}/shell/userPortfoliosUpdate.sh
