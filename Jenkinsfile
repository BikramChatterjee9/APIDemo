pipeline 
{
    agent any
    
    tools{
        maven 'maven'
        }

    stages 
    {
        stage('Regression API Automation Tests on QA') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/BikramChatterjee9/APIDemo.git'
                    bat "mvn clean test -DsuiteXmlFile=testng.xml"
                    
                }
            }
        }
    }
}