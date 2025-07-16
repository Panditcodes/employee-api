pipeline {
    agent any

    tools {
        maven 'Maven_3.9.6'
        jdk 'JDK_17'
    }

    environment {
        PROJECT_DIR = 'C:\\Users\\91998\\employee-api'
    }

    stages {
        stage('Build') {
            steps {
                echo '📦 Building the project...'
                bat "cd %PROJECT_DIR% && mvn clean install"
            }
        }

        stage('Test') {
            steps {
                echo '🧪 Running tests...'
                bat "cd %PROJECT_DIR% && mvn test"
            }
        }

        stage('Package') {
            steps {
                echo '📦 Packaging the app...'
                bat "cd %PROJECT_DIR% && mvn package"
            }
        }

        stage('Archive Artifacts') {
            steps {
                echo '📁 Archiving JAR...'
                bat "if not exist %WORKSPACE%\\target mkdir %WORKSPACE%\\target"
                bat "copy %PROJECT_DIR%\\target\\*.jar %WORKSPACE%\\target\\"
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }

        stage('Kill Existing App') {
            steps {
                echo '🛑 Killing any existing process on port 9090...'
                bat '''
                    for /f "tokens=5" %%a in ('netstat -aon ^| findstr :9090 ^| findstr LISTENING') do (
                        echo Killing PID %%a
                        taskkill /F /PID %%a
                    )
                '''
            }
        }

        stage('Deploy') {
            steps {
                echo '🚀 Deploying the application...'
                bat "start /B java -jar %PROJECT_DIR%\\target\\employee-api-1.0.0.jar"
            }
        }

        stage('Docker Build & Push') {
            steps {
                echo '🐳 Building and pushing Docker image...'
                bat "docker build -t employee-api:latest %PROJECT_DIR%"
                bat "docker tag employee-api:latest panditcodes/employee-api:latest"
                bat "docker push panditcodes/employee-api:latest"
            }
        }
    }

    post {
        success {
            echo '✅ Build & Deploy successful!'
        }
        failure {
            echo '❌ Build failed.'
        }
    }
}
