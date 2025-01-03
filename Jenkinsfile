pipeline {
    agent any

    tools {
        maven 'sonar-maven' 
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token') 
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }
      
        stage('Build and Test') {
            steps {
                script {
                    bat 'mvn clean verify'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    bat '''
                    mvn sonar:sonar \
                    -Dsonar.projectKey=LoginAutomationTest_PoojaK \
                     -Dsonar.sources=src/main/java \
                    -Dsonar.tests=src/test/java \
                    -Dsonar.jacoco.reportPaths=target/site/jacoco/jacoco.xml \
                    -Dsonar.inclusions=**/*.java \
                    -Dsonar.host.url=http://localhost:9000 \
                    -Dsonar.login=%SONAR_TOKEN%
                    '''
                }
            }
        }

        stage('Publish Coverage Report') {
            steps {
                
                echo 'Publishing coverage report...'
               
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check the logs for errors.'
        }
        always {
            echo 'This runs regardless of the result.'
        }
    }
}
