# Frontend Setup
In order to compile all code and setup all dependencies required to render the website data, a few installation steps are necessary.
First, if not already installed, you will need to install the following components on your local OS:
* Node.js
* NPM

Node.js allows you to compile and run a JavaScript project. Necessary, as nearly all parts that make up the frontend are written in JavaScript. NPM on the other hand (which stands Node Package Manager) is an environment that provides, among other applications, tools to run, build and test JavaScript components. For this reason it is used in combination with Node.

### Installation for Windows
Node.js can be downloaded from the browser. Following this link https://nodejs.org/en/download/ download the latest version of Node.js. Follow the description on the website and of the installer during the installation. The NPM is included as well.
After accepting terms and conditions and finishing the installation, check wether Node has been installed properly by checking its version in the command line:
```sh
node -v
```

### Installation for Ubuntu
For Ubuntu systems there are different ways to perform the installation. Here the most direct way through the terminal will be shown. Open the terminal and type the following:
```sh
sudo apt update
sudo apt install nodejs npm
```
Just like for Windows, verify that the package has been installed by checking the version with
```sh
nodejs --version
```
This should output the version number of the currently installed node version. If any problems should occur during the process, refer to this website https://linuxize.com/post/how-to-install-node-js-on-ubuntu-20-04/. Here alternative methods are also presented to install Node.js and NPM.

### Clone Project
After this, the next step is to clone this project in order to have all necessary code and resources for building the frontend locally. Just like for the backend setup, you can either download the project's .zip or clone it via the
```sh
   git clone https://github.com/informatik-heilbronn/transparenz-cockpit
```
command.

### Build and Run
Next you'll have to open the command line again. For Windows open either git or Powershell. For Ubuntu the normal terminal will do. Head into the directory of the cloned project with
```sh
cd <project directory>
```
Now do the first project build with
```sh
gradle frontend:build
```
This will attempt to build the fronted directory, which should be contained directly within the project's root directory. There might occur some errors referring to missing gradle dependencies or missing configurations. In this this case refer to the backend build documentation, which describes how to set up gradle. A possible source for such errors lies within the configuration of the JVM for which gradle is configured to build. Here you must set the "Amazon Corretto Java 11" version as the selected JDK. In case you are working form within an IDE, this config can usually be found under Settings and then Settings for build tools (in this case gradle). 
If the JDK isn't installed on your system yet, do this first before proceeding (this is also described in the "JDK" section of the backend build documentation). Other JDKs may also work, but since these versions weren't used during testing, these instructions should not be reliably applied to them.

Next, since this will be the first time running the project, you won't be able to run it right away, as some additional components, such as graphics, need to be installed through NPM, without which the run will fail. To do this, from the current directory navigate now to the frontend directory with
```sh
cd ./frontend
```
Then do
```sh
npm install
```
which installs the missing resources. This step may take a while.
After this do
```sh
npm ci
```
which installs even more dependencies specified in the package-lock.json, also present in the directory.

Finally after having successfully executed the above steps, you may now run the frontend with
```sh
npm run dev
```
This will compile the source code and start rendering the website, which by default will be hosted locally on your machine for now.
