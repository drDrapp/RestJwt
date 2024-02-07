INSERT INTO dg_user (id, first_Name, last_Name, login, hash_password, state)
VALUES ('1', 'firstName', 'lastName', 'z', '$2a$10$qz7.2I91D3Ea6zXvkxjw1.EdY2m7jAKSxlLePLeo3LDpxED6MY6Me', 'ACTIVE');
INSERT INTO dg_user_role
VALUES ('1', 'ADMIN');

INSERT INTO dg_user (id, first_Name, last_Name, login, hash_password, state)
VALUES ('2', 'userFirstName', 'userLastName', 'u', '$2a$10$qz7.2I91D3Ea6zXvkxjw1.EdY2m7jAKSxlLePLeo3LDpxED6MY6Me', 'ACTIVE');
INSERT INTO dg_user_role
VALUES ('2', 'USER');