##News_Feed

## Launch the application

With the application is included a makefile with the basic commands to launch the application

- ``make run-docker``: runs the application in a docker container
- ``make run``: runs the application

Once the application is started, to call the endpoint:

    POST http://localhost:8080/api/v1/api/v1/entries

Other command included in the makefile

- ``make test``: runs all the test

- ``make test-mutation``: runs a mutation test, the report is available in the directory
  /build/reports/pitest/index.html