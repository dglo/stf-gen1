<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:stf="http://glacier.lbl.gov/icecube/daq/stf"
  xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output indent="yes" method="text"/>
  <xsl:template match="/">
    <xsl:apply-templates select="stf:test"/>
  </xsl:template>
  <xsl:template match="stf:test">
  <xsl:variable name="nl">
<xsl:text>
</xsl:text>
  </xsl:variable>
  <xsl:variable name="testName" select="name"/>
static STF_PARAM <xsl:copy-of select="$testName"/>_params[] = {
    <xsl:apply-templates mode="PARAM" select="inputParameter"/>
    <xsl:apply-templates mode="PARAM" select="outputParameter"/>
   { .name = "" }
};

extern BOOLEAN <xsl:copy-of select="$testName"/>Init(STF_DESCRIPTOR *);
extern BOOLEAN <xsl:copy-of select="$testName"/>Entry(STF_DESCRIPTOR *);

static STF_DESCRIPTOR <xsl:copy-of select="$testName"/>_descriptor = {
  .name = "<xsl:copy-of select="$testName"/>",
  .desc = "<xsl:value-of select="description"/>",
  .majorVersion = <xsl:value-of select="version/@major"/>,
  .minorVersion = <xsl:value-of select="version/@minor"/>,
  .testRunnable = 0,
  .nParams = <xsl:value-of select="count(inputParameter) + count(outputParameter)"/>,
  .params = <xsl:copy-of select="$testName"/>_params,
  .initPt = <xsl:copy-of select="$testName"/>Init,
  .entryPt = <xsl:copy-of select="$testName"/>Entry,
  .isInit = 0
};
<xsl:copy-of select="$nl"/>
  </xsl:template>
  <xsl:template match="stf:test/*/*" mode="PARAM">
   {
     .name = "<xsl:copy-of select="../name"/>",
     .class = "<xsl:apply-templates mode="class" select="."/>",
     .type = "<xsl:value-of select="local-name()"/>",
     .maxValue = <xsl:apply-templates mode="max" select="."/>,
     .minValue = <xsl:apply-templates mode="min" select="."/>,
     .defValue = <xsl:apply-templates mode="default" select="."/>,
     .arraySize = "1",
     .value = {
       .<xsl:apply-templates mode="value" select="."/>Value = <xsl:apply-templates mode="initialValue" select="."/>
     },
     .arrayLength = 1
   },
  </xsl:template>
  <xsl:template match="stf:test/*" mode="PARAM">
    <xsl:apply-templates mode="PARAM" select="boolean|string|unsignedInt|unsignedLong"/>
  </xsl:template>
  <xsl:template match="stf:test/*/*" mode="max">NULL</xsl:template>
  <xsl:template match="stf:test/inputParameter/unsignedInt|stf:test/inputParameter/unsignedLong" mode="max">"<xsl:value-of select="@maxValue"/>"</xsl:template>
  <xsl:template match="stf:test/*/*" mode="min">NULL</xsl:template>
  <xsl:template match="stf:test/inputParameter/unsignedInt|stf:test/inputParameter/unsignedLong" mode="min">"<xsl:value-of select="@minValue"/>"</xsl:template>
  <xsl:template match="stf:test/outputParameter/*" mode="default">NULL</xsl:template>
  <xsl:template match="stf:test/inputParameter/boolean|stf:test/inputParameter/string|stf:test/inputParameter/unsignedInt|stf:test/inputParameter/unsignedLong" mode="default">"<xsl:value-of select="@default"/>"</xsl:template>
  <xsl:template match="stf:test/inputParameter/*" mode="class">input</xsl:template>
  <xsl:template match="stf:test/outputParameter/*" mode="class">output</xsl:template>
  <xsl:template match="stf:test/*/boolean" mode="value">bool</xsl:template>
  <xsl:template match="stf:test/*/string" mode="value">char</xsl:template>
  <xsl:template match="stf:test/*/unsignedInt" mode="value">int</xsl:template>
  <xsl:template match="stf:test/*/unsignedLong" mode="value">long</xsl:template>
  <xsl:template match="stf:test/*/*" mode="initialValue">0</xsl:template>
  <xsl:template match="stf:test/*/string" mode="initialValue">""</xsl:template>
  <xsl:template match="stf:test/inputParameter/*" mode="initialValue"><xsl:value-of select="@default"/></xsl:template>
  <xsl:template match="stf:test/inputParameter/boolean[@default='false']" mode="initialValue">0</xsl:template>
  <xsl:template match="stf:test/inputParameter/boolean[@default='true']" mode="initialValue">1</xsl:template>
  <xsl:template match="stf:test/inputParameter/string" mode="initialValue">"<xsl:value-of select="@default"/>"</xsl:template>
</xsl:stylesheet>
