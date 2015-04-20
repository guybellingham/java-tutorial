This Applet can also be run from the command line as an Application.
Its JAR file has a META-INF/manifest.mf file, with a Main-Class: header, so that the JVM knows which class has the main(String[] s) method in it.

Use WinZip to view the Applet1.jar file.
This is just to illustrate that the JAR tool was used to update the manifest file for this Applet with a Main-Class: header, so it can be run from the jar file :

-jar appletsecurity.Applet1

To update the jar, with your own manifest file, dbl-click the JAR shortcut and enter this parameter:

uvmf appletsecurity\manifest.mf appletsecurity\Applet1.jar

To RUN this applet as an application, dbl-click JAVA shortcut (ensure working directory is the folder that contains your packages), then type:

appletsecurity.Applet1