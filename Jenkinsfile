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
                bat 'mvn'
                bat 'mvn clean package'
            }
        }

        stage('Deploy to Tomcat') {
            steps { 
                
                copy target//calculator-webapp*.war E://apache-tomcat-11.0.0//webapps//
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