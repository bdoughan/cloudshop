create user 'item_service_user'@'%'
identified by 'item_service_password';

grant select, insert, delete, update 
on cloudShopDb.items 
to 'item_service_user'@'%';
