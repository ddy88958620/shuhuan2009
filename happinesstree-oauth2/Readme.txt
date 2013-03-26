1. Import the maven modules as "existing maven projects"

2. convert your projects into eclipse if you are using eclipse:
   mvn eclipse:eclipse

3. build and package the code for test environment:
   mvn clean package  -Dmaven.test.skip=true -DAPP_ENV=test
   
4. build and package the code for product environment:
   mvn clean package  -Dmaven.test.skip=true -DAPP_ENV=product