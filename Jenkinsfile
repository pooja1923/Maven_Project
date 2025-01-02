pipeline {
    agent any

    tools {
        maven 'sonar-maven'
    }

    environment {
        SONAR_SCANNER_PATH = "C:\\Users\\Pooja\\Downloads\\sonar-scanner-cli-6.2.1.4610-windows-x64\\sonar-scanner-6.2.1.4610-windows-x64\\bin"
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
                    bat '''
                    mvn clean verify
                    '''
                }
            }
        }

        stage('SonarQube Analysis') {
            environment {
                SONAR_TOKEN = credentials('sonar-token')
            }
            steps {
                bat '''
                mvn sonar:sonar \
                -Dsonar.projectKey=LoginAutomationTest_PoojaK \
                -Dsonar.sources=src \
                -Dsonar.tests=src/test/java \
                -Dsonar.host.url=http://localhost:9000 \
                -Dsonar.login=%SONAR_TOKEN%
                '''
            }
        }

        stage('Publish Coverage Report') {
            steps {
                // Use the Coverage Plugin to publish coverage reports
                // Assuming you're using a JaCoCo report, adjust if you're using a different format
                coverage([
                    sourceFile: '**/target/site/jacoco/jacoco.xml',
                    reportType: 'JaCoCo' // Ensure you specify the correct report type
                ])
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
