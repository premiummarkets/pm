sed "s/recipient/$1/" mail.txt| sendmail -f piggymarketsqueak@gmail.com -t $1
