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
              withMaven {
                     bat 'mvn clean package'
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps { 
                bat ''' 
                    REM Define variables
                    set TOMCAT_HOME=E:\\apache-tomcat-11.0.0
                    set WEBAPP_NAME=calculator-webapp-1.0.war
                    set WAR_LOCATION=.\\target\\
                    REM Deploy the WAR file to Tomcat
                    copy %WAR_LOCATION%\\%WEBAPP_NAME% %TOMCAT_HOME%\\webapps\\
                    call %TOMCAT_HOME%\\bin\\catalina stop
                    call %TOMCAT_HOME%\\bin\\catalina start
                '''
            }
        }
    }

    post{
        success {
            mail to: 'jsanthoshkiran@gmail.com',
              subject: "Successful Build: ${currentBuild.fullDisplayName}",
              body: "The build ${currentBuild.fullDisplayName} succedded"
        }
    }
}