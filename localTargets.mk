$(BUILD_DIR)/$(PUB_DIR_NAME)/%.h : %.xml
	@test -d $(@D) || mkdir -p $(@D)
	$(XSLT) -in $< -out $@ -xsl $(<D)/stfDefn2Header.xsl

$(BUILD_DIR)/$(PVT_DIR_NAME)/%.h : %.xml
	@test -d $(@D) || mkdir -p $(@D)
	$(XSLT) -in $< -out $@ -xsl $(<D)/stfDefn2Header.xsl
