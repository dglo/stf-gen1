# Add auto-generated include files to list of includes
#   Build list be translating XML file names into include file names.
XML_HDRS_SEARCH := $(patsubst %,%/*.xml, $(PUB_DIRS) $(PVT_DIRS))
XML_HDRS := $(wildcard $(XML_HDRS_SEARCH))
BUILT_HDRS := $(patsubst %.xml, $(BUILD_DIR)/%.$(C_INC_SUFFIX), $(XML_HDRS))

INCS := $(INCS)

BUILT_FILES := $(BUILD_DIR)/$(PVT_DIR_NAME)/stf-apps/stfDictionary.c $(BUILT_HDRS)

vpath %.xml $(PUB_DIR_NAME)/$(PLATFORM) $(PUB_DIR_NAME) $(PVT_DIR_NAME)/$(PLATFORM) $(PVT_DIR_NAME)
vpath %.xsd $(PUB_DIR_NAME)/$(PLATFORM) $(PUB_DIR_NAME) $(PVT_DIR_NAME)/$(PLATFORM) $(PVT_DIR_NAME)
