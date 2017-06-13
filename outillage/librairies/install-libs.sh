#!/bin/bash
mvn install:install-file -Dfile=generic-commons-tools.jar -DgroupId=net.entetrs.commons -DartifactId=generic-common-tools -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=jsf-commons-tools.jar -DgroupId=net.entetrs.commons -DartifactId=jsf-common-tools -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=jpa-commons-tools.jar -DgroupId=net.entetrs.commons -DartifactId=jpa-common-tools -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=security-commons-tools.jar -DgroupId=net.entetrs.commons -DartifactId=security-common-tools -Dversion=1.0 -Dpackaging=jar
