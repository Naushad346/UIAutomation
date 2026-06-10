pipeline {
    agent any
    
    triggers {
        // Run every night at 2 AM
        cron('0 2 * * *')
    }
    
    options {
        // Keep last 30 builds
        buildDiscarder(logRotator(numToKeepStr: '30'))
        // Timeout after 1 hour
        timeout(time: 1, unit: 'HOURS')
        // Timestamp in console output
        timestamps()
    }
    
    environment {
        // Email configuration
        EMAIL_RECIPIENT = 'your-email@example.com'
        REPORT_PATH = '${WORKSPACE}/test-reports'
    }
    
    stages {
        stage('Checkout') {
            steps {
                script {
                    echo "Checking out code from repository..."
                    checkout scm
                }
            }
        }
        
        stage('Build') {
            steps {
                script {
                    echo "Building the project..."
                    sh '''
                        # Add your build commands here
                        # Example: mvn clean compile
                    '''
                }
            }
        }
        
        stage('Run Tests') {
            steps {
                script {
                    echo "Running Selenium tests with BDD..."
                    sh '''
                        # Run your test suite
                        # Example for Maven: mvn clean test
                        # Example for Gradle: gradle test
                        mkdir -p ${REPORT_PATH}
                    '''
                }
            }
        }
        
        stage('Generate Report') {
            steps {
                script {
                    echo "Generating test reports..."
                    sh '''
                        # Generate reports if applicable
                        # Example: mvn allure:report
                    '''
                }
            }
        }
    }
    
    post {
        always {
            script {
                echo "Archiving test reports..."
                // Archive test results
                archiveArtifacts artifacts: '**/test-reports/**', 
                                 allowEmptyArchive: true,
                                 onlyIfSuccessful: false
            }
        }
        success {
            script {
                echo "Build succeeded! Sending success email..."
                emailext(
                    subject: "✅ Jenkins Build Successful - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: '''
                        <h2>Build Report</h2>
                        <p><b>Build Status:</b> SUCCESS</p>
                        <p><b>Job Name:</b> ${JOB_NAME}</p>
                        <p><b>Build Number:</b> ${BUILD_NUMBER}</p>
                        <p><b>Build URL:</b> <a href="${BUILD_URL}">${BUILD_URL}</a></p>
                        <p><b>Build Duration:</b> ${BUILD_DURATION}</p>
                        <p><b>Branch:</b> ${GIT_BRANCH}</p>
                        <p><b>Commit:</b> ${GIT_COMMIT}</p>
                        <h3>Test Results</h3>
                        <p>All tests passed successfully!</p>
                    ''',
                    to: '${EMAIL_RECIPIENT}',
                    mimeType: 'text/html',
                    recipientProviders: [
                        developers(),
                        requestor()
                    ]
                )
            }
        }
        failure {
            script {
                echo "Build failed! Sending failure email..."
                emailext(
                    subject: "❌ Jenkins Build Failed - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: '''
                        <h2>Build Report</h2>
                        <p><b>Build Status:</b> FAILURE</p>
                        <p><b>Job Name:</b> ${JOB_NAME}</p>
                        <p><b>Build Number:</b> ${BUILD_NUMBER}</p>
                        <p><b>Build URL:</b> <a href="${BUILD_URL}">${BUILD_URL}</a></p>
                        <p><b>Build Duration:</b> ${BUILD_DURATION}</p>
                        <p><b>Branch:</b> ${GIT_BRANCH}</p>
                        <p><b>Commit:</b> ${GIT_COMMIT}</p>
                        <h3>Error Details</h3>
                        <p>Please check the build logs for more details.</p>
                        <p><a href="${BUILD_URL}console">View Console Output</a></p>
                    ''',
                    to: '${EMAIL_RECIPIENT}',
                    mimeType: 'text/html',
                    recipientProviders: [
                        developers(),
                        requestor()
                    ]
                )
            }
        }
        unstable {
            script {
                echo "Build unstable! Sending unstable email..."
                emailext(
                    subject: "⚠️ Jenkins Build Unstable - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: '''
                        <h2>Build Report</h2>
                        <p><b>Build Status:</b> UNSTABLE</p>
                        <p><b>Job Name:</b> ${JOB_NAME}</p>
                        <p><b>Build Number:</b> ${BUILD_NUMBER}</p>
                        <p><b>Build URL:</b> <a href="${BUILD_URL}">${BUILD_URL}</a></p>
                        <p><b>Build Duration:</b> ${BUILD_DURATION}</p>
                        <p><b>Branch:</b> ${GIT_BRANCH}</p>
                        <p><b>Commit:</b> ${GIT_COMMIT}</p>
                    ''',
                    to: '${EMAIL_RECIPIENT}',
                    mimeType: 'text/html',
                    recipientProviders: [
                        developers(),
                        requestor()
                    ]
                )
            }
        }
    }
}
