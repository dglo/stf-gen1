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
    "select \
       tt.name, p.name, rp.value, p.is_output, pt.name \
     from \
       STFResult as r \
     left join STFResultParameter 
 STFResultParameter as rp, STFTestType as tt, \
     STFTest as t, STFParameter as p \
     STFParameterType as pt,  \
   where \
     r.stf_result_id=268 and \
     rp.stf_result_id=268 and \
     rp.stf_param_id=p.stf_param_id and \
     r.stf_test_id=t.stf_test_id and \
     t.stf_testtype_id=tt.stf_testtype_id and \
     rp.value_index=1 \
   order by \
     p.name;" ${fname} ${fname}`

    local tabth=${awkpath}/tabtohtml.awk
    local restt=${awkpath}/restotab.awk
    local qry=`printf \
	"select text from STFResultXML where stf_result_id=%s" $1`
    ${mysqlcmd} "${qry}" | xmlv | awk -f ${restt} | \
	awk -v xml=$1 -f ${tabth}
}

function getSession() {
    echo $HTTP_COOKIE | qryCookie stfsession
}

function closeSession() {
    local session=`getSession`
    if [[ -d /tmp/stf/${session} ]]; then
	rm -rf /tmp/stf/${session}
    fi
}

function newSession() {
    local session=`mk-name`

    #
    # are we initialized...
    #
    if [[ ! -d /tmp/stf ]]; then
	mkdir /tmp/stf
    fi

    #
    # clear old session
    #
    if [[ ! -d /tmp/stf/${session} ]]; then
	rm -rf /tmp/stf/${session}
    fi

    #
    # make the session directory
    #
    mkdir /tmp/stf/${session}

    echo ${session}
}

#
# print the currently selected dhtab info
#
function currentPick() {
    local session=`echo $HTTP_COOKIE | qryCookie stfsession`
    local pick=`getPick`

    if [[ ${pick} == 0 ]]; then
	head -1 ${xmlpath}/servers
    else
	sed -n `printf "%dp" ${pick}` /tmp/stf/${session}/dhtab
    fi
}

#
# print the currently selected server...
#
function currentServer() {
    currentPick | awk '{print $1; }'
}

#
# print the currently selected port...
#
function currentPort() {
    currentPick | awk '{print $2; }'
}

#
# select a server, given line number of dhtab file
#
function setPick() {
    local session=`echo $HTTP_COOKIE | qryCookie stfsession`
    echo $1 > /tmp/stf/${session}/pick
}

#
# get the current pick or 0
#
function getPick() {
    local session=`echo $HTTP_COOKIE | qryCookie stfsession`
    local pick=`cat /tmp/stf/${session}/pick`
    if [[ ${#pick} == 0 ]]; then
	echo 0
    else
	echo ${pick}
    fi
}



