#
# common routines/variables for all shell scripts...
#

#
# set proper paths...
#
PATH=/bin:/usr/bin/:/usr/lib/cgi-bin/stf:/usr/lib/cgi-bin/stf/xml/bin
PATH=${PATH}:/usr/local/bin:/usr/local/j2sdk/bin
export PATH

awkpath=/usr/lib/cgi-bin/stf/xml/bin

xmlpath=/var/www/stf/xml

function qryCookie() {
    awk 'BEGIN { FS="="; RS=";[ ]*"; } { printf "%s\t%s\n", $1, $2; }' | \
     grep -e "^$1	" | sed -n "s/^$1	//1p"
}

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

#
# set the mysql command...
#
mysqlcmd="mysql -h glacier.lbl.gov -pt35Tpas5 -u tester domprodtest -s -e"

#
# java command
#
javacmd="java -classpath /usr/lib/cgi-bin/stf/xml/bin"


#
# view results from a result id...
#
#
# FIXME: this should be _much_ better, at least get rid of xmlv step...
#
function viewResults() {
#
# qry in case we don't want to use
# the resultxml...
#
    local qry=`printf \
    "select distinct tt.name, p.name, rp.value, rp.value_index from \
     STFTestType tt, STFTest t, STFResult r, STFParameter p, \
     STFParameterType pt, STFResultParameter rp \
   where \
     r.stf_result_id=%s and \
     rp.stf_result_id=%s and \
     rp.stf_param_id=p.stf_param_id and \
     r.stf_test_id=t.stf_test_id and \
     t.stf_testtype_id=tt.stf_testtype_id \
   order by \
     p.name;" ${fname} ${fname}`

    local tabth=/usr/lib/cgi-bin/stf/xml/bin/tabtohtml.awk
    local restt=/usr/lib/cgi-bin/stf/xml/bin/restotab.awk
    local qry=`printf \
	"select text from STFResultXML where stf_result_id=%s" $1`
    ${mysqlcmd} "${qry}" | xmlv | awk -f ${restt} | \
	awk -v xml=$1 -f ${tabth}
}
