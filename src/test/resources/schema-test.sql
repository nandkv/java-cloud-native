DROP TABLE IF EXISTS account_loan;
DROP TABLE IF EXISTS account;

CREATE TABLE account
(
  id              BIGINT(20) AUTO_INCREMENT PRIMARY KEY NOT NULL,
  username        VARCHAR(255),
  account_number  VARCHAR(255),
  created_at      BIGINT(20),
  last_modified   BIGINT(20),
);
CREATE UNIQUE INDEX UK_ACCOUNT_USER_ACCOUNT_NUMBER ON account (username, account_number);

DROP TABLE IF EXISTS loan;

CREATE TABLE loan
(
  id            BIGINT(20) AUTO_INCREMENT PRIMARY KEY NOT NULL,
  loan_number	VARCHAR(255),
  type          VARCHAR(255),
  created_at    BIGINT(20),
  last_modified BIGINT(20)
);

CREATE TABLE account_loan
(
  account_id   BIGINT(20) NOT NULL,
  loan_id 	   BIGINT(20) NOT NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (account_id, loan_id),
  CONSTRAINT FK_12vtt2maaj4yfrkbjmkf2qonq FOREIGN KEY (account_id) REFERENCES account (id),
  CONSTRAINT FK_r2ahplt2rqwvx1pnd5bbo7o70 FOREIGN KEY (loan_id) REFERENCES loan (id)
);