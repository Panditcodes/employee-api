# 🚀 Employee API – DevOps CI/CD Pipeline Project

This project demonstrates a complete DevOps CI/CD pipeline for a Spring Boot REST API (`employee-api`) integrated with GitHub, Jenkins, Docker, and Docker Hub.

---

## 📦 Technologies Used

- Java + Spring Boot
- Maven
- Git & GitHub
- Jenkins (CI/CD)
- Docker & Docker Hub
- Webhooks for GitHub → Jenkins integration

---

## 🔄 CI/CD Workflow

1. **Code Push to GitHub**  
   ➡ Triggers GitHub Webhook → Jenkins.

2. **Jenkins Pipeline**  
   ✅ Pulls latest code  
   ✅ Runs tests using `mvn test`  
   ✅ Builds `.jar` using `mvn package`  
   ✅ Stops old app running on port 9090  
   ✅ Starts new app  
   ✅ Builds Docker image  
   ✅ Pushes to Docker Hub

3. **Docker Deployment**  
   Pulls latest image from Docker Hub and runs container on port `9090`.

---

## 🧪 Testing the App

Open browser at: http://localhost:9090/hello

✅ Should return: Hello from Employee API


---
## 🐳 Docker Commands (Manual Testing)
# Pull latest image
docker pull panditcodes/employee-api:latest

# Run the app on port 9090
docker run -d -p 9090:9090 panditcodes/employee-api:latest

Jenkins File:
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Panditcodes/employee-api.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Kill Existing App') {
            steps {
                sh 'fuser -k 9090/tcp || true'
            }
        }

        stage('Deploy') {
            steps {
                sh 'nohup java -jar target/*.jar &'
            }
        }

        stage('Docker Build & Push') {
            steps {
                sh 'docker build -t panditcodes/employee-api:latest .'
                withCredentials([string(credentialsId: 'dockerhub-token', variable: 'TOKEN')]) {
                    sh "echo $TOKEN | docker login -u panditcodes --password-stdin"
                    sh 'docker push panditcodes/employee-api:latest'
                }
            }
        }
    }
}

---
Project Structure:
employee-api/
│
├── src/
├── target/
├── Dockerfile
├── Jenkinsfile
├── pom.xml
└── README.md

Author: @Panditcodes





