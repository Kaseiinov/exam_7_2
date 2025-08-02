INSERT INTO authorities (authority)
VALUES ('USER'),
       ('ADMIN');

INSERT INTO roles (role_name, authority_id)
VALUES ('user', 1),
       ('admin', 2);

INSERT INTO currencies (currency)
VALUES ('USD'),
       ('KGS'),
       ('RUB');

INSERT INTO users (phone, username, password, role_id, enabled)
VALUES ('996555112211', 'user1', '$2a$10$YQxKfiIiiCyyAs5yug/l4O2ZEv4pPkXTeiqBOlM24EPSqTgU8PXXG', 1,
        true), -- password: pass1
       ('12345', 'admin', '$2a$10$MgnmveYcL3AowZVu3caRvumYZC8EzBcHLLDAapJ/gug3Kai4JFPwi', 2,
        true), -- password: pass2
       ('996555553366', 'user2', '$2a$10$YQxKfiIiiCyyAs5yug/l4O2ZEv4pPkXTeiqBOlM24EPSqTgU8PXXG', 1, true);
-- password: pass3

INSERT INTO accounts (currency_id, user_id, balance, uniq_number)
VALUES (1, 1, 1500.75, 'ACC-USD-001'),
       (3, 1, 50000.00, 'ACC-KGS-001'),
       (1, 1, 3000.50, 'ACC-USD-002'),
       (2, 3, 2500.00, 'ACC-EUR-001'),
       (3, 3, 100000.00, 'ACC-KGS-002'),
       (1, 3, 75000.50, 'ACC-RUB-001');

INSERT INTO history (from_acc, to_acc, amount_money, approved)
VALUES ('ACC-USD-001', 'ACC-USD-002', 200.00, true),
       ('ACC-KGS-001', 'ACC-KGS-002', 5000.00, true),
       ('ACC-EUR-001', 'ACC-USD-001', 100.00, false),
       ('ACC-KGS-002', 'ACC-RUB-001', 10000.00, true);

INSERT INTO rollbacks (from_acc, to_acc, amount_money, successful, enabled)
VALUES ('ACC-USD-002', 'ACC-USD-001', 100.00, true, true),
       ('ACC-KGS-002', 'ACC-KGS-001', 2500.00, true, true),
       ('ACC-RUB-001', 'ACC-KGS-002', 5000.00, false, true);