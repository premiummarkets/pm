SWTFILES=$(ls *zip);
echo files : $SWTFILES;
rm pom.extract;
for F in $SWTFILES;
do
 unzip $F swt.jar;
 SWTJARNAME=$(expr $F : '\(.*\)\.zip'); 
 echo moving $SWTJARNAME.jar; 
 mv swt.jar $SWTJARNAME.jar;
 #mvn install:install-file -Dfile=$SWTJARNAME.jar -DgroupId=swt -DartifactId=$SWTJARNAME -Dversion=0.0.1 -Dpackaging=jar;
 echo "<dependency>" >> pom.extract
 echo "<groupId>swt</groupId>" >> pom.extract
 echo "<artifactId>$SWTJARNAME</artifactId>" >> pom.extract
 echo "<version>0.0.1</version>" >> pom.extract
 echo "</dependency>" >> pom.extract
done

