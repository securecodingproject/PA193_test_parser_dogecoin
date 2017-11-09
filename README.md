# PA193_test_parser_dogecoin

Travis CI build status: [![Build Status](https://travis-ci.org/securecodingproject/PA193_test_parser_dogecoin.svg?branch=master)](https://travis-ci.org/securecodingproject/PA193_test_parser_dogecoin)

## Build instructions

Maven is used to manage dependencies of, and compile this project (https://maven.apache.org/)
Building the project is as simple as running the following command from the project root (after installing maven of course):

`mvn package`

This will result in a jar file created in the target folder. You can run the file as such:

`java -jar target/PA_test_parser_dogecoin-0.1 <merkle root hash to look for> <file to look for it in>`

Tests can be run with:

`mvn test`