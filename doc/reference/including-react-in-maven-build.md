# Including React in your Spring Boot maven build

From: https://medium.com/@itzgeoff/including-react-in-your-spring-boot-maven-build-ae3b8f8826e

by Geoff Bourne

Jul 14, 2018 · 4 min read

I call myself a “fullstack developer”, but really I’m a backend, Java developer that also likes to write frontend code. As such, I often develop applications starting first with my favorite application framework: Spring Boot.

You can build Spring Boot apps with Gradle or Maven, but I remain a fan of the latter due Maven’s declarative approach to build specifications.

On the front-end side, by far the best way to start a new React application is with create-react-app and it in turn prefers the use of yarn to build the React application.

At this point we’re faced with a dilemma where we have two best-of-breed, but disparate build tools. It turns out we can have both: use maven for the “outer” build and have a maven plugin take care of the yarn invocation. I’m sure it can be attacked from yarn invoking maven…but, did I mention I’m a backend developer?

## Project Structure

It turns out there’s very little you need to do differently to prepare your project’s structure for this hybrid build.

Since Maven is used as the primary build tool, we should follow it’s convention of src/main/<type> for situating production code to be built. You could call that <type> whatever you want, but I usually go with “app”. Something like “ui” or “frontend” are good choices too.

If you haven’t yet run create-react-app to initialize your React, then run it like normal from within the src/main directory:

```bash
cd src/main
npx create-react-app myNewApp
```

That will create a directory named for the application name you passed; however, the React build scripts don’t care if that directory is renamed to something else. As such, rename your generated application directory to “app”:

```bash
mv myNewApp app
```

If you had already generated the React code, then just move that directory to be src/main/app.

Now you can develop and run the front-end just like normal from within the src/main/app directory:

```bash
cd src/main/app
yarn run start
```

Please note that create-react-app has built in support for development-time proxy’ing the API calls via the typical NodeJS port of 3000 over to your Spring Boot web controllers on port 8080. When running the packaged jar in production, etc you don’t need to use the proxy mechanism since the Spring Boot embedded web server will be serving up the frontend, React content.

## Setup the maven build of React code

We need to tell Maven to do two things for us:

0. Build the React code via yarn
0. Place the result of the React build into the right spot for the packaged Spring Boot app

### Declare the tool versions

In your pom.xml locate or create the project > properties section and add the following properties:

```xml
<properties>
    <frontend-src-dir>${project.basedir}/src/main/app</frontend-src-dir>
    <node.version>v12.3.1</node.version>
    <yarn.version>v1.16.0</yarn.version>
    <frontend-maven-plugin.version>1.7.6</frontend-maven-plugin.version>
</properties>
```
> **NOTE**: Be sure to use the latest version of each item listed above since the versions shown here are only for example purposes.

The plugin we’ll add in the next step will take care of downloading NodeJS and Yarn for those versions declared, so **you don’t even need to worry about pre-installing those** on the final build machine (i.e. CI tool). Very handy, right?

### Add the plugin to run yarn

Again, in your pom.xml locate or create the project > build > plugins section and add the following plugin definition:

```xml
<plugin>
    <groupId>com.github.eirslett</groupId>
    <artifactId>frontend-maven-plugin</artifactId>
    <version>${frontend-maven-plugin.version}</version>

    <configuration>
        <nodeVersion>${node.version}</nodeVersion>
        <yarnVersion>${yarn.version}</yarnVersion>
        <workingDirectory>${frontend-src-dir}</workingDirectory>
        <installDirectory>${project.build.directory}</installDirectory>
    </configuration>

    <executions>
        <execution>
            <id>install-frontend-tools</id>
            <goals>
                <goal>install-node-and-yarn</goal>
            </goals>
        </execution>

        <execution>
            <id>yarn-install</id>
            <goals>
                <goal>yarn</goal>
            </goals>
            <configuration>
                <arguments>install</arguments>
            </configuration>
        </execution>

        <execution>
            <id>build-frontend</id>
            <goals>
                <goal>yarn</goal>
            </goals>
            <phase>prepare-package</phase>
            <configuration>
                <arguments>build</arguments>
            </configuration>
        </execution>
    </executions>
</plugin>
```

That plugin configuration will work generically for most applications. The plugin executions are taking care of three sub-steps for you:

0. Installing NodeJS and Yarn local to the project (in target/node if you’re curious)
0. Running `yarn install` to ensure the frontend dependencies are installed
0. Running `yarn build` to perform the optimized production build of the React code

If you’re wondering how this seamlessly weaves into the overall Spring Boot build process, then the use of theprepare-package phase for the last execution of that plugin is important to ensure that the React bits are ready right before the final jar is created.

### Stage React build output into final location

The last pom.xml modification is to add the following plugin configuration after the previous plugin to the project > build > plugins section:

```xml
<plugin>
    <artifactId>maven-resources-plugin</artifactId>
    <version>3.0.1</version>
    <executions>
        <execution>
            <id>position-react-build</id>
            <goals>
                <goal>copy-resources</goal>
            </goals>
            <phase>prepare-package</phase>
            <configuration>
                <outputDirectory>${project.build.outputDirectory}/static</outputDirectory>
                <resources>
                    <resource>
                        <directory>${frontend-src-dir}/build</directory>
                        <filtering>false</filtering>
                    </resource>
                </resources>
            </configuration>
        </execution>
    </executions>
</plugin>
```

This adds an execution of the built-in resources plugin to copy over the React build output from src/main/app/build into the /static path of the jar’s staging directory, target/classes. That is where the embedded Spring Boot web container expects index.html and supporting files to reside. After a mvn package run you can even look in target/classes/static to see how the usual web content ended up:

The static/static path is a bit odd, but that’s just a quirk of both Spring Boot and React build tools using the same naming convention.

## Run the build

This step is super easy since it is no different than any other Spring Boot build:

```bash
mvn package
```

You can run the resulting jar just like any other Spring Boot app and then access the front-end code at http://localhost:8080 (or wherever you have it deployed):

```bash
java -jar target/your-app-VERSION.jar
```
