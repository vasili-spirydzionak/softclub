####  `SOFTCLUB UI AUTOMATION`
Test project created for demo purposes of ui-automation and consists of test suite wit test cases which verify
 functionality of gmail inbox.
 
####  `PROJECT STRUCTURE`
Project consists of the following layers:
> - `test cases` are placed in *com.softclub.test* package.
> - `page objects` are placed in *com.softclub.page* package.
> - `html elements` locator container classes in *com.softclub.page.element* package.
> - `data transfer objects` entity classes for storing fixture data in *com.softclub.page.dto* package.
> - `csv repository` class containing functionality for reading fixture test data in *com.softclub.page.csv* package.
> - `reporting` is implemented by extent report in overriden test-listener class in *com.softclub.page.report*
> - `util` clases in *com.softclub.page.util* package.
> - `configuration` is placed on *com.softclub.page.config* package.

### `START GUIDE`
In order to run the project please enter the following cmd line in terminal in the base folder:
* `mvnw clean install -Dbrowser=<BROWSER> -Dlogin=<GMAIL> -Dpassword=<PASSWORD>`
* \<BROWSER\> - browser name. **In terms of demo Chrome browser provided**.
* \<GMAIL\> - gmail login.
* \<PASSWORD\> - gmail password.