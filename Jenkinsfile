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

        stage('Deploy') {
            steps {
                echo '🚀 Deploying the application...'
                bat "cd %PROJECT_DIR% && java -jar target\\employee-api-1.0.0.jar"
            }
        }

        stage('Docker Build & Push') {
            steps {
                echo '🐳 Building Docker image...'
                bat "docker build -t employee-api:latest %PROJECT_DIR%"
                // Uncomment these lines after logging into Docker Hub:
                // bat "docker tag employee-api:latest your-dockerhub-username/employee-api:latest"
                // bat "docker push your-dockerhub-username/employee-api:latest"
            }
        }
    }

    post {
        success {
            echo '✅ Build successful!'
        }
        failure {
            echo '❌ Build failed.'
        }
    }
}
