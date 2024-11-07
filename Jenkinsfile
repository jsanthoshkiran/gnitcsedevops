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
                
                deploy( adapters: [ tomcatAdapter( tomcatHome: 'E://apache-tomcat-11.0.0', username: 'admin', password: 'admin123', war: 'target/calculator-webapp.war' ) ] ) 
                }
        }
    }

    post{
        success {
            mail to: 'jsanthoshkiran@gmail.com'
              subject: "Successful Build: ${currentBuild.fullDisplayName}"
              body: "The build ${currentBuild.fullDisplayName} succedded"
        }
    }
}
