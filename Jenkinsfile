pipeline {
  agent any

  tools {
    jdk 'java-11-openjdk'
    maven 'mvn-3.6.0'
  }

  stages {
    stage('Build') {
      steps {
        withMaven(maven : 'mvn-3.6.0') {
          sh 'mvn package'
        }
      }
    }
        
    stage('Create and push container') {
      steps {        
          withMaven(maven : 'mvn-3.6.0') {
            sh 'mvn dockerfile:build dockerfile:push'
          }
        }
      }
          
    
  }
}
      