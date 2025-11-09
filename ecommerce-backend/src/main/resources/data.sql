INSERT INTO users (id,name,email,password,role) VALUES
(1,'Admin','admin@shop.com','$2a$10$hQv2mP8h1H1Qy8P7HkQyUe6dD88q2kGxv3M2i8K1oS2K3t6rU3Yj2','ADMIN');
-- create admin cart
INSERT INTO cart (user_id) VALUES (1);

INSERT INTO product (name,description,price,stock,category,image_url,rating) VALUES
('iPhone 14','Smartphone',69999,50,'Mobiles','',4.6),
('Noise Buds','Earbuds',1999,200,'Audio','',4.2);
