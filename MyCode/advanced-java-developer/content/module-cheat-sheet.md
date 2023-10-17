<img src="https://github.com/stayahead-training/shared/blob/master/stayahead.png" />

# Module Cheat Sheet

[<< back](../README.md)

## Commands

|Description|Syntax|
|-----------|------|
|Compile a module|**javac** -p *moduleDirectory* -d *outputDirectory* *classesIncludingModuleInfo*|
|Run a module|**java** -p *moduleDirectory* -m *moduleName/package.class*|
||**java** -jar -p *jarName* -m *moduleName/package.class*|
|Describe a module|**java** -p *moduleDirectory* -d *moduleName*|
||**jar** -f *jarName* -d|
|List dependencies|**jdeps** *moduleDirectory/jarName*|

## Module Types

|Module Type|Description|
|-----------|-----------|
|Named|A module containing a module-info.java file in the root of the JAR alongside other packages. A named module exists on the module path, not on the classpath.|
|Automatic|A module that does NOT contain a module-info.java file but that is on the module path. Java *automatically* determines the name of such a module and exports all of its packages.|
|Unnamed|A module that may or may not contain a module-info.java file but that is NOT on the module path. Unnamed modules export nothing but can read from any JAR on the classpath or module path.|


## Migration

|Strategy|Description|
|--------|-----------|
|Bottom-up|Put the lib with no dependencies (the one at the bottom of the tree) on the module path and name it. The other libs remain on the classpath and so are unnamed. The unnamed modules (those yet to be migrated) can read the named module. Repeat the process with the unnamed module at the bottom of the tree until you've no more unnamed modules.|
|Top-down|Put all the libs on the module path and name the one with the most dependencies (the one at the top of the tree). The other libs are automatic. Add a module-info.java file to the named module and list its dependencies using the modules' automatically assigned names. Repeat the process with the automatic module at the top of the tree until you've no more automatic modules.|

[<< back](../README.md)