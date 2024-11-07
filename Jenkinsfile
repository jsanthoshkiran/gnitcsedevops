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
                
                deploy( adapters: [ tomcatAdapter( tomcatHome: 'E://apache-tomcat-11.0.0', username: 'admin', password: 'admin123', warFile: 'target/calculator-webapp.war' ) ] ) 
                }
        }
    }
}
