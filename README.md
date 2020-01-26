# Equal Experts Hotel Booking Test

This test suite runs against the Equal Experts hotel booking test site located at http://hotel-test.equalexperts.io 

It can be run normally via:

## Intellij
Run `TestMain.main()`

The serenity test will run using the default browser as defined in serenity.conf. This can be overridden using VM option -Dwebdriver.driver=[browser]. See below for supported browsers and OSs.

## Maven
`mvn clean verify`

The same -Dwebdriver.driver=[browser] options are available.

## Standalone JAR
The tests are also exportable as a standalone JAR plus webdriver executables.

To create the JAR run `mvn clean compile package`. This will result in a JAR file ending in '-tests' in the target folder. 

Copy this to a folder along with the whole webdriver folder (`src/test/resources/webdriver`). Make sure this is in the same folder structure i.e. src/test/resources/webdriver (This is currently necessary as I haven't been able to override serenity using the directory java is run from as the base path and maven assembly doesn't seem to want to bundle the drivers in anyway)

Open a terminal in this newly created folder and run `java -jar [nameOfJar].jar`. (Java 8+) 

This will execute the tests in the default browser. Again you can pass a VM option to override this. e.g. `java -Dwebdriver.driver=firefox -jar [nameOfJar].jar`

## Supported browsers and OSs
Supported browser and OS combinations:

* linux
    * chrome
    * firefox
* mac
    * chrome
    * firefox
* windows
    * chrome
    * firefox
