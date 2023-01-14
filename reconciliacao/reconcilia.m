clc;
clear;
close all;

% Medidas
m=[161 79 80]

% Tolerâncias
p=[0.05 0.01 0.01 ]

% tolerâncias absolutas
a=m.*p

% Calcula vetor
% mpeso =[ 2*m(1)/a(1)^2;
%                   2*m(2)/a(2)^2;
%                   2*m(3)/a(3)^2;
%                 0]
%
mpeso = 2*[m./(a.*a) 0]'

Diag1 = 2*inv(diag(a)^2)

% Forma matriz de pesos
%Peso = [2/a(1)^2        0                   0                  1;
%              0                      2/a(2)^2     0                 -1;
%              0                      0                  2/a(3)^2    -1;
%              1                     -1                 -1                  0]

b = [1 -1 -1]
Peso= [Diag1 [1 -1 -1]'; b 0]
InvPeso = inv(Peso)
% Calcula medidas reconciliadas, multiplicadores %  de Lagrange
Result = InvPeso * mpeso

% Medidas reconciliadas

mrec=[Result(1) Result(2) Result(3)]
lambda = Result(4)
Correcao = m - mrec
