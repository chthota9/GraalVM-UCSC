Graal VM UCSC plugin code
# Intellij Call Trace Plugin

Use this folder to run the Intellij Call Trace Plugin. You can test the plugin using gradle.

## Instructions

Open the project in Intellij. Edit the configurations to run with Gradle.
Select this folder in configurations selection, "Gradle Project",
and in tasks, input:

```
:runIde
```

The json file path must be manually inputted into the program files.
In MethodCheck.java and Factory.java, copy your file path to the absolute path line in the code.
Such that:
```
String filepath = *Your/file/path*
```