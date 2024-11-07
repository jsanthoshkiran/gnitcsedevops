pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'development', url: 'https://github.com/jsanthoshkiran/gnitcsedevops.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh '''
                        # Define variables
                        TOMCAT_HOME="E:\apache-tomcat-11.0.0"
                        WEBAPP_NAME="calculator-webapp"
                        WAR_FILE="target/calculator-webapp.war"

                        # Deploy the WAR file to Tomcat
                        cp -f $WAR_FILE $TOMCAT_HOME/webapps/$WEBAPP_NAME.war

                        # Restart Tomcat server
                        $TOMCAT_HOME/bin/shutdown.sh
                        $TOMCAT_HOME/bin/startup.sh
                    '''
                }
            }
        }
    }
}
