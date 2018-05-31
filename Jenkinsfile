

pipeline{
   
        properties([buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '')), [$class: 'EnvInjectJobProperty', info: [loadFilesFromMaster: false, propertiesContent: 'aaa=\'\'', secureGroovyScript: [classpath: [], sandbox: false, script: '']], keepBuildVariables: true, keepJenkinsSystemVariables: true, on: true]])
    
    stage('scm'){
        checkout scm
    }
    stage('build'){
        withMaven(){
            
        }
    }
}
