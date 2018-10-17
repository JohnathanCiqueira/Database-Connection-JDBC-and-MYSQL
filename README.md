<h1>Java Database Connection: JDBC and MYSQL</h1>

<h2>Requerimentos</h2>

<li>Eclipse - https://www.eclipse.org/downloads/</li>
<li>Database MYSQL - https://dev.mysql.com/downloads/windows/installer/5.7.html</li>
<li>JDBC MYSQL Connector version 5.1 - https://dev.mysql.com/downloads/connector/j/5.1.html</li>

<h2>Descrição</h2>

<p>Java Database Connectivity ou JDBC é um conjunto de classes e interfaces (API) escritas em Java que fazem o envio de instruções SQL para 
qualquer banco de dados relacional; Api de baixo nível e base para api’s de alto nível; Amplia o que você pode fazer com Java; 
Possibilita o uso de bancos de dados já instalados; Para cada banco de dados há um driver JDBC que pode cair em quatro categorias. 
Aqui utilizaremos o MySQL</p>

<p>Todos os scripts necessários para criação do banco, tabelas e procedures estão na pasta SQL</p>

<h2>Remover o MySQL SSL Warning Message</h2>

<b>Importante: não é recomendável estabelecer uma conexão SSL sem a verificação de identidade do servidor</b>

<p>Ao se conectar a um banco de dados MySQL, você pode encontrar esta mensagem de aviso em vermelho no console do eclipse.</p>
<p>WARN: Establishing SSL connection without server’s identity verification is not recommended</p>
<p>Seu aplicativo continuará funcionando bem ... é apenas o banco de dados MySQL falando com você.</p>

<h3>Solução</h3>

<p>Para se livrar da mensagem de aviso, anexe ?UseSSL = false ao final da string de conexão do seu banco de dados.</p>

<p>Exemplo</p>

<p>Substitua - jdbc:mysql://localhost:3306/demo</p>
<p>Por - jdbc:mysql://localhost:3306/demo?UseSSL=false</p>

<p>Observe que eu adicionei ?UseSSL = false ao final.</p>

<p>Isso irá remover o aviso em vermelho do mysql</p>



