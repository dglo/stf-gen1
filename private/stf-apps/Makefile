AFLAGS = -mcpu=arm920t
CFLAGS = -mlittle-endian -mcpu=arm920 -Wall \
	-nostdinc -I/usr/local/arm-elf/arm-elf/include \
	-I/usr/local/arm-elf/lib/gcc-lib/arm-elf/3.2/include \
	-I../expat -I../../include -I../../../hal/include

CPP = arm-elf-cpp
CC = arm-elf-gcc
AS = arm-elf-as
AS = arm-elf-ar
LD = arm-elf-ld -N
OBJCOPY = arm-elf-objcopy

SYSLIBS = /usr/local/arm-elf/arm-elf/lib/libc.a \
	/usr/local/arm-elf/lib/gcc-lib/arm-elf/3.2/libgcc.a

.SUFFIXES: .c .o .S .elf .srec .xml .html

.c.o:
	$(CC) -c $(CFLAGS) $<

.S.o:
	$(CPP) $(CPPFLAGS) -o $*.i $<
	$(AS) $(AFLAGS) -o $*.o $*.i

.elf.srec:
	$(OBJCOPY) -O binary $*.elf $*.bin
	$(CPP) $(CPPFLAGS) -DBINFILE=\"$*.bin\" -o $*-raw.i raw.S
	$(AS) $(AFLAGS) -o $*-raw.o $*-raw.i
	$(LD) --script=raw.x -o $*-raw.elf $*-raw.o
	$(OBJCOPY) -O srec $*-raw.elf $*.srec

.xml.html:
	Xalan -o $*.html $< restohtml.xsl

all: stfserv.elf

clean:
	rm -f *.o *.i *.bin *.elf *.srec gendir genqry stfDirectory.c

TESTOBJS = memory.o StfEg.o
STFOBJS = $(TESTOBJS) stfserv.o ../stf/stf.o stfDirectory.o
XMLFILES = memory.xml StfEg.xml

stfserv.elf: $(STFOBJS) 
	$(LD) -r -o stfserv.elf \
		$(STFOBJS) \
		../expat/xmlparse/libexpat.a \
		../../../lib/epxa10/libhal.a \
		$(SYSLIBS)

gendir: gendir.c ../../include/stf/stf.h
	gcc -o gendir -I../../include -I../../../hal/include \
		-I/usr/include/libxml2 gendir.c -Wall -g -lxml2

genqry: genqry.c
	gcc -o genqry -I/usr/include/libxml2 genqry.c -Wall -g -lxml2

run-tests.html: genqry $(XMLFILES)
	./genqry $(XMLFILES)

stfDirectory.c: $(XMLFILES) gendir
	./gendir $(XMLFILES)

test-cgi.exe: test-cgi.c
	gcc -Wall -o test-cgi.exe test-cgi.c

stf.exe: stf.c
	gcc -o stf.exe -Wall -I../../include stf.c

CGIDIR = c:/program files/apache group/apache2/cgi-bin

install: test-cgi.exe test-cgi.exe
	cp test-cgi.exe test-cgi.exe "$(CGIDIR)"

memory.html: memory.xml restohtml.xsl
	xalan -IN memory.xml -OUT memory.html -HTML -XSL restohtml.xsl -Q

StfEg.h: StfEg.xml stfDefn2Header.xsl
	xalan -IN StfEg.xml -OUT StfEg.h -XSL stfDefn2Header.xsl -Q

html-install: run-tests.html
	cp *.html ${HOME}/public_html/stf

CGIS = run-test
CGIHELPERS = show-time.exe mk-name.exe get-test-name.exe mk-test-xml.exe \
	stf.exe
XSLFILES = restohtml.xsl restosum.xsl

cgi: $(CGIS) $(CGIHELPERS)

cgi-install: $(CGIS) $(CGIHELPERS) $(XSLFILES)
	cp $(CGIS) "/c/program files/apache group/apache2/cgi-bin"
	cp $(CGIHELPERS) "/e/arthur/xml/bin"
	cp $(XSLFILES) "/e/arthur/xml"

show-time.exe: show-time.c
	gcc -o show-time.exe -Wall show-time.c

mk-name.exe: mk-name.c
	gcc -o mk-name.exe -Wall mk-name.c

get-test-name.exe: get-test-name.c parse-qry.c
	gcc -o get-test-name.exe -Wall get-test-name.c parse-qry.c

mk-test-xml.exe: mk-test-xml.c parse-qry.c
	gcc -o mk-test-xml.exe -Wall mk-test-xml.c parse-qry.c
