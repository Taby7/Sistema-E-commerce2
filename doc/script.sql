INSERT INTO public.tb_role(id, nome) VALUES (nextval('tb_role_seq'), 'ROLE_ADMIN');
INSERT INTO public.tb_role(id, nome) VALUES (nextval('tb_role_seq'), 'ROLE_PROFESSOR');
INSERT INTO public.tb_role(id, nome) VALUES (nextval('tb_role_seq'), 'ROLE_ALUNO');


INSERT INTO public.tb_usuario(id, nascimento, email, login, nome, senha, username)
VALUES (nextval('tb_usuario_seq'), '2000-01-01', 'admin@admin', 'admin', 'Admin', '$2a$10$xrf7BIfAypijHuEypnme7u4lklTDRUJzGWe9NHWkXU4GOqglkobfm', 'admin');


INSERT INTO public.tb_usuario_role(usuario_id, role_id) 
select u.id, r.id from tb_usuario u 
cross join tb_role r
where u.login = 'admin' and r.nome = 'ROLE_ADMIN';