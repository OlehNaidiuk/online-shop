INSERT INTO category (category_id, category_name, catalog_type) VALUES (DEFAULT, 'футболка', 'CLOTHES');
INSERT INTO category (category_id, category_name, catalog_type) VALUES (DEFAULT, 'куртка', 'CLOTHES');
INSERT INTO category (category_id, category_name, catalog_type) VALUES (DEFAULT, 'джинси', 'CLOTHES');
INSERT INTO category (category_id, category_name, catalog_type) VALUES (DEFAULT, 'кофта', 'CLOTHES');
INSERT INTO category (category_id, category_name, catalog_type) VALUES (DEFAULT, 'сандалі', 'FOOTWEAR');
INSERT INTO category (category_id, category_name, catalog_type) VALUES (DEFAULT, 'кеди', 'FOOTWEAR');
INSERT INTO category (category_id, category_name, catalog_type) VALUES (DEFAULT, 'кросівки', 'FOOTWEAR');
INSERT INTO category (category_id, category_name, catalog_type) VALUES (DEFAULT, 'ремінь', 'ACCESSORIES');

INSERT INTO company (company_id, company_name) VALUES (DEFAULT, 'BOSS');
INSERT INTO company (company_id, company_name) VALUES (DEFAULT, 'NIKE');
INSERT INTO company (company_id, company_name) VALUES (DEFAULT, 'HUGO');
INSERT INTO company (company_id, company_name) VALUES (DEFAULT, 'Reebok');
INSERT INTO company (company_id, company_name) VALUES (DEFAULT, 'Emporio Armani');
INSERT INTO company (company_id, company_name) VALUES (DEFAULT, 'Levi''s');

INSERT INTO sale (sale_id, sale_value) VALUES (DEFAULT, '10');
INSERT INTO sale (sale_id, sale_value) VALUES (DEFAULT, '20');
INSERT INTO sale (sale_id, sale_value) VALUES (DEFAULT, '30');
INSERT INTO sale (sale_id, sale_value) VALUES (DEFAULT, '40');
INSERT INTO sale (sale_id, sale_value) VALUES (DEFAULT, '50');
INSERT INTO sale (sale_id, sale_value) VALUES (DEFAULT, '60');
INSERT INTO sale (sale_id, sale_value) VALUES (DEFAULT, '70');

INSERT INTO size (size_id, size_value) VALUES (DEFAULT, 'XS');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, 'S');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, 'M');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, 'L');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, 'XL');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, '33/22');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, '38/37');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, '39/39');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, '40/40');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, '41/41');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, '42/42');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, '38');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, '39');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, '40');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, '41');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, '42');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, '43');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, '44');
INSERT INTO size (size_id, size_value) VALUES (DEFAULT, '45');

INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 3, DEFAULT, 1234.00, 'UAH', 'BLUE', 'Сандалі HUGO Jens Sand',
        'Сандалі із колекції HUGO. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 1, 3, DEFAULT, 200.00, 'UAH', 'GREEN', 'Футболка HUGO Jens Sand',
        'Футболка із колекції HUGO. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 4, 3, DEFAULT, 2345.00, 'UAH', 'BLACK', 'Кофта HUGO Jens Sand',
        'Кофта із колекції HUGO. Модель виготовлена із еластичного трикотажу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 3, 3, DEFAULT, 44.22, 'UAH', 'BLACK', 'Джинси HUGO Jens Sand',
        'Джинси із колекції HUGO. Модель виготовлена із еластичного трикотажу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 3, DEFAULT, 788.00, 'UAH', 'BLUE', 'Сандалі HUGO Jens Sand',
        'Сандалі із колекції HUGO. Модель виготовлена із еластичного трикотажу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 1, 3, DEFAULT, 433.00, 'UAH', 'GREEN', 'Футболка HUGO Jens Sand',
        'Футболка із колекції HUGO. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 7, 3, DEFAULT, 1500.00, 'UAH', 'BLACK', 'Кросівки HUGO Jens Sand',
        'Кросівки із колекції HUGO. Модель виготовлена із текстильного матеріалу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 3, 3, DEFAULT, 1200.22, 'UAH', 'BLACK', 'Джинси HUGO Jens Sand',
        'Джинси із колекції HUGO. Модель виготовлена із еластичного трикотажу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 2, 1, DEFAULT, 10000.99, 'UAH', 'PINK', 'Куртка BOSS',
        'Куртка із колекції BOSS. Неутеплена модель виготовлена з деніму.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 2, 5, DEFAULT, 12000.10, 'UAH', 'BLACK', 'Куртка Emporio Armani',
        'Куртка із колекції Emporio Armani. Перехідна модель виготовленаз гладкого матеріалу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 7, 4, DEFAULT, 3499.99, 'UAH', 'BLUE', 'Бігові кросівки Reebok Zig Dynamica 3',
        'Черевики для бігу з колекції Reebok. Модель забезпечує стопи амортизацією під час активності.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 7, 4, DEFAULT, 3422.10, 'UAH', 'GREEN', 'Бігові кросівки Reebok Zig Dynamica 3',
        'Черевики для бігу з колекції Reebok. Модель забезпечує стопи амортизацією під час активності.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 6, 3, DEFAULT, 3422.10, 'UAH', 'GREEN', 'Кеди HUGO',
        'Кеди із колекції HUGO. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 6, 3, DEFAULT, 3422.10, 'UAH', 'GREEN', 'Кеди HUGO',
        'Кеди із колекції HUGO. Модель виготовлена із текстильного матеріалу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 6, 1, DEFAULT, 5422.10, 'UAH', 'WHITE', 'Кеди BOSS',
        'Кеди із колекції BOSS. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 6, 1, DEFAULT, 2422.99, 'UAH', 'BLACK', 'Кеди BOSS',
        'Кеди із колекції BOSS. Модель виготовлена із текстильного матеріалу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 6, 3, DEFAULT, 3422.19, 'UAH', 'ORANGE', 'Кеди HUGO',
        'Кеди із колекції HUGO. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 6, 3, DEFAULT, 2132.19, 'UAH', 'GREEN', 'Кеди HUGO',
        'Кеди із колекції HUGO. Модель виготовлена із текстильного матеріалу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 6, 2, DEFAULT, 1231.99, 'UAH', 'GREEN', 'Кеди NIKE',
        'Кеди із колекції HUGO. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 6, 2, DEFAULT, 1111.55, 'UAH', 'BLACK', 'Кеди NIKE',
        'Кеди із колекції HUGO. Модель виготовлена із текстильного матеріалу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 7, 2, DEFAULT, 1231.99, 'UAH', 'GREEN', 'Кросівки NIKE',
        'Кросівки із колекції NIKE. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 7, 2, DEFAULT, 1111.55, 'UAH', 'BLACK', 'Кросівки NIKE',
        'Кросівки із колекції NIKE. Модель виготовлена із текстильного матеріалу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 4, 2, DEFAULT, 1231.99, 'UAH', 'WHITE', 'Кофта NIKE',
        'Кофта із колекції NIKE. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 4, 2, DEFAULT, 1111.55, 'UAH', 'ORANGE', 'Кофта NIKE',
        'Кофта із колекції NIKE. Модель виготовлена із еластичного трикотажу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 1, 2, DEFAULT, 1231.99, 'UAH', 'BROWN', 'Футболка NIKE',
        'Футболка із колекції NIKE. Модель виготовлена із еластичного трикотажу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 1, 2, DEFAULT, 2222.55, 'UAH', 'PINK', 'Футболка NIKE',
        'Футболка із колекції NIKE. Модель виготовлена із еластичного трикотажу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 4, 2, DEFAULT, 7777.99, 'UAH', 'WHITE', 'Кофта NIKE',
        'Кофта із колекції NIKE. Модель виготовлена із еластичного трикотажу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 4, 2, DEFAULT, 1000.55, 'UAH', 'ORANGE', 'Кофта NIKE',
        'Кофта із колекції NIKE. Модель виготовлена із еластичного трикотажу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 1, 2, DEFAULT, 1200.99, 'UAH', 'BROWN', 'Футболка NIKE',
        'Футболка із колекції NIKE. Модель виготовлена із еластичного трикотажу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 1, 2, DEFAULT, 1800.55, 'UAH', 'PINK', 'Футболка NIKE',
        'Футболка із колекції NIKE. Модель виготовлена із еластичного трикотажу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 4, 2, DEFAULT, 1231.99, 'UAH', 'WHITE', 'Кофта NIKE',
        'Кофта із колекції NIKE. Модель виготовлена із еластичного трикотажу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 4, 2, DEFAULT, 1111.55, 'UAH', 'ORANGE', 'Кофта NIKE',
        'Кофта із колекції NIKE. Модель виготовлена із еластичного трикотажу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 1, 5, DEFAULT, 1231.99, 'UAH', 'BROWN', 'Футболка Emporio Armani',
        'Футболка із колекції Emporio Armani. Модель виготовлена із еластичного трикотажу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 1, 5, DEFAULT, 2222.55, 'UAH', 'PINK', 'Футболка Emporio Armani',
        'Футболка із колекції Emporio Armani. Модель виготовлена із еластичного трикотажу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 4, 5, DEFAULT, 7777.99, 'UAH', 'WHITE', 'Кофта Emporio Armani',
        'Кофта із колекції Emporio Armani. Модель виготовлена із еластичного трикотажу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 4, 5, DEFAULT, 1000.55, 'UAH', 'ORANGE', 'Кофта Emporio Armani',
        'Кофта із колекції Emporio Armani. Модель виготовлена із еластичного трикотажу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 1, 5, DEFAULT, 1200.99, 'UAH', 'BROWN', 'Футболка Emporio Armani',
        'Футболка із колекції Emporio Armani. Модель виготовлена із еластичного трикотажу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 1, 5, DEFAULT, 1800.55, 'UAH', 'PINK', 'Футболка Emporio Armani',
        'Футболка із колекції Emporio Armani. Модель виготовлена із еластичного трикотажу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 3, DEFAULT, 788.00, 'UAH', 'BLUE', 'Сандалі HUGO Jens Sand',
        'Сандалі із колекції HUGO. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 3, DEFAULT, 555.00, 'UAH', 'BLACK', 'Сандалі HUGO Jens Sand',
        'Сандалі із колекції HUGO. Модель виготовлена із текстильного матеріалу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 3, DEFAULT, 300.00, 'UAH', 'WHITE', 'Сандалі HUGO Jens Sand',
        'Сандалі із колекції HUGO. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 1, DEFAULT, 788.00, 'UAH', 'BLACK', 'Сандалі BOSS',
        'Сандалі із колекції BOSS. Модель виготовлена із текстильного матеріалу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 1, DEFAULT, 200.00, 'UAH', 'ORANGE', 'Сандалі BOSS',
        'Сандалі із колекції BOSS. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 1, DEFAULT, 988.99, 'UAH', 'WHITE', 'Сандалі BOSS',
        'Сандалі із колекції BOSS. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 1, DEFAULT, 5600.00, 'UAH', 'BLUE', 'Сандалі BOSS',
        'Сандалі із колекції BOSS. Модель виготовлена із натуральної шкіри.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 2, DEFAULT, 788.00, 'UAH', 'BLACK', 'Сандалі NIKE',
        'Сандалі із колекції NIKE. Модель виготовлена із натуральної шкіри.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 2, DEFAULT, 12788.00, 'UAH', 'WHITE', 'Сандалі NIKE',
        'Сандалі із колекції NIKE. Модель виготовлена із натуральної шкіри.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 2, DEFAULT, 15788.00, 'UAH', 'BLACK', 'Сандалі NIKE',
        'Сандалі із колекції NIKE. Модель виготовлена із натуральної шкіри.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 2, DEFAULT, 788.00, 'UAH', 'ORANGE', 'Сандалі NIKE',
        'Сандалі із колекції NIKE. Модель виготовлена із натуральної шкіри.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 5, DEFAULT, 9999.99, 'UAH', 'WHITE', 'Сандалі Emporio Armani',
        'Сандалі із колекції Emporio Armani. Модель виготовлена із натуральної шкіри.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 6, DEFAULT, 2200.99, 'UAH', 'WHITE', 'Сандалі Levi''s',
        'Сандалі із колекції Levi''s. Модель виготовлена із натуральної шкіри.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 6, DEFAULT, 2200.99, 'UAH', 'BLUE', 'Сандалі Levi''s',
        'Сандалі із колекції Levi''s. Модель виготовлена із натуральної шкіри.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 6, DEFAULT, 800.99, 'UAH', 'BLACK', 'Сандалі Levi''s',
        'Сандалі із колекції Levi''s. Модель виготовлена із натуральної шкіри.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 3, 6, DEFAULT, 2500.99, 'UAH', 'BLUE', 'Джинси Levi''s',
        'Джинси із колекції Levi''s. Модель виготовлена із натуральної шкіри.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 3, 6, DEFAULT, 1200.99, 'UAH', 'BLACK', 'Джинси Levi''s',
        'Джинси із колекції Levi''s. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 3, 6, DEFAULT, 1999.99, 'UAH', 'BLUE', 'Джинси Levi''s',
        'Джинси із колекції Levi''s. Модель виготовлена із еластичного трикотажу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 3, 6, DEFAULT, 899.99, 'UAH', 'BLACK', 'Джинси Levi''s',
        'Джинси із колекції Levi''s. Модель виготовлена із еластичного трикотажу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 6, DEFAULT, 2200.99, 'UAH', 'WHITE', 'Сандалі Levi''s',
        'Сандалі із колекції Levi''s. Модель виготовлена із натурального матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 6, DEFAULT, 2200.99, 'UAH', 'BLUE', 'Сандалі Levi''s',
        'Сандалі із колекції Levi''s. Модель виготовлена із текстильного матеріалу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 5, 6, DEFAULT, 800.99, 'UAH', 'BLACK', 'Сандалі Levi''s',
        'Сандалі із колекції Levi''s. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 3, 6, DEFAULT, 2500.99, 'UAH', 'BLUE', 'Джинси Levi''s',
        'Джинси із колекції Levi''s. Модель виготовлена із еластичного трикотажу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 3, 6, DEFAULT, 1200.99, 'UAH', 'BLACK', 'Джинси Levi''s',
        'Джинси із колекції Levi''s. Модель виготовлена із еластичного трикотажу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 3, 6, DEFAULT, 1999.99, 'UAH', 'BLUE', 'Джинси Levi''s',
        'Джинси із колекції Levi''s. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 3, 5, DEFAULT, 899.99, 'UAH', 'BLACK', 'Джинси Emporio Armani',
        'Джинси із колекції Emporio Armani. Модель виготовлена із текстильного матеріалу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 3, 5, DEFAULT, 2500.99, 'UAH', 'BLUE', 'Джинси Emporio Armani',
        'Джинси із колекції Emporio Armani. Модель виготовлена із еластичного трикотажу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 3, 5, DEFAULT, 1200.99, 'UAH', 'BLACK', 'Джинси Emporio Armani',
        'Джинси із колекції Emporio Armani. Модель виготовлена із еластичного трикотажу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 3, 5, DEFAULT, 1999.99, 'UAH', 'BLUE', 'Джинси Emporio Armani',
        'Джинси із колекції Emporio Armani. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 3, 5, DEFAULT, 899.99, 'UAH', 'BLACK', 'Джинси Emporio Armani',
        'Джинси із колекції Emporio Armani. Модель виготовлена із текстильного матеріалу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 8, 5, DEFAULT, 200.99, 'UAH', 'BLACK', 'Ремінь Emporio Armani',
        'Ремінь із колекції Emporio Armani. Модель виготовлена із текстильного матеріалу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 8, 5, DEFAULT, 244.99, 'UAH', 'BLACK', 'Ремінь Emporio Armani',
        'Ремінь із колекції Emporio Armani. Модель виготовлена із текстильного матеріалу.', 'MEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 8, 1, DEFAULT, 322.99, 'UAH', 'BLACK', 'Ремінь BOSS',
        'Ремінь із колекції BOSS. Модель виготовлена із текстильного матеріалу.', 'WOMEN');
INSERT INTO product (product_id, category_id, company_id, sale_id, price, currency, color, product_name, description, male)
VALUES (DEFAULT, 8, 1, DEFAULT, 611.99, 'UAH', 'BLACK', 'Ремінь BOSS',
        'Ремінь із колекції BOSS. Модель виготовлена із текстильного матеріалу.', 'MEN');

INSERT INTO review (review_id, product_id, review_value) VALUES (DEFAULT, 1, 'класні черевики');
INSERT INTO review (review_id, product_id, review_value) VALUES (DEFAULT, 1, 'гарний колір');
INSERT INTO review (review_id, product_id, review_value) VALUES (DEFAULT, 4, 'товар на 10 балів');
INSERT INTO review (review_id, product_id, review_value) VALUES (DEFAULT, 4, 'дуже вузькі - добре подумайте ніж чим покупати цю модель');

insert into category_size
select category_id, size_id
from category
         left join size
                   on size_id in (1, 2, 3, 4, 5)
where category_id in (1, 2);

insert into category_size
select category_id, size_id
from category
         left join size
                   on size_id in (2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 16, 18)
where category_id in (3);

insert into category_size
select category_id, size_id
from category
         left join size
                   on size_id in (1, 2, 3, 4, 5)
where category_id in (4);

insert into category_size
select category_id, size_id
from category
         left join size
                   on size_id in (12, 13, 14, 15, 16, 17, 18, 19)
where category_id in (5, 6, 7);

insert into category_size
select category_id, size_id
from category
         left join size
                   on size_id in (2, 3, 4, 5, 12, 14, 16)
where category_id in (8);

insert into product_size
select product_id, size_id
from product
         left join size
                   on size_id in (1, 2, 3, 4)
where product_id in (64, 66, 68, 4, 8, 36, 3, 24, 28, 32, 69, 71);

insert into product_size
select product_id, size_id
from product
         left join size
                   on size_id in (2, 3, 4, 5)
where product_id in (35, 23, 27, 31, 9, 70, 72);

insert into product_size
select product_id, size_id
from product
         left join size
                   on size_id in (1, 2, 3, 4, 5)
where product_id in (33, 37, 2, 6, 29, 25, 34, 38, 26, 30);

insert into product_size
select product_id, size_id
from product
         left join size
                   on size_id in (2, 3, 4, 5, 7, 8, 9, 10, 11)
where product_id in (57, 61);

insert into product_size
select product_id, size_id
from product
         left join size
                   on size_id in (6, 7, 8)
where product_id in (56, 62);

insert into product_size
select product_id, size_id
from product
         left join size
                   on size_id in (7, 8, 9, 10, 11)
where product_id in (65, 67, 54, 55, 57, 61, 63);

insert into product_size
select product_id, size_id
from product
         left join size
                   on size_id in (12, 14)
where product_id in (10);

insert into product_size
select product_id, size_id
from product
         left join size
                   on size_id in (12, 13, 14, 15)
where product_id in (16, 18, 14, 20, 7, 22, 11, 42, 45, 40, 51, 53, 59, 46, 49);

insert into product_size
select product_id, size_id
from product
         left join size
                   on size_id in (12, 13, 14, 15, 16, 17, 18, 19)
where product_id in (15, 13, 17, 19, 21, 12, 1, 5, 39, 41, 50, 52, 58, 60, 47, 48, 43, 44);

INSERT INTO usr (user_id, username, password, role)
VALUES (DEFAULT, 'admin', '$2a$12$BzYKcHt9Co7imxqS2rC2xuiV6IN6urlq7zoVhUx2BMlNp/wGJXCaq', 'ADMIN');
INSERT INTO usr (user_id, username, password, role)
VALUES (DEFAULT, 'user', '$2a$12$2txe2cyZh/LcipDZ3vTPIexdmPO0FeJqm0L9zAMyNUdSaZ8g8uENu', 'USER');

INSERT INTO product_statistics (product_id, product_views) VALUES (1, 1);
INSERT INTO product_statistics (product_id, product_views) VALUES (2, 2);
INSERT INTO product_statistics (product_id, product_views) VALUES (3, 3);
INSERT INTO product_statistics (product_id, product_views) VALUES (4, 4);
INSERT INTO product_statistics (product_id, product_views) VALUES (5, 5);
