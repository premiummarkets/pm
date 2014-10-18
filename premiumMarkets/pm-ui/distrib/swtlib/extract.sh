SWTFILES=$(ls *zip);
echo files : $SWTFILES;
rm pom.extract;
for F in $SWTFILES;
do
 unzip $F swt.jar;
 SWTJARNAME=$(expr $F : '\(.*\)\.zip'); 
 VERSION="4.4"
 echo moving $SWTJARNAME.jar; 
 mv swt.jar $SWTJARNAME.jar;
 mvn install:install-file -Dfile=$SWTJARNAME.jar -DgroupId=swt -DartifactId=$SWTJARNAME -Dversion=$VERSION -Dpackaging=jar;
 echo "<dependency>" >> pom.extract
 echo "<groupId>swt</groupId>" >> pom.extract
 echo "<artifactId>$SWTJARNAME</artifactId>" >> pom.extract
 echo "<version>$VERSION</version>" >> pom.extract
 echo "</dependency>" >> pom.extract
done

