package com.project.sondagemocr.Sondagem;

import android.util.Log;

public class AnalisePalavras {

    private String palavraCorreta;
    private String palavraInserida;
    private String[] listaSilabasCorreta;
    private String[] listaSilabasInserida;
    public static String[] silabasCorreta = new String[20];
    public static char[] listaLetras = new char[30];

    public AnalisePalavras(String palavraCorreta, String palavraInserida){
        this.palavraCorreta = palavraCorreta;
        this.palavraInserida = palavraInserida;
        this.palavraCorreta = this.palavraCorreta.toLowerCase();
        this.palavraInserida = this.palavraInserida.toLowerCase();
        this.palavraCorreta = this.palavraCorreta.replace(" ", "");
        this.palavraInserida = this.palavraInserida.replace(" ","");
        this.listaSilabasCorreta = separaSilabas(this.palavraCorreta);
        this.listaSilabasInserida = separaSilabas(this.palavraInserida);

    }


    //Verificando se aluno se enquadra como alfabético
    public boolean alfabetico(){
        //Caso palavra sejam iguais será declarado alfabético
        if(palavraCorreta.equals(palavraInserida)){
            return true;
        }else{
            int tam = 0;
            tam = tamanhoArray(listaSilabasCorreta);
            if(tam > 4){
                System.out.println("Length:"+listaSilabasCorreta.length);
                int erros = 0;
                int acertos = 0;
                boolean flag = true;
                for(int i=0; i<listaSilabasInserida.length; i++){
                    if(listaSilabasCorreta[i]=="" || listaSilabasCorreta[i]== null){
                        if(listaSilabasInserida[i]==null || listaSilabasCorreta[i].equals("")){
                            if((listaSilabasCorreta[i]==""|| listaSilabasCorreta[i]==null) && (listaSilabasInserida[i]!=null || listaSilabasCorreta[i]!="" )){
                                erros++;
                            }else{
                                break;
                            }
                        }else{
                            flag = false;
                        }

                        break;
                    }
                    else if (listaSilabasInserida[i]==null){
                        erros = erros + (tam-i);
                        break;
                    }
                    for(char c : listaSilabasInserida[i].toCharArray()){
                        for(int y=0; y<listaSilabasCorreta[i].length(); y++){
                            if((c == listaSilabasCorreta[i].charAt(y))||((c == 'k')&&(listaSilabasCorreta[i].charAt(y)=='c'))){
                                acertos++;
                            }
                        }
                    }
                    if(acertos == 0){
                        if(i>0){
                            if(listaSilabasInserida[i-1].charAt(listaSilabasInserida[i-1].length()-1) == listaSilabasCorreta[i].charAt(0)){
                                i++;
                                erros++;
                                continue;
                            }else{
                                erros++;
                                flag = false;
                                break;
                            }
                        }else{
                            erros++;
                            flag = false;
                            break;
                        }
                    }else if(acertos==1 && listaSilabasCorreta[i].length()==2){ //Caso na silaba apenas obteve-se 1 acerto e a silaba é formada por 2 letras ou mais contara erro
                        erros++;
                    }else if(acertos==1 && listaSilabasCorreta[i].length()==3){
                        erros = erros + 2;
                    }
                    acertos = 0;
                }
                if(erros > 3 || flag == false){
                    return false;
                }else{
                    return true;
                }
            }else{
                return false;
            }
        }
    }

    //Verificando se aluno se enquadra como silabico alfabético
    public boolean silabicoAlfabetico(){
        boolean flag = true;
        int acertos = 0;
        int erros = 0;
        int reg = 0;
        int tam = 0;
        tam = tamanhoArray(listaSilabasCorreta);
        //Percorrendo array de silabas de paralavra inserida
        for(int i=0; i<listaSilabasInserida.length; i++){
            if(listaSilabasCorreta[i+reg]=="" || listaSilabasCorreta[i+reg]== null){
                if(listaSilabasInserida[i]==null || listaSilabasCorreta[i+reg]==""){
                    if((listaSilabasCorreta[i+reg] == "" || listaSilabasCorreta[i+reg]==null)&& (listaSilabasInserida[i] != null|| listaSilabasCorreta[i]!="" ) ||!listaSilabasInserida[i].equals("")){
                        erros++;
                    }else{
                        break;
                    }
                }else{
                    flag = false;
                }
                break;
            }else if (listaSilabasInserida[i]==null){
                erros = erros + (tam-i);
                break;
            }
            for(char c : listaSilabasInserida[i].toCharArray()){
                for(int y=0; y<listaSilabasCorreta[i+reg].length(); y++){
                    if((c == listaSilabasCorreta[i+reg].charAt(y))||((c == 'k')&&(listaSilabasCorreta[i].charAt(y)=='c'))){
                        acertos++;
                    }
                }
            }
            if(acertos == 0){
                if(i>0){
                    //Caso não se tenha encontrado algum acerto de letras na silaba
                    //atual tentamos encontrar algma combinada com silaba anterior da palavra inserida
                    if(listaSilabasInserida[i-1].charAt(listaSilabasInserida[i-1].length()-1) == listaSilabasCorreta[i].charAt(0)){
                        i++;
                        erros++;
                        continue;
                    }else{
                        if(tam > 5){
                            erros++;
                            //Caso silaba anterior ou posterior seja compatível a silaba correta derá reposicionada de acordo com sílaba inserida
                            if(listaSilabasInserida[i].equals(listaSilabasCorreta[(i+reg)-1])){
                                reg--;
                            }else if (listaSilabasInserida[i].equals(listaSilabasCorreta[(i+reg)+1])){
                                reg++;
                            }
                            continue;
                        }else{
                            erros++;
                            flag = false;
                            break;
                        }
                    }
                }else{
                    erros++;
                    flag = false;
                    break;
                }
            }else if(acertos==1 && listaSilabasCorreta[i+reg].length()==2){
                erros++;
            }else if(acertos==1 && listaSilabasCorreta[i+reg].length()==3){
                erros = erros + 2;
            }
            acertos = 0;
        }

        if((tam > 5)&&(erros > 5)){
            flag = false;
        }else if((tam == 4 || tam ==5 )&&(erros > 3)){
            flag = false;
        }else if((tam == 3)&&(erros > 2)){
            flag = false;
        }else if(tam == 2 && erros > 1){
            flag = false;
        }else if(tam <= 1 && erros > 0){
            flag = false;
        }


        return flag;
    }

    public boolean silabicoComValor(){

        boolean flag = true;    //Flag indica se aluno se encaixo como silabico com valor ou não
        int acertos = 0;
        int erros = 0;
        int tam = 0;
        int w = 0;
        int tamcorreta = tamanhoArray(listaSilabasCorreta);

        for(int i=0; i<listaSilabasInserida.length; i++){   //Percorrendo pelo array de silabas

            if(listaSilabasCorreta[i]=="" || listaSilabasCorreta[i]==null || listaSilabasInserida[i]==null){
                //Caso tamanh do array de silabs inseridas ultrapasse a da correta, não será declarado silabico com valor
                if((listaSilabasInserida[i]==null && listaSilabasCorreta[i]=="" || listaSilabasCorreta[i]==null)||
                        (listaSilabasInserida[i]=="" && listaSilabasCorreta[i]=="")){
                    if(tamcorreta >= 4 && listaSilabasInserida[i]==null && listaSilabasCorreta[i]==""){
                        break;
                    }

                }else{
                    erros++;
                    break;
                }
                break;
            }else if (listaSilabasInserida[i]==null || listaSilabasInserida[i]=="" ){
                erros = erros + (tamcorreta-i);
                break;
            }



            for(char c : listaSilabasInserida[i].toCharArray()){   //Percorrendo silaba da atual posição de array de silabas da palavra inserida pelo aluno
                for(int y=0; y<listaSilabasCorreta[w].length(); y++){    //Percorrendo silaba da atual posição de array de silabas da palavra correta
                    if((c == listaSilabasCorreta[w].charAt(y))||((c == 'k')&&(listaSilabasCorreta[i].charAt(y)=='c'))){ //Caso uma das letras da atual silaba da palavra correta sejam compativeis com umas das letras da silaba da palavra inserida sera contado acerto
                        acertos++;
                    }
                }
            }

            //Caso nenhuma das letras da silabas sejam iguais, entratá nesta condição
            if(acertos == 0){
                erros++;
                if(i>0){  //Verifica se array de silabas da palavra inserida não esta na primeira casa
                    if(listaSilabasInserida[i-1].charAt(listaSilabasInserida[i-1].length()-1) == listaSilabasCorreta[i].charAt(0)){  //Caso silaba anterior da inserida tenha alguma letra em comum com silaba correta, terá a chance se ainda seguir no metodo
                        i++;
                        continue;
                    }

                }
            }
            acertos = 0;
        }

        //Teste final de polissilaba
        if(tamcorreta > 4 && erros > 3){
            flag = false;
        }else if(tamcorreta == 4 && erros > 1){
            //Caso seja polissilaba e tenha ocorrido erro de alguma silaba
            //uma chance é dada
            flag = false;
        }else if(tamcorreta < 4 && erros > 0){
            flag = false;
        }
        return flag;
    }

    //Verificando se aluno se enquadra como silábico sem valor
    public boolean silabicoSemValor(){
        //Criando flag com valor true, caso se mantenha true ao final do método será declarado  Silábico sem valor
        boolean flag = true;
        //Recebendo tamanho de arrays para verificar limites
        int taminc = tamanhoArray(listaSilabasInserida);
        int tamcor = tamanhoArray(listaSilabasCorreta);

        //Caso a quantidade de silabas sejam iguais, será declarado Silabico alfabético
        if(tamcor > 5){
            if(tamcor - taminc > 3 || tamcor - taminc < -3){
                flag = false;
            }
        }
        else if(tamcor == 4 || tamcor == 5){
            if(tamcor - taminc > 1 || tamcor - taminc < -1){
                flag = false;
            }
        }else{
            if(tamcor - taminc != 0){
                flag = false;
            }
        }

        return flag;
    }

    //Método realiza a separação de silabas
    public static String[] separaSilabas(String palavra){
        int i = 0;
        int y = 0;
        boolean ns = false;
        silabasCorreta = new String[20];
        listaLetras = new char[30];
        //Recebendo letra por letra da palavra e transferindo para um array de char
        for(char c : palavra.toCharArray()){
            listaLetras[i] = c;
            i++;
        }

        for(i=0; i<listaLetras.length; i++){
            if(silabasCorreta[y]==null){
                silabasCorreta[y] = "";
            }
            //Caso a lista de letras acabe o método será finalizado
            if(listaLetras[i]=='\0'){
                System.out.print("\n\nSaiu!!\n\n");
                break;
            }

            //Caso tenha occorrido a relação ans,ens,ins,ons,uns sera adicionado a letra "s" e fechara silaba
            if (ns == true){
                ns = false;
                silabasCorreta[y] = silabasCorreta[y]+String.valueOf(listaLetras[i]);
                y++;
                continue;
            }

            //Caso a letra na posição atual seja Consoante, entrará nesta condição
            if((listaLetras[i]!='a')&&(listaLetras[i]!='e')&&(listaLetras[i]!='i')&&(listaLetras[i]!='o')&&
                    (listaLetras[i]!='u')){

                //Caso a letra após a consoante atual seja vogal ou H entrará na condição
                if(((listaLetras[i+1]=='a')||(listaLetras[i+1]=='e')||(listaLetras[i+1]=='i')||(listaLetras[i+1]=='o')||
                        (listaLetras[i+1]=='u')||(listaLetras[i+1]=='h'))
                        //
                        ||((listaLetras[i]=='b' || listaLetras[i]=='c' || listaLetras[i]=='d' || listaLetras[i]=='f' || listaLetras[i]=='g' || listaLetras[i]=='t' || listaLetras[i]=='p') && listaLetras[i+1]=='r')

                        || ((listaLetras[i]=='b' || listaLetras[i]=='c' || listaLetras[i]=='f' ||
                        listaLetras[i]=='g' || listaLetras[i]=='p')&&(listaLetras[i+1]=='l'))

                        && ((listaLetras[i+2]=='a')||(listaLetras[i+2]=='e')||(listaLetras[i+2]=='i')||(listaLetras[i+2]=='o')||
                                (listaLetras[i+2]=='u'))){
                    //Array de silaba recebe letra e continua na sílaba
                    silabasCorreta[y] = silabasCorreta[y]+String.valueOf(listaLetras[i]);
                }else if(  listaLetras[i] == 'n' && listaLetras[i+1] == 's' && ((listaLetras[i+2]!='a')&&(listaLetras[i+2]!='e')&&(listaLetras[i+2]!='i')&&(listaLetras[i+2]!='o')&&
                        (listaLetras[i+2]!='u'))) {
                    silabasCorreta[y] = silabasCorreta[y] + String.valueOf(listaLetras[i]);
                    ns = true;
                }
                else{
                    //Array de silaba recebe letra e fecha a silaba
                    silabasCorreta[y] = silabasCorreta[y]+String.valueOf(listaLetras[i]);
                    System.out.print("Consoante depois:"+silabasCorreta[y]+"\n");
                    y++;
                }
            }
            //Caso a letra na posição atual seja Vogal
            else{
                //Caso a letra após a Vogal seja uma Consoante entrará nesta condição
                if((listaLetras[i+1]!='a')&&(listaLetras[i+1]!='e')&&(listaLetras[i+1]!='i')&&(listaLetras[i+1]!='o')&&
                        (listaLetras[i+1]!='u')){
                    //Caso a letra após a Vogal seja l, s, r, y, z ou x. E ainda que a letra seguinte não seja Vogal nem H, entrará na condição
                    //Isto trata questão como silabas al,el,ar,er,ir,or,ur,an,en,etc
                    if(((listaLetras[i+1]=='l')||(listaLetras[i+1]=='s')||(listaLetras[i+1]=='r')||(listaLetras[i+1]=='y')||
                            (listaLetras[i+1]=='z')||(listaLetras[i+1]=='x')||(listaLetras[i+1]=='n'||(listaLetras[i+1]=='m')))
                            &&((listaLetras[i+2]!='a')&&(listaLetras[i+2]!='e')&&(listaLetras[i+2]!='i')&&(listaLetras[i+2]!='o')&&
                            (listaLetras[i+2]!='u')&&(listaLetras[i+2]!=' ')&&(listaLetras[i+2]!='h'))){//pode alterar
                        //Array de silaba recebe letra e continua na sílaba
                        silabasCorreta[y]=silabasCorreta[y]+listaLetras[i];
                    }
                    else{
                        //Array de silaba recebe letra e fecha a silaba
                        silabasCorreta[y]=silabasCorreta[y]+listaLetras[i];
                        y++;
                    }
                }
                else{
                    //Caso letra na posição atual seja Vogal I, entrará na condição
                    //Trata a questão de hiato
                    if(listaLetras[i]=='i'){
                        //Array de silaba recebe letra e fecha a silaba
                        silabasCorreta[y]=silabasCorreta[y]+listaLetras[i];
                        y++;
                        continue;
                    }
                    //Caso letra na posição atual seja Vogal U, entrará na condição
                    //Trata a questão de hiato
                    if(listaLetras[i]=='u'){
                        //Caso U não seja primeira letra da palavra
                        if(i > 0){
                            //Caso a sílaba seja formada por quo,qui,que,gua,gue,gui,guo
                            if(((listaLetras[i-1]=='q')||(listaLetras[i-1]=='g'))
                                    &&(listaLetras[i+1]=='a')||(listaLetras[i+1]=='e')||(listaLetras[i+1]=='i')||(listaLetras[i+1]=='o')){
                                //Array de silaba recebe letra e continua na sílaba
                                silabasCorreta[y]=silabasCorreta[y]+listaLetras[i];
                                continue;
                            }
                        }
                        else{
                            //Trata questão de hiato com letra U
                            //Array de silaba recebe letra e fecha a silaba
                            silabasCorreta[y]=silabasCorreta[y]+listaLetras[i];
                            y++;
                            continue;
                        }
                    }
                    if(((listaLetras[i]=='a')||(listaLetras[i]=='e')||(listaLetras[i]=='o'))&&
                            ((listaLetras[i+1]=='á')||(listaLetras[i+1]=='é')||(listaLetras[i+1]=='í')||(listaLetras[i+1]=='ó')||
                                    (listaLetras[i+1]=='ú'))){
                        silabasCorreta[y]=silabasCorreta[y]+listaLetras[i];
                        y++;
                        continue;
                    }
                    //Casos como letras "ao" estiverem unidas serão mantidas na mesma silaba
                    if(((listaLetras[i] == 'a' && listaLetras[i+1] == 'o')||
                            (listaLetras[i] == 'e' && listaLetras[i+1] == 'i')||
                            (listaLetras[i] == 'o' && listaLetras[i+1] == 'i'))&&(listaLetras[i+2]!= 'm')){
                        silabasCorreta[y]=silabasCorreta[y]+listaLetras[i];
                        continue;
                    }
                    //Caso a letra da posição atual seja vogal e a próxima seja igual
                    //Array de silaba recebe letra e fecha a silaba
                    if(listaLetras[i]==listaLetras[i+1]){
                        silabasCorreta[y]=silabasCorreta[y]+listaLetras[i];
                        y++;
                        continue;
                    }
                    else{
                        System.out.print("Ultimo else");
                        silabasCorreta[y]=silabasCorreta[y]+listaLetras[i];
                        y++;
                    }
                }


            }
            //System.out.print("\nContagem y:"+y+" ::para length: "+listaLetras.length+"\n");
        }

        //Apenas informando por console a palavra em avaliação em silabas
        String palavraSilabas = new String();
        for(String silaba:silabasCorreta){
            palavraSilabas = palavraSilabas+"-"+silaba;
        }
        Log.i("Sondagem palavra:",palavraSilabas);

        return silabasCorreta;

    }

    public String[] complementoSeparaSilabaInserida(String[] silabaInserida){
        int y = 0;
        int tamanho = silabaInserida.length;
        for(int i = 0; i < tamanho; i++){
            listaLetras = new char[11];
            for(char c:silabaInserida[i].toCharArray()){
                listaLetras[y] = c;
                y++;
            }
            y = 0;
            for(int z=0; z<listaLetras.length; z++){//pode mudar
                if(((listaLetras[z]=='a')||(listaLetras[z]=='e')||(listaLetras[z]=='i')||(listaLetras[z]=='o')||(listaLetras[z]=='u'))
                        &&((listaLetras[z+1]=='a')||(listaLetras[z+1]=='e')||(listaLetras[z+1]=='i')||(listaLetras[z+1]=='o')||(listaLetras[z+1]=='u'))){
                    for(y=tamanho; y>i; y--){
                        silabaInserida[y+1]=silabaInserida[y];

                    }
                }
            }
        }

        return silabaInserida;
    }

    public int tamanhoArray(String[] array){
        int tam = 0;
        for(int i=0;i<array.length;i++){
            if(array[i]=="" || array[i]==null){
                break;
            }
            tam = tam+ 1;
        }
        return tam;
    }


    public String getPalavraCorreta() {
        return palavraCorreta;
    }

    public void setPalavraCorreta(String palavraCorreta) {
        this.palavraCorreta = palavraCorreta;
    }

    public String getPalavraInserida() {
        return palavraInserida;
    }

    public void setPalavraInserida(String palavraInserida) {
        this.palavraInserida = palavraInserida;
    }

    public String[] getSilabasCorreta() {
        return silabasCorreta;
    }

    public void setSilabasCorreta(String[] silabasCorreta) {
        this.silabasCorreta = silabasCorreta;
    }

    public char[] getListaLetras() {
        return listaLetras;
    }

    public void setListaLetras(char[] listaLetras) {
        this.listaLetras = listaLetras;
    }


}
