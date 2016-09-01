package com.project.sondagemocr.DataBase;


public class ScriptSQL {



    public static String createTableEndereco(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS tb_endereco (");
        stringBuilder.append(" _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
        stringBuilder.append(" rua_end VARCHAR(80),");
        stringBuilder.append(" numero_end VARCHAR(8),");
        stringBuilder.append(" bairro_end VARCHAR(80),");
        stringBuilder.append(" cep_end VARCHAR(8),");
        stringBuilder.append(" cidade_end VARCHAR(80),");
        stringBuilder.append(" uf_end CHAR(2));");

        return stringBuilder.toString();

    }

    public static String createTableLoginUsuario(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS tb_login_usuario (");
        stringBuilder.append(" usuario VARCHAR(40) NOT NULL PRIMARY kEY, ");
        stringBuilder.append(" senha VARCHAR(40) NOT NULL ");
        stringBuilder.append(");");

        return stringBuilder.toString();

    }

    public static String createTableUsuario(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS tb_usuario ( ");
        stringBuilder.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        stringBuilder.append("login_user VARCHAR(40) NOT NULL, ");
        stringBuilder.append("nome_usuario VARCHAR(80) NOT NULL, ");
        stringBuilder.append("rg_usuario CHAR(9), ");
        stringBuilder.append("cpf_usuario CHAR(11), ");
        stringBuilder.append("dt_nasc_usuario DATE, ");
        stringBuilder.append("tel_usuario VARCHAR(11), ");
        stringBuilder.append("escolaridade_usuario VARCHAR(40), ");
        stringBuilder.append("coordenador_usuario VARCHAR(80) ");
        stringBuilder.append(");");

        return stringBuilder.toString();

    }

    public static String createTableAluno(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists tb_aluno ( ");
        stringBuilder.append("_id integer not null primary key autoincrement, ");
        stringBuilder.append("_id_responsavel integer, ");
        stringBuilder.append("_id_usuario integer, ");
        stringBuilder.append("_id_turma integer, ");
        stringBuilder.append("nome_aluno varchar(80) not null, ");
        stringBuilder.append("ra_aluno varchar(10), ");
        stringBuilder.append("dt_nasc_aluno date ");
        stringBuilder.append(");");

        return stringBuilder.toString();

    }

    public static String createTableResponsavel(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists tb_responsavel_aluno ( ");
        stringBuilder.append("_id integer not null primary key autoincrement, ");
        stringBuilder.append("_id_endereco integer, ");
        stringBuilder.append("nome_responsavel varchar(80) not null, ");
        stringBuilder.append("cpf_responsavel varchar(11), ");
        stringBuilder.append("tel_responsavel varchar(11) ");
        stringBuilder.append(");");

        return stringBuilder.toString();
    }

    public static String createTableTurma(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists tb_turma ( ");
        stringBuilder.append("_id integer not null primary key autoincrement, ");
        stringBuilder.append("_id_usuario integer, ");
        stringBuilder.append("_id_aluno integer, ");
        stringBuilder.append("identificacao_turma varchar(3), ");
        stringBuilder.append("ano_turma char(4) ");
        stringBuilder.append(");");

        return stringBuilder.toString();


    }

    public static String createTableSondagemModelo(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists tb_sondagem_modelo ( ");
        stringBuilder.append("_id integer not null primary key autoincrement, ");
        stringBuilder.append("_id_usuario integer, ");
        stringBuilder.append("desc_sondagem_mod varchar(50) not null, ");
        stringBuilder.append("monossilaba varchar(5), ");
        stringBuilder.append("dissilaba varchar(10), ");
        stringBuilder.append("trissilaba varchar(15), ");
        stringBuilder.append("polissilaba varchar(20), ");
        stringBuilder.append("frase varchar(40) ");
        stringBuilder.append(");");

        return stringBuilder.toString();

    }

    public static String createTableNivel(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists tb_nivel ( ");
        stringBuilder.append("_id integer not null primary key autoincrement, ");
        stringBuilder.append("nome_nivel varchar (35), ");
        stringBuilder.append("desc_nivel varchar (180) ");
        stringBuilder.append(");");

        return stringBuilder.toString();

    }

    public static String createTableSondagemAluno(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists tb_sondagem_aluno ( ");
        stringBuilder.append("_id integer not null primary key autoincrement, ");
        stringBuilder.append("_id_sondagem_modelo integer, ");
        stringBuilder.append("_id_usuario integer , ");
        stringBuilder.append("_id_aluno integer, ");
        stringBuilder.append("_id_nivel integer, ");
        stringBuilder.append("monossilaba varchar(5), ");
        stringBuilder.append("dissilaba varchar(10), ");
        stringBuilder.append("trissilaba varchar(15), ");
        stringBuilder.append("polissilaba varchar(20), ");
        stringBuilder.append("frase varchar(40) ");
        stringBuilder.append(");");

        return stringBuilder.toString();

    }

}
