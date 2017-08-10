if(
(SELECT COUNT(idaddress) FROM user_has_address_view WHERE address LIKE '%%' AND iduser='')>0,
INSERT 
);

INSERT INTO address VALUES ();