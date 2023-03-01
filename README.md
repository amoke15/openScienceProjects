# Compilador-PDL
#### Proyecto para Procesadores de lenguajes Universidad Politécnica de madrid 2022
- Adrián Olmos Amador: b19051
- Daniel Fernández Calvo : a180268
- Javier Isasa Campos : a180267

## Tabla de Contenidos 
- [Caracteristicas](#características)
### Analizador Léxico
* [Tokens](#tokens)
* [Gramática](#gramática)
*  [Autómata Finito](#autómata-finito)
*  [Acciones Semanticas](#acciones-semánticas)
* [Matriz de transiciones](#matriz-de-transiciones)
* [Estado Actual](#estado-actual)
### Analizador Sintáctico
* [Gramática2](#Gramática2)
* [First follow](#First-follow)
* [Tabla de analisis](#Tabla-de-Analisis)
### Analizador Semántico
*
*
> Recordar Actualizar en cada comit el estado actual 
> Assets contiene los archivos para el readmi

## Características:
- Sentencias: Sentencia repetitiva (for)
- Operadores especiales: Asignación con y lógico (&=)
- Técnicas de Análisis Sintáctico: Ascendente LR
- Comentarios: Comentario de bloque (/* */)
- Cadenas: Con comillas dobles (" ")

# Analizador Lexico

## Tokens
|expresión      | token                      |
|---------------|----------------------------|
|‘boolean’      | <boolean, ->               |
|for            |<for, ->                    |
|‘function’     |<function, ->               |
|‘if ‘          |<if, ->                     |
|‘while’        |<while, - >                 |
|‘let’          |<let, - >                   |
|‘int’          |<int, ->                    |
|‘true’         |<true, ->                   |
|‘false’        |<false, ->                  |
|‘return’       |<return, ->                 | 
|‘++’           |<incremento, ->             |
|‘entero’       |<constanteEntera, número>   | 
|‘identificador’|<id, pos>                   |
|‘ , ‘          |<coma, ->                   |
|‘ ;‘           |<puntoComa, ->              | 
|‘ ( ‘          |<abrirParentesis,->         |
|‘ ) ’          |<cerrarParentesis,->        | 
|‘ { ‘          |<abrirLlaves, ->            |
| ‘ } ‘         |<cerrarLlaves, ->           | 
|‘ + ‘          |<operadorSuma,->            |
|‘ -‘           |<operadorResta,->           |
|‘ ! ‘          |<operadorNegacion, ->       | 
|‘ < ‘          |<operadorMenor, ->          |
|‘ > ‘          |<operadorMayor, ->          |
|‘ = ’          |<operadorAsignacion, ->     |
|‘ && ’         |<operadorAnd, ->            |
|‘ &= ’         |<operadorAsignacionLogico,->|

## Gramática
|Signo| Alfabeto que representa       |
|-----|-------------------------------|
| x   |{ a-z, A - Z }                 |
| d   | { 0 - 9 }                     |
| c   | Carácter cualquiera           |
| a   | Carácter cualquiera excepto ” |
| del | {blanco , tabulador }         |
| b   | Carácter cualquiera excepto * |


- S → xA | dB | _E | , | ; | ( | ) | { | } | +D | - | ! | < | > | = | &C | delS | “F | /G
- A → xA | dA | _A |  λ
- B → dB |  λ
- D → + | λ
- C → & | =
- E → _E | xE | dE | λ
- F → aF | ”
- G → *H
- H → bH | *J
- J → /

## Autómata finito

![imagen del automata](https://github.com/amoke15/Compilador-PDL-/blob/principal.java/Assets/automata.png?raw=true)

## Acciones Semánticas

| N.error    | error                                   |
|------------|-----------------------------------------|
| error 50   | caracter no esperado                    |
| error 51   | identificador ya declarado(para despues)|
| error 52   | identificador no declarado              |
| error 53   | entero fuera de rango 32767             |
| error 54   | cadena superior a 64 caracteres         |
| error 55   | no λ al final de una cadena             |


```sh
A.- 0:1     GENTOKEN (coma, -);
B.- 0:2     GENTOKEN (puntoComa,-)
C.- 0:3     GENTOKEN (abrirParentesis,-)
D.- 0:4     GENTOKEN (cerrarParentesis,-)
E.- 0:5     GENTOKEN (abrirLlaves,-)
F.- 0:6     GENTOKEN (cerrarLlaves,-)
G.- 0:7     GENTOKEN (operadorResta,-)
H - 0:8     lee
I.- 8:9     GENTOKEN (Incremento,-)
H.- 8:10    GENTOKEN (operadorSuma,-)
J.- 0:11    GENTOKEN (operadorMayor,-)
K.- 0:12    GENTOKEN (operadorMenor,-)
L.- 0:13    GENTOKEN (operadorAsignacion,-)  
M.- 0:14    Lee   
N.- 14:15   GENTOKEN (operadorAnd,-)
O.- 14:16   GENTOKEN (operadorAsignacionLogico,-)
P.- 0:17    Lee ,  lex:= x

Q.- 17:17   lex:=concat(lex,(caracterRec))

    17:18    c = BuscarTS(lex);
                if(c == NULL) then {
                    InsertarTS(lex)
                    GenToken(Identificador,c)}
                else {
                    Error( 51 )}
       

 R.- 0:19        Lee  num:= vald(d)
 S.- 19:19       Lee num:= num * 10 + vald(d)
     19:20  
        if (num < 32767) then {
            GenToken(Entero, num)
            }else{
                Error(D)}


    If contador < 64 then {
        GenToken(cadena,Cadena(“lex”))}
        else {
        Error( E )}

T.- 0:21     lee
Q.- 21:21   lex:=concat(lex,(caracterRec))

    21:22   If Zona_Decl = True then {
         c = BuscarTS(lex);
            if(c == NULL) then {
                                            InsertarTS(lex)
                GenToken(Identificador,c)}
             else {
                Error( B )}
        else{  //La Zona_Decl = False
            c = BuscarTS(lex)
              if( c == NULL ) then{
                Error( C )}
                                     else {
                GenToken(Identificador, )}


U.- 0:23     lee
V.- 23:24   lee
W.- 24:24  lee y forma comentario
X.- 24:25  lee
U.- 25     coomentario
Y.- 0:27     lee
Z.- 27:27    lee y forma comentario
AA.-27:28 FIN COMENTARIO
BB.-0:0     lee
```


## Matriz de transiciones
![imagen del automata](https://github.com/amoke15/Compilador-PDL-/blob/principal.java/Assets/matriz.png?raw=true)


# Analizador Sintáctico
## Gramática2
### Cosas que reconoce
‘boolean’ |’ for’|‘function’|‘if ‘|‘while’|‘let’|‘int’|‘true’|‘false’|‘return’|‘++’|‘entero’|‘identificador’|‘ , ‘|‘ ;‘|‘ ( ‘|‘ ) ’|‘ { ‘| ‘ } ‘|‘ + ‘|‘ -‘|‘ ! ‘|‘ < ‘|‘ > ‘|‘ = ’|‘ && ’|‘ &= ’|         

```sh
	1.-  S -> PS 
	2.-  S -> FS 
	3.-  S -> 0
 	5.-  P -> let id A |
	6.-  P -> print  ( E ) 
	7.-  P -> input  id 
	8.-  P -> id = E  ;
	9.-  P -> ( L ) ; 
	10.- P -> return E 
	11.- A -> int C 
	12.- A -> string C 
	13.- A -> boolean C   
	14.- A -> 0
	15.- E -> RE'
	16.- E' -> && RE' 
	17.- A -> 0
	18.- R -> UR'
	19.- R' -> < UR' 
	20.- R' -> > UR' 
	21.- R' -> 0 
	22.- U -> VU'
        23.- U' -> + VU' 
	24.- U' -> * VU' 
	25.- U' -> - VU' 
	26.- U' -> 0 
	27.- V ->  ( E ) 
	28.- V -> id ( L ) 
	29.- V -> entero 
	30.- V -> cadena
	31.- L -> EQ 
	32.- L -> 0
	33.- Q -> , EQ 
	34.- Q -> 0
	35.- C ->   = E ; 
	36.- C -> ; 
	37.- C ->  0
	//14.- X -> E | 0
	38.- B -> if ( E ) P 
	39.- B -> let id A 
	40.- B -> P 
	41.- P -> while 
	42.- P -> for 
	43.- F -> function id H ( T ) { D }
	44.- H -> A 
	45.- H -> 0
	46.- T -> A id K 
	47.- T -> 0
	48.- K -> , A id K 
	49.- K -> 0
	50.- D -> BD 
	51.- D -> 0
```
## First follow
### FIRST

```sh
FIRST(S)  = { function,  let , print , input , id  , return , ( , 0 }
FIRST(P) = { let , print , input , id  , return , ( , while , for }
FIIRST (A) = { int , string , boolean , 0 }
FIRST (E) = { id , (  , entero , cadena }
FIRST (E') = { && }
FIRST (R) =  { id , (  , entero , cadena } 
FIRST (R') = { < , > , 0 }
FIRST (U) =  { id , (  , entero , cadena } 
FIRST (U') = { + , - , * , 0 }
FIRST(V) = { id , (  , entero , cadena }
FIRST(L) = {id , (  , entero , cadena , 0 }
FIRST(Q) = { “ , “ , 0 }
FIRST(C) = { = , ; , 0 }
FIRST(X) = { id , (  , entero , cadena , 0 }
FIRST(B) = { if , let  , print , input , id  , return , ( , while , for }
FIRST(F) = {function}
FIRST(H) = { int , string , boolean , 0 }
FIRST(T) = { int , string , boolean , 0 }
FIRST(K) = { “, “ , 0 }
FIRST(D) = { if , let  , print , input , id  , return , ( , while , for, 0  }
```
### FOLLOW

```sh
FOLLOW(S) = { $ }
FOLLOW(P) = { function , let , print , input , id  , return , ( , if , for , while, },  $ }
FOLLOW(F) = { function , let , print , input , id  , return , ( , $ }
FOLLOW(A) = { function , let , print , input , id  , return , ( , $ , } ,  if , for , while ,  }
FOLLOW(E) = { ) , ; , “ , “ ,function , let , print , input , id  , return , ( , if , for , while, },  $ , entero , cadena }
FOLLOW(E') = { ) , ; , “ , “ ,function , let , print , input , id  , return , ( , if , for , while, },  $ , entero , cadena  }
FOLLOW (R) = { && }
FOLLOW (R') = { && }
FOLLOW(U) = { < , > , && }
FOLLOW(U') = { < , > , &&  }
FOLLOW (V) = { + , - , * , < , > , && }
FOLLOW(L) = { ) }
FOLLOW(Q) = { ) }
FOLLOW(C) = { function , let , print , input , id  , return , ( , $ , } ,  if , for , while , }
FOLLOW(X) = { function , let , print , input , id  , return }
FOLLOW(B) = { if , let , print , input , id  , return , function , } , ( , for , while ,  }
FOLLOW(F) = {function,  let , print , input , id  , return , ( , $}
FOLLOW(H) = { ( }
FOLLOW(T) = { ) }
FOLLOW(K) = { )  }
FOLLOW(D) = { } }
```

## Tabla de Analisis

![TablaAnalisis1](https://github.com/amoke15/Compilador-PDL-/blob/principal.java/Assets/tabla1.png?raw=true)
![TablaAnalisis2](https://github.com/amoke15/Compilador-PDL-/blob/principal.java/Assets/Tabla3.png?raw=true)
![TablaAnalisis3](https://github.com/amoke15/Compilador-PDL-/blob/principal.java/Assets/tabla2.png?raw=true)





## ESTADO ACTUAL
### Analizador Sintactico
###### Memoria
- [x] Tokens
- [x] Gramática
- [x] Autómata Finito 
- [x] Acciones Semánticas 
- [x] Matriz de transiciones
###### Código
- [x] Error.java // gestión de errores
- [x] Lexer.java // Lee el file y genera los tokens en función de lo que lee
- [x] Token.java // objeto token que sirve para el lexer
- [x] Transicion.java // genera las transiciones
### Analizador léxico
###### Memoria
- [ ] Crear una gramática tipo 2
- [ ] Aplicar el FIRST/FOLLOW
- [ ] Hacer la tabla de las transiciones
###### Código
>Esto se puede cambiar pero es como yo me imagino como se deberia hacer
- [ ] Clase tabla de transiciones 
>Esta clase deberia de tener todas las posibilidades de nuestra gramatica , para ir pasando los tokens y comprobar si son correctos (almacena datos)
>algo asi como la clase token
- [ ] Arbol 
>Esta clase crea un objeto arbol con los estados que el parcer le devuelve en funcion de si la Tabla de Simbolos y la tabla transiciones son correctas
>y lo almacena de manera que luego se pueda pasar al Sintactico
- [ ] Parcer
>Programa llamado desde el main que comprueba y une las transiciones en caso de correctas 


### Analizador Sintáctico


