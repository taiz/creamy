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

:: �W���o�͂Ƀ��b�Z�[�W��\�����Ȃ�
@echo off

:: �N���X�p�X���w�肵�Ȃ���v���O�������s
:: �p�����[�^�́A0:���o�b�`�t�@�C���̃f�B���N�g�� 1: new or scaffold or destroy 3�ȍ~: �e�R�}���h���Ƃ̃p�����[�^
java -classpath .;.\lib\commons-io-2.4.jar;.\lib\velocity-1.7-dep.jar -jar %~dp0creamy_tools.jar %~dp0 %1 %2 %3 %4 %5 %6 %7 %8
