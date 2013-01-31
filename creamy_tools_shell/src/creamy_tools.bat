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

:: 標準出力にメッセージを表示しない
@echo off

:: クラスパスを指定しながらプログラム実行
:: パラメータは、0:当バッチファイルのディレクトリ 1: new or scaffold or destroy 3以降: 各コマンドごとのパラメータ
java -classpath .;.\lib\commons-io-2.4.jar;.\lib\velocity-1.7-dep.jar -jar %~dp0creamy_tools.jar %~dp0 %1 %2 %3 %4 %5 %6 %7 %8
