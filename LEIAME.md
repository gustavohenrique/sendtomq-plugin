Selo Qualidade
==============

Plugin para o Build Server para edicao do DocEar.


Configuracao
------------

# Configurar o projeto para o eclipse
mvn -DdownloadSources=true -DdownloadJavadocs=true -DoutputDirectory=target/eclipse-classes eclipse:eclipse

# Rodar uma instancia do build server em desenvolvimento
export MAVEN_OPTS="-Xms512m -Xmx1024m -XX:MaxPermSize=512m"
mvn clean hpi:run

# Acessar o build server
http://localhost:8080/

# Debug pelo eclipse
export MAVEN_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=8000,suspend=n"
mvn hpi:run

No eclipse vai em Debug Configurations\Remote Java Application

# Configurando o maven
Copiar o arquivo settings-exemplo.xml para $M2_HOME/settings.xml

Testes
------

Os testes so rodam pelo maven:
mvn clean test
mvn -Dtest=NomeDaClasse
mvn -Dtest=NomeDaClasse#metodo
mvn -Dtest=TestCi*le test
mvn -Dtest=TestSquare,TestCi*le test
mvn -Dtest=TestCircle#test* test

# Debug dos Testes
mvn clean test -Dmaven.surefire.debug
No eclipse vai em Debug Configurations\Remote Java Application e configure a porta para 5005

