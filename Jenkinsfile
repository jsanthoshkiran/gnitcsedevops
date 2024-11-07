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
                bat 'mvn clean package'
            }
        }

        stage('Deploy') {
            steps {
                script {
                bat ''' 
                    REM Define variables
                    set TOMCAT_HOME=E:\\apache-tomcat-11.0.0 
                    set WEBAPP_NAME=calculator-webapp 
                    set WAR_FILE=target\\calculator-webapp.war 
                    REM Deploy the WAR file to Tomcat 
                    copy %WAR_FILE% %TOMCAT_HOME%\\webapps\\%WEBAPP_NAME%.war 
                    REM Restart Tomcat server 
                    call %TOMCAT_HOME%\\bin\\shutdown.bat 
                    call %TOMCAT_HOME%\\bin\\startup.bat
                '''
                }
            }
        }
    }
}
