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

.elf.srec:
	$(OBJCOPY) -O binary $*.elf $*.bin
	$(CPP) $(CPPFLAGS) -DBINFILE=\"$*.bin\" -o $*-raw.i raw.S
	$(AS) $(AFLAGS) -o $*-raw.o $*-raw.i
	$(LD) --script=raw.x -o $*-raw.elf $*-raw.o
	$(OBJCOPY) -O srec $*-raw.elf $*.srec

#
# add tests to each here...
#
TESTOBJS  = ADC.o memory.o StfEg.o atwd_pedestal.o atwd_baseline.o \
	pmt_hv_ramp.o pmt_hv_stability.o pressure.o
XMLFILES  = ADC.xml memory.xml StfEg.xml atwd_pedestal.xml \
	atwd_baseline.xml pmt_hv_ramp.xml pmt_hv_stability.xml pressure.xml

all: stfserv.elf menu.elf

clean:
	rm -f *.o *.i *.bin *.elf *.srec \
		gendir stfDirectory.[ch] \
		memory.h atwd_pedestal.h adwd_baseline.h \
		StfEg.h

STFOBJS = $(TESTOBJS) ../stf/stf.o stfDirectory.o memtests.o stfUtils.o
KOBJS = ../lib/crt0.o ../lib/libkernel.a

stfserv.elf: stfserv.o $(STFOBJS)
	$(LD) --script=$(KERNELX) -o stfserv.elf \
		$(KOBJS) stfserv.o $(STFOBJS) $(LIBHAL) $(LIBEXPAT) $(SYSLIBS)

menu.elf: menu.o $(STFOBJS)
	$(LD) --script=$(KERNELX) -o menu.elf \
		$(KOBJS) menu.o $(STFOBJS) $(LIBHAL) $(SYSLIBS)

stfserv.bin: stfserv.o $(STFOBJS) $(LIBHAL)
	$(CC) -o stfserv.bin stfserv.o \
		$(STFOBJS) $(LIBHAL) $(LIBEXPAT) $(SYSLIBS)

menu.bin: menu.o $(STFOBJS) $(LIBHAL)
	$(CC) -o menu.bin menu.o $(STFOBJS) $(LIBHAL) $(SYSLIBS)

$(STFSERVBINGZ): stfserv.bin
	gzip -c stfserv.bin > $(STFSERVBINGZ)

$(MENUBINGZ): menu.bin
	gzip -c menu.bin > $(MENUBINGZ)

gendir: gendir.c ../public/stf/stf.h
	gcc -o gendir -I../public \
		-I/usr/include/libxml2 gendir.c -Wall -g -lxml2

stfDirectory.c: $(XMLFILES) gendir
	./gendir -skeleton $(XMLFILES)

stfDirectory.h: $(XMLFILES) gendir
	./gendir -skeleton $(XMLFILES)

StfEg.c: stfDirectory.h StfEg.h
memory.c: stfDirectory.h memory.h
ADC.c: stfDirectory.h
atwd_pedestal.c: stfDirectory.h atwd_pedestal.h
atwd_baseline.c: stfDirectory.h atwd_baseline.h
pmt_hv_ramp.c: stfDirectory.h pmt_hv_ramp.h
pmt_hv_stability.c: stfDirectory.h pmt_hv_stability.h
pressure.c: stfDirectory.h pressure.h
fadc_fe_pulser.c: stfDirectory.h

memory.h:
	ln -s stfDirectory.h memory.h

atwd_pedestal.h:
	ln -s stfDirectory.h atwd_pedestal.h

atwd_baseline.h:
	ln -s stfDirectory.h atwd_baseline.h

StfEg.h:
	ln -s stfDirectory.h StfEg.h

pressure.h:
	ln -s stfDirectory.h pressure.h

pmt_hv_ramp.h:
	ln -s stfDirectory.h pmt_hv_ramp.h

pmt_hv_stability.h:
	ln -s stfDirectory.h pmt_hv_stability.h

fadc_fe_pulser.h:
	ln -s stfDirectory.h fadc_fe_pulser.h
