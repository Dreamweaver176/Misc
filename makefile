JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
    $(JC) $(JFLAGS) $*.java

CLASSES = \
    Driver.java
    DataFile.java \
    Drink.java \
    Item.java \
    Snack.java \
    Vending.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
    $(RM) *.class
    
run:
    java Driver