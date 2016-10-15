cp target/mhpService-1.0-SNAPSHOT.jar c:/temp
cd /temp
jar xf mhpService-1.0-SNAPSHOT.jar
cd /temp/BOOT-INF/classes/WEB-INF/jsp/page/portal/mhp
cp * c:/work/czen/app/src/main/webapp.war/WEB-INF/jsp/page/portal/mhp
cd /work/mhpService
