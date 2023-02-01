create table tb_member_tag (
tags varchar(255) not null,
id_member serial,
foreign key(id_member) references tb_members(id_member)
);

--rollback
delete from tb_member_tag;