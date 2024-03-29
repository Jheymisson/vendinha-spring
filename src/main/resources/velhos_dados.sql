CREATE TABLE CLIENTE (
  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  NOME varchar(100),
  CPF varchar(12)
);

CREATE TABLE PRODUTO (
  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  DESCRICAO varchar(100),
  PRECO_UNITARIO NUMERIC(20,2)
);

CREATE TABLE PEDIDO (
  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  CLIENTE_ID INTEGER REFERENCES CLIENTE (ID),
  STATUS VARCHAR(20)
  DATA_PEDIDO TIMESTAMP,
  TOTAL NUMERIC(20,2)
);

CREATE TABLE ITEM_PEDIDO (
  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  PEDIDO_ID INTEGER REFERENCES PEDIDO (ID),
  PRODUTO_ID INTEGER REFERENCES PRODUTO (ID),
  QUANTIDADE INTEGER
);