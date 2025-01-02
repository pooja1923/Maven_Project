pipeline {
    agent any

    tools {
        maven 'sonar-maven' 
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token') // Ensure you have this credential set in Jenkins
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
                    -Dsonar.sources=src \
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
                // Assuming you have coverage reporting logic here
                echo 'Publishing coverage report...'
                // Add your coverage report logic here if needed
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
