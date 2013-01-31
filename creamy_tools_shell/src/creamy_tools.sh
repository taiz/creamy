::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::                                                                  ::
::  creamy ツール（new、scaffold、destroy）                         ::
::  author Ryusaburo Tanaka (Professor Akiguchi's PBL 2012, AIIT)   ::
::                                                                  ::
::  実行コマンド例 :                                                ::
::    creamy_tools new                                              ::
::    creamy_tools scaffold Company name:string num_branch:integer  ::
::    creamy_tools destroy Company                                  ::
::                                                                  ::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

:: クラスパスを指定しながらプログラム実行
:: パラメータは、0:当シェルスクリプトのディレクトリ 1: new or scaffold or destroy 3以降: 各コマンドごとのパラメータ
execdir	= dirname $0
execapp	= ${execdir}/creamy_tools.jar
java -classpath .;./lib/commons-io-2.4.jar;./lib/velocity-1.7-dep.jar -jar $execapp $execdir $1 $2 $3 $4 $5 $6 $7 $8 2>/dev/null
