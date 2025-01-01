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
      
        stage('Build') {
            steps {
                script {
                    bat '''
                    mvn clean install
                    '''
                }
            }
        }

        stage('SonarQube Analysis') {
            environment {
                SONAR_TOKEN = credentials('sonar-token')
            }
            steps {
                script {
                    bat '''
                    mvn clean verify sonar:sonar \
                    -Dsonar.projectKey=LoginAutomationTest_PoojaK \
                    -Dsonar.sources=src \
                    -Dsonar.inclusions=**/*.java \
                    -Dsonar.jacoco.reportPaths=target/site/jacoco/jacoco.xml \
                    -Dsonar.host.url=http://localhost:9000 \
                    -Dsonar.login=%SONAR_TOKEN%
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    timeout(time: 1, unit: 'HOURS') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
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
