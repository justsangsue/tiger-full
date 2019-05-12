OUT=output
JC = javac
JVM= java

.SUFFIXES: .java .class

.java.class:
		$(JC) $*.java

CLASSES = \
		main.java


MAIN = main

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN)


clean:
		$(RM) *.class
