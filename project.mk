ifeq ("epxa10","$(strip $(PLATFORM))")
  C_BIN_NAMES := 
  C_EXCLUDE_NAMES := stfserv gendir genqry mk-name stftcp menu stfclient
  USES_TOOLS := expat z m
else
  C_BIN_NAMES := gendir genqry menu stfserv
  C_EXCLUDE_NAMES := stfclient
  USES_TOOLS := libxml expat z m
endif
USES_PROJECTS := hal

include ../resources/standard.mk
