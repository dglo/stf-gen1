ifeq ("epxa10","$(strip $(PLATFORM))")
  C_BIN_NAMES := 
  C_EXCLUDE_NAMES := menu stfserv gendir genqry mk-name stftcp stf stflient
  USES_TOOLS := expat z m
else
  C_BIN_NAMES := gendir genqry menu stfserv
  C_EXCLUDE_NAMES := stf stflient
  USES_TOOLS := libxml expat z m
endif
USES_PROJECTS := hal

include ../resources/standard.mk
