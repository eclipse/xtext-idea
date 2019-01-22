pipeline {
  agent any

  options {
    buildDiscarder(logRotator(numToKeepStr:'15'))
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Gradle Build') {
      steps {
        sh './1-gradle-build.sh'
        step([$class: 'JUnitResultArchiver', testResults: '**/build/test-results/test/*.xml'])
      }
    }
  }

  post {
    success {
      archiveArtifacts artifacts: 'build/maven-repository/**'
    }
    changed {
      script {
        def envName = ''
        if (env.JENKINS_URL.contains('ci.eclipse.org/xtext')) {
          envName = ' (JIPP)'
        } else if (env.JENKINS_URL.contains('jenkins.eclipse.org/xtext')) {
          envName = ' (CBI)'
        } else if (env.JENKINS_URL.contains('typefox.io')) {
          envName = ' (TF)'
        }
        
        def curResult = currentBuild.currentResult
        def color = '#00FF00'
        if (curResult == 'SUCCESS' && currentBuild.previousBuild != null) {
          curResult = 'FIXED'
        } else if (curResult == 'UNSTABLE') {
          color = '#FFFF00'
        } else if (curResult == 'FAILURE') {
          color = '#FF0000'
        }
        
        slackSend message: "${curResult}: <${env.BUILD_URL}|${env.JOB_NAME}#${env.BUILD_NUMBER}${envName}>", baseUrl: 'https://itemis.slack.com/services/hooks/jenkins-ci/', botUser: true, channel: 'xtext-builds', color: '#00FFFF', token: '1vbkhv8Hwlp3ausuFGj1BdJb'
      }
    }
  }
}
