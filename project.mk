C_BIN_NAMES := menu stfserv
C_EXCLUDE_NAMES := gendir genqry mk-name stftcp stfclient
USES_PROJECTS := hal dom-loader
USES_TOOLS := libxml expat z m
CC_FLAGS := -g


ifneq ("epxa10","$(strip $(PLATFORM))")
  USES_PROJECTS := $(filter-out dom-loader, $(USES_PROJECTS))
else
  USES_TOOLS := $(filter-out libxml, $(USES_TOOLS))
  C_BIN_NAMES :=
  C_EXCLUDE_NAMES := stfserv gendir genqry mk-name stftcp menu stfclient
endif

include ../resources/standard.mk
