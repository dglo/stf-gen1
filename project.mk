C_BIN_NAMES := menu stfserv
C_EXCLUDE_NAMES := gendir genqry mk-name stftcp stfclient
USES_PROJECTS := iceboot hal dom-loader
USES_TOOLS := libxml expat z m

ifeq ("epxa10","$(strip $(PLATFORM))")
  USES_TOOLS := $(filter-out libxml m, $(USES_TOOLS))
else
  USES_PROJECTS := $(filter-out dom-loader, $(USES_PROJECTS))
endif

# The normal include of standard.mk is not used here so that the 'clean' target
# does not create the BUILT_FILES needed to get the dependencies correct.
#include ../resources/standard.mk

project.mk: ;

Makefile: ;

force: ;

%: force
	@$(MAKE) -f ../resources/standard.mk PROJECT="$(PROJECT)" PLATFORM="$(PLATFORM)" ICESOFT_HOST="$(ICESOFT_HOST)" C_BIN_NAMES="$(C_BIN_NAMES)" C_EXCLUDE_NAMES="$(C_EXCLUDE_NAMES)" USES_PROJECTS="$(USES_PROJECTS)" USES_TOOLS="$(USES_TOOLS)" $@

clean:
	@$(MAKE) -f ../resources/standard.mk PROJECT="$(PROJECT)" PLATFORM="$(PLATFORM)" ICESOFT_HOST="$(ICESOFT_HOST)" C_BIN_NAMES="$(C_BIN_NAMES)" C_EXCLUDE_NAMES="$(C_EXCLUDE_NAMES)" USES_PROJECTS="$(USES_PROJECTS)" USES_TOOLS="$(USES_TOOLS)" BUILT_FILES="" BUILD_DEPENDS="" $@
