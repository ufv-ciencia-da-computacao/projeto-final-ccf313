use tpfinalpoo;
insert into disciplina(`codDisciplina`,`nome`,`descricao`) values ("MAF","Cálculo 1","Descrição Cálculo 1");
insert into disciplina(`codDisciplina`,`nome`,`descricao`) values ("CFC","POO","Descrição POO");
insert into disciplina(`codDisciplina`,`nome`,`descricao`) values ("ADF","Economia","Descrição Economia");

insert into usuario(`nome`,`descricao`,`dataNascimento`,`formacao`,`username`,`tipoUsuario`)
 values ("Dener Vieira","Descrição Dener","2000-10-07","Ciência da Computação","dener",1);
 insert into usuario(`nome`,`descricao`,`dataNascimento`,`formacao`,`username`,`tipoUsuario`)
 values ("Germano Barcelos","Descrição Germano","2001-03-26","Ciência da Computação","germano",0);
 insert into usuario(`nome`,`descricao`,`dataNascimento`,`formacao`,`username`,`tipoUsuario`)
 values ("Fabricio ","Descrição Fabricio","1978-05-03","Ciência da Computação","fabricio",1);
 insert into usuario(`nome`,`descricao`,`dataNascimento`,`formacao`,`username`,`tipoUsuario`)
 values ("Fábio Trindade","Descrição Fábio","2001-09-05","Ciência da Computação","fabio",0);

insert into topico(`idTopico`,`descricao`) values (1,"Integral");
insert into topico(`idTopico`,`descricao`) values (2,"Interfaces");
insert into topico(`idTopico`,`descricao`) values (3,"Exceptions");

insert into avaliacaousuario(`usernameAvaliador`,`usernameProfessor`,`comentario`,`valor`)
 values ("germano","dener","Excelente!",5.0);

insert into aula(`codAula`,`descricao`,`codDisciplina`,`usernameProfessor`,`valorHora`)
 values ("codAula1","Descriçaõ aula Calculo","MAF","dener",15.0);
 insert into aula(`codAula`,`descricao`,`codDisciplina`,`usernameProfessor`,`valorHora`)
 values ("codAula2","Descrição aula POO","CFC","fabricio",25.0);
 
 insert into topicoaula(`idTopico`,`codAula`) values (1,"codAula1");
 insert into topicoaula(`idTopico`,`codAula`) values (2,"codAula2");
 insert into topicoaula(`idTopico`,`codAula`) values (3,"codAula2");
  
 insert into contratoaula(`usernameAluno`,`codAula`,`codContrato`,`dataFinal`,`dataInicio`,`statusContrato`)
 values ("germano","codAula1","codContrato1","2021-05-27","2021-05-20",1);
 insert into contratoaula(`usernameAluno`,`codAula`,`codContrato`,`dataFinal`,`dataInicio`,`statusContrato`)
 values ("fabio","codAula2","codContrato2","2021-06-20","2021-06-10",0);
