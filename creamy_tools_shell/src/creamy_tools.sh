::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::                                                                  ::
::  creamy �c�[���inew�Ascaffold�Adestroy�j                         ::
::  author Ryusaburo Tanaka (Professor Akiguchi's PBL 2012, AIIT)   ::
::                                                                  ::
::  ���s�R�}���h�� :                                                ::
::    creamy_tools new                                              ::
::    creamy_tools scaffold Company name:string num_branch:integer  ::
::    creamy_tools destroy Company                                  ::
::                                                                  ::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

:: �N���X�p�X���w�肵�Ȃ���v���O�������s
:: �p�����[�^�́A0:���V�F���X�N���v�g�̃f�B���N�g�� 1: new or scaffold or destroy 3�ȍ~: �e�R�}���h���Ƃ̃p�����[�^
execdir	= dirname $0
execapp	= ${execdir}/creamy_tools.jar
java -classpath .;./lib/commons-io-2.4.jar;./lib/velocity-1.7-dep.jar -jar $execapp $execdir $1 $2 $3 $4 $5 $6 $7 $8 2>/dev/null
