#
# adding a new test?  add the .o to TESTOBJS below...
#
.SUFFIXES: .c .o .S .elf .srec .xml .html .tab .bin

.c.o:
	$(CC) -c $(CFLAGS) $<

.S.o:
	$(CPP) $(CPPFLAGS) -o $*.i $<
	$(AS) $(AFLAGS) -o $*.o $*.i

.elf.bin:
	$(OBJCOPY) -O binary $*.elf $*.bin
	$(CPP) $(CPPFLAGS) -DBINFILE=\"$*.bin\" -o $*-raw.i $(RAWS)
	$(AS) $(AFLAGS) -o $*-raw.o $*-raw.i
	$(LD) --script=$(RAWX) -o $*-raw.elf $*-raw.o
	$(OBJCOPY) -O binary $*-raw.elf $*.bin

#
# add new tests here...
#
TESTOBJS  = ADC.o memory.o StfEg.o atwd_pedestal.o atwd_baseline.o \
	pmt_hv_ramp.o pmt_hv_stability.o pressure.o

XMLFILES  = $(TESTOBJS:.o=.xml)
CFILES    = $(TESTOBJS:.o=.c)

#
# generated links...
#
HDRFILES  = $(TESTOBJS:.o=.h)

all: stfserv.elf menu.elf

clean:
	rm -f *.o *.i *.bin *.elf *.srec \
		gendir stfDirectory.[ch] \
		memory.h atwd_pedestal.h adwd_baseline.h \
		StfEg.h $(HDRFILES)

STFOBJS = $(TESTOBJS) ../stf/stf.o stfDirectory.o memtests.o stfUtils.o
KOBJS = ../lib/crt0.o ../lib/libkernel.a

stfserv.elf: stfserv.o $(STFOBJS) $(LIBHAL)
	$(LD) --script=$(KERNELX) -o stfserv.elf \
		$(KOBJS) stfserv.o $(STFOBJS) $(LIBHAL) $(LIBEXPAT) $(SYSLIBS)

menu.elf: menu.o $(STFOBJS) $(LIBHAL)
	$(LD) --script=$(KERNELX) -o menu.elf \
		$(KOBJS) menu.o $(STFOBJS) $(LIBHAL) $(SYSLIBS)

../bin/%.bin.gz: %.bin; gzip -c $< > $@

gendir: gendir.c ../public/stf/stf.h
	gcc -o gendir -I../public \
		-I/usr/include/libxml2 gendir.c -Wall -g -lxml2

stfDirectory.h stfDirectory.c: $(XMLFILES) gendir Makefile
	./gendir -skeleton $(XMLFILES)

$(CFILES): %.c: %.h

$(HDRFILES): stfDirectory.h
	if [[ ! -h $@ ]]; then ln -s stfDirectory.h $@; fi












