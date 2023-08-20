pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout your code from version control system (e.g., Git)
                // You might need to configure credentials and repository URL
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Build your Maven project
                // Replace 'mvn' with the correct path to your Maven executable if needed
                sh 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                // Run your tests using Maven and TestNG
                // Replace 'testng.xml' with the correct path to your TestNG suite file
                // Replace 'mvn' with the correct path to your Maven executable if needed
                sh 'mvn test -DsuiteXmlFile=testng.xml'
            }
        }

        stage('Publish Allure Reports') {
            steps {
                // Generate and publish Allure reports
                // Replace 'allure' with the correct path to your Allure commandline executable if needed
                sh 'allure generate target/allure-results --clean -o target/allure-report'
                archiveArtifacts allowEmptyArchive: true, artifacts: 'target/allure-report'
            }
        }
    }
}
