#
# common routines/variables for all shell scripts...
#

#
# set proper path...
#
xpath=/usr/local/bin
xpath=${xpath}:/e/apps/xerces-c2_1_0-win32/bin
xpath=${xpath}:/e/apps/xml-xalan/c/Build/Win32/VC6/Release
PATH=/d/apps/msys-1.0/bin:/d/apps/mingw/bin:/e/arthur/xml/bin:${xpath}

xmlpath=e:\\arthur\\xml

#
# splitParams, split the paramString into lines, translate
#  as we go...
#
function splitParams() {
    tr '+' ' ' | awk 'BEGIN { FS="="; RS="&" } { printf "%s\t%s\n", $1, $2; }'
}

#
# qryParam param
#
#  get a parameter from a qry string (in stdin)
#
# format: v=val&v2=val2&v3=val3+val3
#
# + is replaced by ' '
#
# test is a special case parameter, strip off the "Run " in
# the beginning...
#
function qryParam() {
    local parm=`splitParams | \
	grep -e "^$1	" | \
	sed -n "s/^$1	//1p"`

    if [ $1 == "test" ]; then
	echo $parm | sed -n 's/^Run //1p'
    else
	echo $parm
    fi
}

#
# print the current time
#
function prtTime() { 
    awk 'BEGIN { printf "%s", systime() }' 
}
