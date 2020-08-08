# tcp-client
Cliente TCP desenvolvido em Java

Obs.: o executável .jar não está incluso neste repositório pois seu tamanho excedeu o tamanho máximo permitido pelo GitHub.

Instruções:
1) Abrir o Spring Tool Suite 4, ir em File > Import > Maven > Existing Maven Projects > Next
2) Selecionar o projeto por meio do botão "Browse" e "Finish"
3) A execução do projeto pode ser feita clicando com o botão direito do mouse sobre o projeto, Run As > Java Application ou Spring Boot App
4) Quando executado, é necessário informar o IP onde se localiza o servidor e a porta de conexão

-> Criação do .jar do projeto:
1) Com o botão direito do mouse sobre o projeto, Run As > Maven build...
2) No campo "Goals", informar "package" e clicar no botão "Run"
3) O .jar é criado no diretório "target" do projeto
4) Para executá-lo, execute cmd.exe, vá até o diretório onde o .jar está localizado e digite "java -jar nome_do_executavel.jar"
4) Quando executado, é necessário informar o IP onde se localiza o servidor e a porta de conexão

-> Considerações
1) O arquivo de log LOG_CLIENT.txt é criado no diretório raiz do projeto ou no diretório onde se localiza o executável .jar
